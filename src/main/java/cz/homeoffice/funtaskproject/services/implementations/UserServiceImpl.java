package cz.homeoffice.funtaskproject.services.implementations;

import cz.homeoffice.funtaskproject.convertors.UserConvertor;
import cz.homeoffice.funtaskproject.entity.PersonalDataDao;
import cz.homeoffice.funtaskproject.entity.UserDao;
import cz.homeoffice.funtaskproject.repositories.UserRepository;
import cz.homeoffice.funtaskproject.rest.models.PersonalDataRest;
import cz.homeoffice.funtaskproject.rest.models.UserRest;
import cz.homeoffice.funtaskproject.services.PersonalDataService;
import cz.homeoffice.funtaskproject.services.UserService;
import cz.homeoffice.funtaskproject.services.exceptions.UserServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConvertor userConvertor;

    @Autowired
    private PersonalDataService personalDataService;

    /**
     * This method will add User to the UserDao
     *
     * @param userRest will return
     * @return converted UserDao to UserRest
     */

    @Override
    public UserRest addUser(UserRest userRest) {
        UUID accessToken = UUID.randomUUID();
        LocalDateTime ldt = LocalDateTime.now();
        PersonalDataDao data = new PersonalDataDao();
        data.setPhoneNumber(userRest.getPersonalData().getPhoneNumber());
        data.setDateOfCreation(LocalDate.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt)));
        data.setDateOfBirthday(userRest.getPersonalData().getDateOfBirthday());
        data.setAddress(userRest.getPersonalData().getAddress());
        userRest.setAccessToken(String.valueOf(accessToken));
        UserDao userDao = userConvertor.toDao(userRest);
        userDao.setPersonalData(data);
        UserDao savedUser = userRepository.save(userDao);
        logger.info("Adding user " + userDao);
        return userConvertor.toRest(savedUser);
    }

    /**
     * this method will get all Users from UserDao
     *
     * @return converted UserDaos to UserRests
     */
    @Override
    public List<UserRest> getUsers() {
        logger.info("Getting users");
        return userConvertor.toRest(userRepository.findAll());
    }

    /**
     * this method will delete User from UserDao by ID
     *
     * @param id required parameter
     */
    @Override
    public void deleteUserById(Integer id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserServiceException("The id number isn't found");
        }
        logger.info("Deleting user");
        userRepository.deleteById(id);
    }

    /**
     * this method will update the user from the UserDao
     *
     * @param id       required parameter
     * @param userRest will return
     * @return converted saved UserDao to UserRest
     */
    @Override
    public UserRest updateUser(Integer id, UserRest userRest) {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserServiceException("The id number isn't found");
        }
        UserDao dao = userConvertor.toDao(id, userRest);
        UserDao save = userRepository.save(dao);
        return userConvertor.toRest(save);
    }

    /**
     * method will get the regenerated UUID access token by password and username
     *
     * @param username required parameter
     * @param password required parameter
     * @return UUID access token
     */
    @Override
    public String login(String username, String password) {
        Optional<UserDao> user = userRepository.login(username, password);
        if (user.isPresent()) {
            String token = UUID.randomUUID().toString();
            UserDao user1 = user.get();
            user1.setAccessToken(token);
            userRepository.save(user1);
            return token;
        }

        return StringUtils.EMPTY;
    }

    /**
     * this method will find by UUID access token in the UserDao and return new User
     *
     * @param token required parameter
     * @return new User
     */
    @Override
    public Optional<User> findByToken(String token) {
        Optional<UserDao> user = userRepository.findByAccessToken(token);
        if (user.isPresent()) {
            UserDao user1 = user.get();
            User newUser = new User(user1.getUserName(), user1.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(newUser);
        }
        return Optional.empty();
    }

    /**
     * this method will get User personal data
     *
     * @param accessToken required parameter
     * @return personal user data by user access token and personal data id
     */
    @Override
    public PersonalDataRest getUserPersonalDataByAccessToken(String accessToken) {
        Optional<UserDao> userDao = userRepository.findByAccessToken(accessToken);
        if (!userDao.isPresent()) {
            throw new UserServiceException("The id number isn't found");
        }
        return personalDataService.getPersonalDataById(userDao.get().getPersonalData().getId());
    }
}
