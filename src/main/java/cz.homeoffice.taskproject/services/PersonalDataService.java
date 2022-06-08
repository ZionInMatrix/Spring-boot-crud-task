package cz.homeoffice.taskproject.services;

import cz.homeoffice.taskproject.rest.models.PersonalDataRest;

import java.util.List;

public interface PersonalDataService {

    PersonalDataRest addPersonalData(PersonalDataRest personalDataRest);

    List<PersonalDataRest> getAllPersonalData();

    PersonalDataRest getPersonalDataById(Integer id);

    void deletePersonalDataById(Integer id);

    PersonalDataRest updatePersonalDataById(Integer id, PersonalDataRest personalDataRest);
}
