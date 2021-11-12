package cz.homeoffice.funtaskproject.services.implementations;

import cz.homeoffice.funtaskproject.convertors.PersonalDataConvertor;
import cz.homeoffice.funtaskproject.entity.PersonalData;
import cz.homeoffice.funtaskproject.repositories.PersonalDataRepository;
import cz.homeoffice.funtaskproject.rest.models.PersonalDataRest;
import cz.homeoffice.funtaskproject.services.PersonalDataService;
import cz.homeoffice.funtaskproject.services.exceptions.PersonalDataServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class PersonalDataServiceImpl implements PersonalDataService {

    private static final Logger logger = LogManager.getLogger(PersonalDataServiceImpl.class);

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
    private PersonalDataConvertor personalDataConvertor;

    /**
     * This method will add personal data to the PersonalDataDao
     *
     * @param personalDataRest will return
     * @return converted PersonalDataDao to PersonalDataRest
     */
    @Override
    public PersonalDataRest addPersonalData(PersonalDataRest personalDataRest) {
        LocalDateTime ldt = LocalDateTime.now();
        personalDataRest.setDateOfCreation(LocalDate.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt)));
        PersonalData personalData = personalDataConvertor.toDao(personalDataRest);
        PersonalData savedDao = personalDataRepository.save(personalData);
        logger.info("Adding user " + personalData);
        return personalDataConvertor.toRest(savedDao);
    }

    /**
     * this method will get all PersonalData from PersonalDataDao
     *
     * @return converted PersonalDataDao to PersonalDataRest
     */
    @Override
    public List<PersonalDataRest> getAllPersonalData() {
        logger.info("Getting personal data");
        return personalDataConvertor.toRest(personalDataRepository.findAll());
    }

    /**
     * this method will get PersonalData from PersonalDataDao by ID
     *
     * @param id required parameter
     * @return converted PersonalDataDao to PersonalDataRest
     */
    @Override
    public PersonalDataRest getPersonalDataById(Integer id) {
        Optional<PersonalData> dataDao = personalDataRepository.findById(id);
        if (!dataDao.isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        logger.info("Getting data");
        return personalDataConvertor.toRest(dataDao.get());
    }

    /**
     * this method will PersonalDataDao by ID
     *
     * @param id required parameter
     */
    @Override
    public void deletePersonalDataById(Integer id) {
        if (!personalDataRepository.findById(id).isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        logger.info("Deleting data");
        personalDataRepository.deleteById(id);
    }

    /**
     * this method will update the PersonalData from PersonalDataDao by ID
     *
     * @param id               required parameter
     * @param personalDataRest required parameter
     * @return converted PersonalDataDao to PersonalDataRest
     */
    @Override
    public PersonalDataRest updatePersonalDataById(Integer id, PersonalDataRest personalDataRest) {
        if (!personalDataRepository.findById(id).isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        PersonalData dao = personalDataConvertor.toDao(id, personalDataRest);
        PersonalData save = personalDataRepository.save(dao);
        return personalDataConvertor.toRest(save);
    }
}
