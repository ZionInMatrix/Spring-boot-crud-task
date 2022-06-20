package cz.homeoffice.taskproject.rest.controllers;

import cz.homeoffice.taskproject.rest.models.PersonalDataDto;
import cz.homeoffice.taskproject.services.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("rest/data")
@RequiredArgsConstructor
@RestController
public class PersonalDataRestController {

    private final PersonalDataService personalDataService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    private PersonalDataDto addPersonalData(@RequestBody PersonalDataDto personalDataRest) {
        return personalDataService.addPersonalData(personalDataRest);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    private List<PersonalDataDto> getAllPersonalData() {
        return personalDataService.getAllPersonalData();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private PersonalDataDto getPersonalDataById(@PathVariable("id") Long id) {
        return personalDataService.getPersonalDataById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private void deletePersonalDataById(@PathVariable("id") Integer id) {
        personalDataService.deletePersonalDataById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    private PersonalDataDto updatePersonalDataById(@PathVariable("id") Long id, @RequestBody PersonalDataDto personalDataDto) {
        return personalDataService.updatePersonalDataById(personalDataDto);
    }

    @RequestMapping(value = "/api/users/user/{id}", method = RequestMethod.GET)
    public PersonalDataDto getPersonalData(@PathVariable Long id) {
        return personalDataService.getPersonalDataById(id);
    }
}
