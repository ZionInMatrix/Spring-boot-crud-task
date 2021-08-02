package cz.homeoffice.funtaskproject.services.implementations;

import cz.homeoffice.funtaskproject.convertors.UserConvertor;
import cz.homeoffice.funtaskproject.entity.PersonalDataDao;
import cz.homeoffice.funtaskproject.entity.UserDao;
import cz.homeoffice.funtaskproject.repositories.UserRepository;
import cz.homeoffice.funtaskproject.rest.models.UserRest;
import cz.homeoffice.funtaskproject.services.exceptions.UserServiceException;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

@RunWith(EasyMockRunner.class)
public class UserServiceImplTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConvertor userConvertor;

    @TestSubject
    private final UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void addUser() {
        PersonalDataDao personalDataDao = new PersonalDataDao();
        personalDataDao.setAddress("Plzenska 1311, 150 00, Praha 5");
        personalDataDao.setDateOfBirthday("11.1.1981");
        personalDataDao.setDateOfCreation(LocalDate.now());
        personalDataDao.setPhoneNumber("+420 777 777 777");

        UserRest rest = new UserRest();
        rest.setUserName("Jana");
        rest.setEmail("bomba@bubu.cz");
        rest.setAccessToken(UUID.randomUUID().toString());
        rest.setPassword("123");
        rest.setPersonalData(personalDataDao);

        UserDao dao = new UserDao();
        dao.setUserName("Jana");
        dao.setEmail("bomba@bubu.cz");
        dao.setAccessToken(UUID.randomUUID().toString());
        dao.setPassword("123");
        dao.setPersonalData(personalDataDao);

        expect(userConvertor.toDao(rest)).andReturn(dao);
        expect(userRepository.save(dao)).andReturn(dao);
        expect(userConvertor.toRest(dao)).andReturn(rest);

        replay(userConvertor);
        replay(userRepository);

        UserRest userRest = userService.addUser(rest);

        verify(userConvertor);
        verify(userRepository);

        assertEquals(dao.getUserName(), userRest.getUserName());
        assertNotEquals(dao.getAccessToken(), userRest.getAccessToken());
        assertEquals(dao.getEmail(), userRest.getEmail());
        assertEquals(dao.getPassword(), userRest.getPassword());
        assertEquals(dao.getPersonalData(), userRest.getPersonalData());
    }

    @Test
    public void getUsers() {
        PersonalDataDao personalDataDao = new PersonalDataDao();
        personalDataDao.setAddress("Plzenska 1311, 150 00, Praha 5");
        personalDataDao.setDateOfBirthday("11.1.1981");
        personalDataDao.setDateOfCreation(LocalDate.now());
        personalDataDao.setPhoneNumber("+420 777 777 777");

        UserDao dao = new UserDao();
        dao.setUserName("Jana");
        dao.setEmail("bomba@bubu.cz");
        dao.setAccessToken(UUID.randomUUID().toString());
        dao.setPassword("123");
        dao.setPersonalData(personalDataDao);

        List<UserDao> userDaoList = new ArrayList<>();
        userDaoList.add(dao);
        userDaoList.add(dao);

        UserRest rest = new UserRest();
        rest.setUserName("Jana");
        rest.setEmail("bomba@bubu.cz");
        rest.setAccessToken(UUID.randomUUID().toString());
        rest.setPassword("123");
        rest.setPersonalData(personalDataDao);

        List<UserRest> userRestList = new ArrayList<>();
        userRestList.add(rest);
        userRestList.add(rest);

        expect(userConvertor.toRest(userDaoList)).andReturn(userRestList);
        expect(userRepository.findAll()).andReturn(userDaoList);

        replay(userConvertor);
        replay(userRepository);

        List<UserRest> users = userService.getUsers();

        verify(userConvertor);
        verify(userRepository);

        assertEquals(2, users.size());
        assertEquals(userDaoList.size(), users.size());

        assertTrue(users.size() > 0);

        for (int i = 0; i < userDaoList.size(); i++) {
            assertEquals(users.get(i).getUserName(), userDaoList.get(i).getUserName());
            assertEquals(users.get(i).getEmail(), userDaoList.get(i).getEmail());
            assertEquals(users.get(i).getPassword(), userDaoList.get(i).getPassword());
            assertNotEquals(users.get(i).getAccessToken(), userDaoList.get(i).getAccessToken());
            assertEquals(users.get(i).getPersonalData(), userDaoList.get(i).getPersonalData());
        }


    }

    @Test
    public void deleteUserById() {
        PersonalDataDao personalDataDao = new PersonalDataDao();
        personalDataDao.setAddress("Plzenska 1311, 150 00, Praha 5");
        personalDataDao.setDateOfBirthday("11.1.1981");
        personalDataDao.setDateOfCreation(LocalDate.now());
        personalDataDao.setPhoneNumber("+420 777 777 777");

        UserDao dao = new UserDao();
        dao.setUserName("Jana");
        dao.setEmail("bomba@bubu.cz");
        dao.setAccessToken(UUID.randomUUID().toString());
        dao.setPassword("123");
        dao.setPersonalData(personalDataDao);

        expect(userRepository.findById(anyInt())).andReturn(Optional.of(dao));

        userRepository.deleteById(anyInt());
        expectLastCall();

        replay(userRepository);

        userService.deleteUserById(1);

        verify(userRepository);

    }

    @Test
    public void should_throw_exception_when_deleteUserDaoById() {
        Optional<UserDao> userDaoById = Optional.empty();

        expect(userRepository.findById(anyInt())).andReturn(userDaoById);
        expectedEx.expect(UserServiceException.class);
        expectedEx.expectMessage("The id number isn't found");

        replay(userRepository);

        userService.deleteUserById(anyInt());
    }

    @Test
    public void updateUser() {
        PersonalDataDao personalDataDao = new PersonalDataDao();
        personalDataDao.setAddress("Plzenska 1311, 150 00, Praha 5");
        personalDataDao.setDateOfBirthday("11.1.1981");
        personalDataDao.setDateOfCreation(LocalDate.now());
        personalDataDao.setPhoneNumber("+420 777 777 777");

        UserDao dao = new UserDao();
        dao.setId(1);
        dao.setUserName("Jana");
        dao.setEmail("bomba@bubu.cz");
        dao.setAccessToken(UUID.randomUUID().toString());
        dao.setPassword("123");
        dao.setPersonalData(personalDataDao);

        UserRest rest = new UserRest();
        rest.setId(1);
        rest.setUserName("Jana");
        rest.setEmail("bomba@bubu.cz");
        rest.setAccessToken(UUID.randomUUID().toString());
        rest.setPassword("123");
        rest.setPersonalData(personalDataDao);

        UserDao dao1 = new UserDao();
        dao1.setId(1);
        dao1.setUserName("Jana");
        dao1.setEmail("bomba@bubu.cz");
        dao1.setAccessToken(UUID.randomUUID().toString());
        dao1.setPassword("123");
        dao1.setPersonalData(personalDataDao);

        UserRest rest1 = new UserRest();
        rest1.setId(1);
        rest1.setUserName("Jana");
        rest1.setEmail("bomba@bubu.cz");
        rest1.setAccessToken(UUID.randomUUID().toString());
        rest1.setPassword("123");
        rest1.setPersonalData(personalDataDao);

        expect(userRepository.findById(1)).andReturn(Optional.of(dao));
        expect(userConvertor.toDao(1, rest)).andReturn(dao);
        expect(userRepository.save(dao)).andReturn(dao1);
        expect(userConvertor.toRest(dao1)).andReturn(rest1);

        replay(userConvertor);
        replay(userRepository);

        UserRest userRest = userService.updateUser(1, rest);

        verify(userRepository);
        verify(userConvertor);

        assertEquals(dao.getId(), userRest.getId());
        assertEquals(dao.getUserName(), userRest.getUserName());
        assertNotEquals(dao.getAccessToken(), userRest.getAccessToken());
        assertEquals(dao.getEmail(), userRest.getEmail());
        assertEquals(dao.getPassword(), userRest.getPassword());
        assertEquals(dao.getPersonalData(), userRest.getPersonalData());

    }

    @Test
    public void login() {
        PersonalDataDao personalDataDao = new PersonalDataDao();
        personalDataDao.setId(1);
        personalDataDao.setAddress("Plzenska 1311, 150 00, Praha 5");
        personalDataDao.setDateOfBirthday("11.1.1981");
        personalDataDao.setDateOfCreation(LocalDate.now());
        personalDataDao.setPhoneNumber("+420 777 777 777");

        UserDao dao = new UserDao();
        dao.setId(1);
        dao.setUserName("Jana");
        dao.setEmail("bomba@bubu.cz");
        dao.setAccessToken(UUID.randomUUID().toString());
        dao.setPassword("123");
        dao.setPersonalData(personalDataDao);

        expect(userRepository.login(anyString(), anyString())).andReturn(Optional.of(dao));
        expect(userRepository.save(dao)).andReturn(dao);

        replay(userRepository);

        userService.login(anyString(), anyString());

        verify(userRepository);
    }

    @Test
    public void findByToken() {
        PersonalDataDao personalDataDao = new PersonalDataDao();
        personalDataDao.setAddress("Plzenska 1311, 150 00, Praha 5");
        personalDataDao.setDateOfBirthday("11.1.1981");
        personalDataDao.setDateOfCreation(LocalDate.now());
        personalDataDao.setPhoneNumber("+420 777 777 777");

        UserDao dao = new UserDao();
        dao.setUserName("Jana");
        dao.setEmail("bomba@bubu.cz");
        dao.setAccessToken(UUID.randomUUID().toString());
        dao.setPassword("123");
        dao.setPersonalData(personalDataDao);

        expect(userRepository.findByAccessToken(anyObject())).andReturn(Optional.of(dao));

        replay(userRepository);

        userService.findByToken(anyObject());

        verify(userRepository);

    }
}