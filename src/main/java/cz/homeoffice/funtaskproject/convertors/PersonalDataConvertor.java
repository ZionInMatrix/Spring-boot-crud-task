package cz.homeoffice.funtaskproject.convertors;

import cz.homeoffice.funtaskproject.entity.PersonalDataDao;
import cz.homeoffice.funtaskproject.rest.models.PersonalDataRest;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class PersonalDataConvertor {

    public PersonalDataDao toDao(PersonalDataRest rest) {
        PersonalDataDao dao = new PersonalDataDao();
        dao.setDateOfBirthday(rest.getDateOfBirthday());
        dao.setAddress(rest.getAddress());
        dao.setPhoneNumber(rest.getPhoneNumber());
        dao.setDateOfCreation(rest.getDateOfCreation());
        return dao;
    }

    public PersonalDataRest toRest(PersonalDataDao dao) {
        PersonalDataRest rest = new PersonalDataRest();
        rest.setId(dao.getId());
        rest.setAddress(dao.getAddress());
        rest.setPhoneNumber(dao.getPhoneNumber());
        rest.setDateOfBirthday(dao.getDateOfBirthday());
        rest.setDateOfCreation(dao.getDateOfCreation());
        return rest;
    }

    public List<PersonalDataRest> toRest(Iterable<PersonalDataDao> daos) {
        List<PersonalDataRest> dataRests = new ArrayList<>();
        for (PersonalDataDao dao : daos) {
            dataRests.add(toRest(dao));
        }
        return dataRests;
    }

    public PersonalDataDao toDao(Integer id, PersonalDataRest dataRest) {
        PersonalDataDao dao = toDao(dataRest);
        dao.setId(id);
        return dao;
    }
}
