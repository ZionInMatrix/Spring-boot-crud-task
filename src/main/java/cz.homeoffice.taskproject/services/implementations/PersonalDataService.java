package cz.homeoffice.taskproject.services.implementations;

import cz.homeoffice.taskproject.convertors.PersonalDataConvertor;
import cz.homeoffice.taskproject.entity.PersonalData;
import cz.homeoffice.taskproject.repository.PersonalDataRepository;
import cz.homeoffice.taskproject.rest.models.PersonalDataDto;
import cz.homeoffice.taskproject.services.exceptions.PersonalDataServiceException;
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
        PersonalData personalData = personalDataConvertor.toDao(personalDataDto);
        PersonalData savedDao = personalDataRepository.save(personalData);
        log.info("Adding user " + personalData);
        return personalDataConvertor.toDto(savedDao);
    }

    public List<PersonalDataDto> getAllPersonalData() {
        log.info("Getting personal data");
        return personalDataConvertor.toDto(personalDataRepository.findAll());
    }

    public PersonalDataDto getPersonalDataById(Integer id) {
        Optional<PersonalData> dataDao = personalDataRepository.findById(id);
        if (!dataDao.isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        log.info("Getting data");
        return personalDataConvertor.toDto(dataDao.get());
    }

    public void deletePersonalDataById(Integer id) {
        if (!personalDataRepository.findById(id).isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        log.info("Deleting data");
        personalDataRepository.deleteById(id);
    }

    public PersonalDataDto updatePersonalDataById(Integer id, PersonalDataDto personalDataDto) {
        if (!personalDataRepository.findById(id).isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        PersonalData dao = personalDataConvertor.toDao(id, personalDataDto);
        PersonalData save = personalDataRepository.save(dao);
        return personalDataConvertor.toDto(save);
    }
}
