package cz.homeoffice.taskproject.rest.controllers;

import cz.homeoffice.taskproject.rest.models.PersonalDataDto;
import cz.homeoffice.taskproject.rest.models.UserDto;
import cz.homeoffice.taskproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add-user")
    private UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping("/api/get-users")
    private List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/api/access")
    public PersonalDataDto getUserPersonalDataByAccessToken(@RequestParam String accessToken) {
        return userService.getUserPersonalDataByAccessToken(accessToken);
    }

    @PostMapping("/api/delete/{id}")
    private void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/api/update")
    private UserDto updateCustomerById(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @GetMapping("/api/token")
    public String getToken(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }
}
