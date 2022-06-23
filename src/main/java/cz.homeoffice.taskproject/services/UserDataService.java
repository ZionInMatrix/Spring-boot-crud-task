package cz.homeoffice.taskproject.services;

import cz.homeoffice.taskproject.convertors.RoleConvertor;
import cz.homeoffice.taskproject.convertors.UserConvertor;
import cz.homeoffice.taskproject.entity.Role;
import cz.homeoffice.taskproject.entity.UserData;
import cz.homeoffice.taskproject.repository.RoleRepository;
import cz.homeoffice.taskproject.repository.UserDataRepository;
import cz.homeoffice.taskproject.rest.models.RoleDto;
import cz.homeoffice.taskproject.rest.models.RoleToUserRequest;
import cz.homeoffice.taskproject.rest.models.UserDataDto;
import cz.homeoffice.taskproject.services.exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final RoleConvertor roleConvertor;
    private final UserConvertor userConvertor;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = userDataRepository.findByUsername(username);

        if (userData == null) {
            log.error("User not found in the database");
            throw new UserServiceException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userData.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(userData.getUsername(), userData.getPassword(),
                authorities);
    }

    public UserDataDto addUserData(UserDataDto dto) {
        log.info("Saving user data to the database");
        return userConvertor.toDto(userDataRepository.save(
                UserData.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .sysDateCreate(LocalDateTime.now())
                        .build()));
    }

    public RoleDto addRole(RoleDto roleDto) {
        log.info("Saving new role to the database");
        return roleConvertor.toDto(roleRepository.save(
                Role.builder()
                        .name(roleDto.getName())
                        .build()));
    }

    public void addRoleToUser(RoleToUserRequest roleToUserRequest) {
        log.info("Adding role {} to user {}", roleToUserRequest.getRoleName(), roleToUserRequest.getUsername());
        UserData userData = userDataRepository.findByUsername(roleToUserRequest.getRoleName());
        Role role = roleRepository.findByName(roleToUserRequest.getUsername());
        userData.getRoles().add(role);
    }

    public UserDataDto getUserByUsername(String username) {
        log.info("Fetching user {}", username);
        return userConvertor.toDto(userDataRepository.findByUsername(username));
    }

    public List<UserDataDto> getUsers() {
        log.info("Fetching all users");
        return userDataRepository.findAll().stream().map(userData -> UserDataDto.builder()
                .build()).collect(Collectors.toList());
    }

}
