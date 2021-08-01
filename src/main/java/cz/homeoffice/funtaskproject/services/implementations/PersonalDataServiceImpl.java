package cz.homeoffice.funtaskproject.services.implementations;

import cz.homeoffice.funtaskproject.convertors.PersonalDataConvertor;
import cz.homeoffice.funtaskproject.entity.PersonalDataDao;
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
     * @param personalDataRest
     * @return
     */
    @Override
    public PersonalDataRest addPersonalData(PersonalDataRest personalDataRest) {
        LocalDateTime ldt = LocalDateTime.now();
        personalDataRest.setDateOfCreation(LocalDate.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt)));
        PersonalDataDao personalDataDao = personalDataConvertor.toDao(personalDataRest);
        PersonalDataDao savedDao = personalDataRepository.save(personalDataDao);
        logger.info("Adding user " + personalDataDao);
        return personalDataConvertor.toRest(savedDao);
    }

    /**
     * @return
     */
    @Override
    public List<PersonalDataRest> getAllPersonalData() {
        logger.info("Getting personal data");
        return personalDataConvertor.toRest(personalDataRepository.findAll());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public PersonalDataRest getPersonalDataById(Integer id) {
        Optional<PersonalDataDao> dataDao = personalDataRepository.findById(id);
        if (!dataDao.isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        logger.info("Getting data");
        return personalDataConvertor.toRest(dataDao.get());
    }

    /**
     * @param id
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
     * @param id
     * @param personalDataRest
     * @return
     */
    @Override
    public PersonalDataRest updatePersonalDataById(Integer id, PersonalDataRest personalDataRest) {
        if (!personalDataRepository.findById(id).isPresent()) {
            throw new PersonalDataServiceException("The id number isn't found");
        }
        PersonalDataDao dao = personalDataConvertor.toDao(id, personalDataRest);
        PersonalDataDao save = personalDataRepository.save(dao);
        return personalDataConvertor.toRest(save);
    }
}
