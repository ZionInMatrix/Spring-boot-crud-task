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
import cz.homeoffice.taskproject.services.TokenService;
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
    private final TokenService tokenService;

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
    private void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        tokenService.refreshToken(request, response);
    }
}