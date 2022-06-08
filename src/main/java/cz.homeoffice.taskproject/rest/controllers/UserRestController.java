package cz.homeoffice.taskproject.rest.controllers;

import cz.homeoffice.funtaskproject.rest.models.PersonalDataRest;
import cz.homeoffice.funtaskproject.rest.models.UserRest;
import cz.homeoffice.funtaskproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "rest/user", method = RequestMethod.POST)
    private UserRest addUser(@RequestBody UserRest userRest) {
        return userService.addUser(userRest);
    }

    @RequestMapping(value = "rest/users", method = RequestMethod.GET)
    private List<UserRest> getAllUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/api/user/data/{accessToken}", method = RequestMethod.GET)
    public PersonalDataRest getUserPersonalDataByAccessToken(@PathVariable String accessToken) {
        return userService.getUserPersonalDataByAccessToken(accessToken);
    }

    @RequestMapping(value = "/rest/user/{id}", method = RequestMethod.DELETE)
    private void deleteCustomerById(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
    }

    @RequestMapping(value = "/rest/user/{id}", method = RequestMethod.PUT)
    private UserRest updateCustomerById(@PathVariable("id") Integer id, @RequestBody UserRest userRest) {
        return userService.updateUser(id, userRest);
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String getToken(@RequestParam("userName") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }
}
