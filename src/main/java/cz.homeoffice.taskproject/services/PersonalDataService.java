package cz.homeoffice.taskproject.services;

import cz.homeoffice.taskproject.convertors.PersonalDataConvertor;
import cz.homeoffice.taskproject.entity.PersonalData;
import cz.homeoffice.taskproject.repository.PersonalDataRepository;
import cz.homeoffice.taskproject.rest.models.PersonalDataDto;
import cz.homeoffice.taskproject.services.exception.PersonalDataServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalDataService {

    private final PersonalDataRepository personalDataRepository;

    private final PersonalDataConvertor personalDataConvertor;

    public PersonalDataDto addPersonalData(PersonalDataDto personalDataDto) {
        LocalDateTime ldt = LocalDateTime.now();
        personalDataDto.setDateOfCreation(LocalDate.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt)));
        PersonalData personalData = personalDataConvertor.toEntity(personalDataDto);
        PersonalData savedDao = personalDataRepository.save(personalData);
        log.info("Adding user " + personalData);
        return personalDataConvertor.toDto(savedDao);
    }

    public List<PersonalDataDto> getAllPersonalData() {
        log.info("Getting personal data");
        return personalDataConvertor.toDto(personalDataRepository.findAll());
    }

    public PersonalDataDto getPersonalDataById(Long id) {
        Optional<PersonalData> dataDao = personalDataRepository.findById(id.intValue());
        if (!dataDao.isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        log.info("Getting data");
        return personalDataConvertor.toDto(dataDao.get());
    }

    public void deletePersonalDataById(Long id) {
        if (!personalDataRepository.findById(id.intValue()).isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        log.info("Deleting data");
        personalDataRepository.deleteById(id.intValue());
    }

    public PersonalDataDto updatePersonalDataById(PersonalDataDto personalDataDto) {
        if (!personalDataRepository.findById(personalDataDto.getId().intValue()).isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        PersonalData dao = personalDataConvertor.toEntity(personalDataDto);
        PersonalData save = personalDataRepository.save(dao);
        return personalDataConvertor.toDto(save);
    }
}
