package cz.homeoffice.funtaskproject.convertors;

import cz.homeoffice.funtaskproject.entity.User;
import cz.homeoffice.funtaskproject.rest.models.UserRest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConvertor {

    public User toDao(UserRest rest) {
        User dao = new User();
        dao.setUserName(rest.getUserName());
        dao.setEmail(rest.getEmail());
        dao.setPassword(rest.getPassword());
        dao.setAccessToken(rest.getAccessToken());
        dao.setPersonalData(rest.getPersonalData());
        return dao;
    }

    public UserRest toRest(User dao) {
        UserRest rest = new UserRest();
        rest.setId(dao.getId());
        rest.setUserName(dao.getUserName());
        rest.setEmail(dao.getEmail());
        rest.setPassword(dao.getPassword());
        rest.setAccessToken(dao.getAccessToken());
        rest.setPersonalData(dao.getPersonalData());
        return rest;
    }

    public List<UserRest> toRest(Iterable<User> daos) {
        List<UserRest> users = new ArrayList<>();
        for (User dao : daos) {
            users.add(toRest(dao));
        }
        return users;
    }

    public User toDao(Integer id, UserRest userRest) {
        User dao = toDao(userRest);
        dao.setId(id);
        return dao;
    }
}
