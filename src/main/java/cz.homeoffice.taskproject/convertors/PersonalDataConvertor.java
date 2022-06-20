package cz.homeoffice.taskproject.convertors;

import cz.homeoffice.taskproject.entity.PersonalData;
import cz.homeoffice.taskproject.rest.models.PersonalDataDto;

import java.util.ArrayList;
import java.util.List;

public class PersonalDataConvertor {

    public PersonalData toDao(PersonalDataDto dto) {
        return PersonalData.builder()
                .id(dto.getId())
                .birthday(dto.getDateOfBirthday())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public PersonalDataDto toDto(PersonalData dao) {
        return PersonalDataDto.builder()
                .id(dao.getId())
                .address(dao.getAddress())
                .dateOfBirthday(dao.getBirthday())
                .build();
    }

    public List<PersonalDataDto> toDto(Iterable<PersonalData> daos) {
        List<PersonalDataDto> dataRests = new ArrayList<>();
        for (PersonalData dao : daos) {
            dataRests.add(toDto(dao));
        }
        return dataRests;
    }

    public PersonalData toDao(Long id, PersonalDataDto dataRest) {
        PersonalData dao = toDao(dataRest);
        dao.setId(id);
        return dao;
    }
}