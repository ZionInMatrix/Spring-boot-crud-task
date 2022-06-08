package cz.homeoffice.taskproject.convertors;

import cz.homeoffice.taskproject.entity.PersonalData;
import cz.homeoffice.taskproject.rest.models.PersonalDataRest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonalDataConvertor {

    public PersonalData toDao(PersonalDataRest rest) {
        PersonalData dao = new PersonalData();
        dao.setDateOfBirthday(rest.getDateOfBirthday());
        dao.setAddress(rest.getAddress());
        dao.setPhoneNumber(rest.getPhoneNumber());
        dao.setDateOfCreation(rest.getDateOfCreation());
        return dao;
    }

    public PersonalDataRest toRest(PersonalData dao) {
        PersonalDataRest rest = new PersonalDataRest();
        rest.setId(dao.getId());
        rest.setAddress(dao.getAddress());
        rest.setPhoneNumber(dao.getPhoneNumber());
        rest.setDateOfBirthday(dao.getDateOfBirthday());
        rest.setDateOfCreation(dao.getDateOfCreation());
        return rest;
    }

    public List<PersonalDataRest> toRest(Iterable<PersonalData> daos) {
        List<PersonalDataRest> dataRests = new ArrayList<>();
        for (PersonalData dao : daos) {
            dataRests.add(toRest(dao));
        }
        return dataRests;
    }

    public PersonalData toDao(Integer id, PersonalDataRest dataRest) {
        PersonalData dao = toDao(dataRest);
        dao.setId(id);
        return dao;
    }
}
