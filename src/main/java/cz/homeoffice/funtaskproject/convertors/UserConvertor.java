package cz.homeoffice.funtaskproject.convertors;

import cz.homeoffice.funtaskproject.entity.UserDao;
import cz.homeoffice.funtaskproject.rest.models.UserRest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConvertor {

    public UserDao toDao(UserRest rest) {
        UserDao dao = new UserDao();
        dao.setUserName(rest.getUserName());
        dao.setEmail(rest.getEmail());
        dao.setPassword(rest.getPassword());
        dao.setAccessToken(rest.getAccessToken());
        dao.setPersonalData(rest.getPersonalData());
        return dao;
    }

    public UserRest toRest(UserDao dao) {
        UserRest rest = new UserRest();
        rest.setId(dao.getId());
        rest.setUserName(dao.getUserName());
        rest.setEmail(dao.getEmail());
        rest.setPassword(dao.getPassword());
        rest.setAccessToken(dao.getAccessToken());
        rest.setPersonalData(dao.getPersonalData());
        return rest;
    }

    public List<UserRest> toRest(Iterable<UserDao> daos) {
        List<UserRest> users = new ArrayList<>();
        for (UserDao dao : daos) {
            users.add(toRest(dao));
        }
        return users;
    }

    public UserDao toDao(Integer id, UserRest userRest) {
        UserDao dao = toDao(userRest);
        dao.setId(id);
        return dao;
    }
}
