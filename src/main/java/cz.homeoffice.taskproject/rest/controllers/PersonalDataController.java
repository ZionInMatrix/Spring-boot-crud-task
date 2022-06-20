package cz.homeoffice.taskproject.rest.controllers;

import cz.homeoffice.taskproject.rest.models.PersonalDataDto;
import cz.homeoffice.taskproject.services.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/data")
@RequiredArgsConstructor
@RestController
public class PersonalDataController {

    private final PersonalDataService personalDataService;

    @PostMapping("/add")
    private PersonalDataDto addPersonalData(@RequestBody PersonalDataDto personalDataRest) {
        return personalDataService.addPersonalData(personalDataRest);
    }

    @GetMapping("/api/get-all")
    private List<PersonalDataDto> getAllPersonalData() {
        return personalDataService.getAllPersonalData();
    }

    @GetMapping("/api/get/{id}")
    private PersonalDataDto getPersonalDataById(@PathVariable("id") Long id) {
        return personalDataService.getPersonalDataById(id);
    }

    @PostMapping("/api/delete-data/{id}")
    private void deletePersonalDataById(@PathVariable("id") Long id) {
        personalDataService.deletePersonalDataById(id);
    }

    @PostMapping("/api/update")
    private PersonalDataDto updatePersonalDataById(@RequestBody PersonalDataDto personalDataDto) {
        return personalDataService.updatePersonalDataById(personalDataDto);
    }
}
