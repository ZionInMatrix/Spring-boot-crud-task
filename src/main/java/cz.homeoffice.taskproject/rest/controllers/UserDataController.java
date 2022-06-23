package cz.homeoffice.taskproject.rest.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.homeoffice.taskproject.entity.Role;
import cz.homeoffice.taskproject.entity.UserData;
import cz.homeoffice.taskproject.repository.UserDataRepository;
import cz.homeoffice.taskproject.rest.models.RoleDto;
import cz.homeoffice.taskproject.rest.models.RoleToUserRequest;
import cz.homeoffice.taskproject.rest.models.UserDataDto;
import cz.homeoffice.taskproject.services.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserDataController {

    private final UserDataService userDataService;
    private final UserDataRepository userDataRepository;

    @GetMapping("/role/get-users")
    private List<UserDataDto> getUsers() {
        return userDataService.getUsers();
    }

    @PostMapping("/role/add-user")
    private UserDataDto addUserData(@RequestBody UserDataDto userDataDto) {
        return userDataService.addUserData(userDataDto);
    }

    @PostMapping("/role/add-role")
    private RoleDto addRole(@RequestBody RoleDto roleDto) {
        return userDataService.addRole(roleDto);
    }

    @PostMapping("/role/role-user")
    private void addRoleToUser(@RequestBody RoleToUserRequest request) {
        userDataService.addRoleToUser(request);
    }

    @GetMapping("/token/refresh")
    private void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {

                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UserData user = userDataRepository.findByUsername(username);

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() * 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
//                response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}