package cz.homeoffice.taskproject.rest.controllers;

import cz.homeoffice.taskproject.rest.models.PersonalDataDto;
import cz.homeoffice.taskproject.rest.models.UserDto;
import cz.homeoffice.taskproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    private UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @RequestMapping(value = "/get-users", method = RequestMethod.GET)
    private List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/access", method = RequestMethod.GET)
    public PersonalDataDto getUserPersonalDataByAccessToken(@RequestParam String accessToken) {
        return userService.getUserPersonalDataByAccessToken(accessToken);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    private void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    private UserDto updateCustomerById(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String getToken(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }
}
