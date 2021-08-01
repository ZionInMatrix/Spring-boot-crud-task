package cz.homeoffice.funtaskproject.rest.controllers;

import cz.homeoffice.funtaskproject.rest.models.PersonalDataRest;
import cz.homeoffice.funtaskproject.services.PersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("rest/data")
@RestController
public class PersonalDataRestController {

    @Autowired
    private PersonalDataService personalDataService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    private PersonalDataRest addPersonalData(@RequestBody PersonalDataRest personalDataRest) {
        return personalDataService.addPersonalData(personalDataRest);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    private List<PersonalDataRest> getAllPersonalData() {
        return personalDataService.getAllPersonalData();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private PersonalDataRest getPersonalDataById(@PathVariable("id") Integer id) {
        return personalDataService.getPersonalDataById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private void deletePersonalDataById(@PathVariable("id") Integer id) {
        personalDataService.deletePersonalDataById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    private PersonalDataRest updatePersonalDataById(@PathVariable("id") Integer id, @RequestBody PersonalDataRest personalDataRest) {
        return personalDataService.updatePersonalDataById(id, personalDataRest);
    }

    @RequestMapping(value = "/api/users/user/{id}", method = RequestMethod.GET)
    public PersonalDataRest getPersonalData(@PathVariable Integer id) {
        return personalDataService.getPersonalDataById(id);
    }
}
