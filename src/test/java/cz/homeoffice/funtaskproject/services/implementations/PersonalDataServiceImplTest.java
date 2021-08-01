package cz.homeoffice.funtaskproject.services.implementations;

import cz.homeoffice.funtaskproject.convertors.PersonalDataConvertor;
import cz.homeoffice.funtaskproject.entity.PersonalDataDao;
import cz.homeoffice.funtaskproject.repositories.PersonalDataRepository;
import cz.homeoffice.funtaskproject.rest.models.PersonalDataRest;
import cz.homeoffice.funtaskproject.services.exceptions.PersonalDataServiceException;
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

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(EasyMockRunner.class)
public class PersonalDataServiceImplTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private PersonalDataRepository personalDataRepository;

    @Mock
    private PersonalDataConvertor personalDataConvertor;

    @TestSubject
    private final PersonalDataServiceImpl personalDataService = new PersonalDataServiceImpl();

    @Test
    public void addPersonalData() {
        PersonalDataRest rest = new PersonalDataRest();
        rest.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
        rest.setDateOfBirthday("11.1.1981");
        rest.setDateOfCreation(LocalDate.now());
        rest.setPhoneNumber("+420 777 777 777");

        PersonalDataDao dao = new PersonalDataDao();
        dao.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
        dao.setDateOfBirthday("11.1.1981");
        dao.setDateOfCreation(LocalDate.now());
        dao.setPhoneNumber("+420 777 777 777");

        expect(personalDataConvertor.toDao(rest)).andReturn(dao);
        expect(personalDataRepository.save(dao)).andReturn(dao);
        expect(personalDataConvertor.toRest(dao)).andReturn(rest);

        replay(personalDataConvertor);
        replay(personalDataRepository);

        PersonalDataRest personalDataRest = personalDataService.addPersonalData(rest);

        verify(personalDataConvertor);
        verify(personalDataRepository);

        assertEquals(dao.getAddress(), personalDataRest.getAddress());
        assertEquals(dao.getDateOfBirthday(), personalDataRest.getDateOfBirthday());
        assertEquals(dao.getPhoneNumber(), personalDataRest.getPhoneNumber());
        assertEquals(dao.getDateOfCreation(), personalDataRest.getDateOfCreation());
    }

    @Test
    public void getAllPersonalData() {
        PersonalDataDao dao = new PersonalDataDao();
        dao.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
        dao.setDateOfBirthday("11.1.1981");
        dao.setDateOfCreation(LocalDate.now());
        dao.setPhoneNumber("+420 777 777 777");

        List<PersonalDataDao> personalDataDaoList = new ArrayList<>();
        personalDataDaoList.add(dao);
        personalDataDaoList.add(dao);

        PersonalDataRest rest = new PersonalDataRest();
        rest.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
        rest.setDateOfBirthday("11.1.1981");
        rest.setDateOfCreation(LocalDate.now());
        rest.setPhoneNumber("+420 777 777 777");

        List<PersonalDataRest> personalDataRestList = new ArrayList<>();
        personalDataRestList.add(rest);
        personalDataRestList.add(rest);

        expect(personalDataConvertor.toRest(personalDataDaoList)).andReturn(personalDataRestList);
        expect(personalDataRepository.findAll()).andReturn(personalDataDaoList);

        replay(personalDataConvertor);
        replay(personalDataRepository);

        List<PersonalDataRest> allPersonalData = personalDataService.getAllPersonalData();

        verify(personalDataConvertor);
        verify(personalDataRepository);

        assertEquals(2, allPersonalData.size());
        assertEquals(allPersonalData.size(), personalDataDaoList.size());

        assertTrue(allPersonalData.size() > 0);

        for (int i = 0; i < allPersonalData.size(); i++) {
            assertEquals(personalDataDaoList.get(i).getPhoneNumber(), allPersonalData.get(i).getPhoneNumber());
            assertEquals(personalDataDaoList.get(i).getAddress(), allPersonalData.get(i).getAddress());
            assertEquals(personalDataDaoList.get(i).getDateOfBirthday(), allPersonalData.get(i).getDateOfBirthday());
            assertEquals(personalDataDaoList.get(i).getDateOfBirthday(), allPersonalData.get(i).getDateOfBirthday());
        }
    }

    @Test
    public void getPersonalDataById() {
        PersonalDataDao dao = new PersonalDataDao();
        dao.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
        dao.setDateOfBirthday("11.1.1981");
        dao.setDateOfCreation(LocalDate.now());
        dao.setPhoneNumber("+420 777 777 777");

        PersonalDataRest rest = new PersonalDataRest();
        rest.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
        rest.setDateOfBirthday("11.1.1981");
        rest.setDateOfCreation(LocalDate.now());
        rest.setPhoneNumber("+420 777 777 777");

        expect(personalDataRepository.findById(anyInt())).andReturn(Optional.of(dao));
        expect(personalDataConvertor.toRest(dao)).andReturn(rest);

        replay(personalDataRepository);
        replay(personalDataConvertor);

        PersonalDataRest personalDataById = personalDataService.getPersonalDataById(anyInt());

        verify(personalDataRepository);
        verify(personalDataConvertor);

        assertEquals(dao.getAddress(), personalDataById.getAddress());
        assertEquals(dao.getDateOfBirthday(), personalDataById.getDateOfBirthday());
        assertEquals(dao.getPhoneNumber(), personalDataById.getPhoneNumber());
        assertEquals(dao.getDateOfCreation(), personalDataById.getDateOfCreation());
    }

    @Test
    public void should_throw_exception_when_getPersonalDataById() {
        Optional<PersonalDataDao> personalDataById = Optional.empty();

        expect(personalDataRepository.findById(anyInt())).andReturn(personalDataById);
        expectedEx.expect(PersonalDataServiceException.class);
        expectedEx.expectMessage("The id number isn't found");

        replay(personalDataRepository);

        personalDataService.getPersonalDataById(anyInt());
    }

    @Test
    public void deletePersonalDataById() {
        PersonalDataDao dao = new PersonalDataDao();
        dao.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
        dao.setDateOfBirthday("11.1.1981");
        dao.setDateOfCreation(LocalDate.now());
        dao.setPhoneNumber("+420 777 777 777");

        expect(personalDataRepository.findById(anyInt())).andReturn(Optional.of(dao));

        personalDataRepository.deleteById(anyInt());
        expectLastCall();

        replay(personalDataRepository);

        personalDataService.deletePersonalDataById(1);

        verify(personalDataRepository);
    }

    @Test
    public void should_throw_exception_when_deletePersonalDataById() {
        Optional<PersonalDataDao> personalDataById = Optional.empty();

        expect(personalDataRepository.findById(anyInt())).andReturn(personalDataById);
        expectedEx.expect(PersonalDataServiceException.class);
        expectedEx.expectMessage("The id number isn't found");

        replay(personalDataRepository);

        personalDataService.deletePersonalDataById(anyInt());
    }

    @Test
    public void updatePersonalDataById() {
        PersonalDataDao dao = new PersonalDataDao();
        dao.setId(1);
        dao.setAddress("Musilkova 1311/5b, 150 00, Praha 5");
        dao.setDateOfBirthday("11.1.1981");
        dao.setDateOfCreation(LocalDate.now());
        dao.setPhoneNumber("+420 777 777 777");

        PersonalDataRest rest = new PersonalDataRest();
        rest.setId(1);
        rest.setAddress("Plzenska 1311/5b, 150 00, Praha 5");
        rest.setDateOfBirthday("12.2.1981");
        rest.setDateOfCreation(LocalDate.now());
        rest.setPhoneNumber("+420 999 777 777");

        PersonalDataDao dao1 = new PersonalDataDao();
        dao.setId(1);
        dao.setAddress("Plzenska 1311/5b, 150 00, Praha 5");
        dao.setDateOfBirthday("12.2.1981");
        dao.setDateOfCreation(LocalDate.now());
        dao.setPhoneNumber("+420 999 777 777");

        PersonalDataRest rest1 = new PersonalDataRest();
        rest.setId(1);
        rest.setAddress("Plzenska 1311/5b, 150 00, Praha 5");
        rest.setDateOfBirthday("12.2.1981");
        rest.setDateOfCreation(LocalDate.now());
        rest.setPhoneNumber("+420 999 777 777");


        expect(personalDataRepository.findById(1)).andReturn(Optional.of(dao));
        expect(personalDataConvertor.toDao(1, rest)).andReturn(dao);
        expect(personalDataRepository.save(dao)).andReturn(dao1);
        expect(personalDataConvertor.toRest(dao1)).andReturn(rest1);

        replay(personalDataConvertor);
        replay(personalDataRepository);

        PersonalDataRest personalDataRest = personalDataService.updatePersonalDataById(1, rest);

        verify(personalDataRepository);
        verify(personalDataConvertor);

        assertEquals(dao1.getId(), personalDataRest.getId());
        assertEquals(dao1.getAddress(), personalDataRest.getAddress());
        assertEquals(dao1.getDateOfBirthday(), personalDataRest.getDateOfBirthday());
        assertEquals(dao1.getPhoneNumber(), personalDataRest.getPhoneNumber());
        assertEquals(dao1.getDateOfCreation(), personalDataRest.getDateOfCreation());
    }
}