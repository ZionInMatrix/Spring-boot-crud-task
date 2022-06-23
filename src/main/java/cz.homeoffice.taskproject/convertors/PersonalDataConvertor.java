package cz.homeoffice.taskproject.convertors;

import cz.homeoffice.taskproject.entity.PersonalData;
import cz.homeoffice.taskproject.rest.models.PersonalDataDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalDataConvertor {

    public PersonalData toEntity(PersonalDataDto dto) {
        return PersonalData.builder()
                .id(dto.getId())
                .birthday(dto.getBirthday())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .postcode(dto.getPostcode())
                .email(dto.getEmail())
                .build();
    }

    public PersonalDataDto toDto(PersonalData dao) {
        return PersonalDataDto.builder()
                .id(dao.getId())
                .birthday(dao.getBirthday())
                .address(dao.getAddress())
                .phoneNumber(dao.getPhoneNumber())
                .postcode(dao.getPostcode())
                .email(dao.getEmail())
                .build();
    }

    public List<PersonalDataDto> toDto(Iterable<PersonalData> daos) {
        List<PersonalDataDto> dataRests = new ArrayList<>();
        for (PersonalData dao : daos) {
            dataRests.add(toDto(dao));
        }
        return dataRests;
    }

    public PersonalData toEntity(Long id, PersonalDataDto dataRest) {
        PersonalData dao = toEntity(dataRest);
        dao.setId(id);
        return dao;
    }
}
