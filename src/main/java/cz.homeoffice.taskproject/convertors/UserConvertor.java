package cz.homeoffice.taskproject.convertors;


import cz.homeoffice.funtaskproject.rest.models.UserRest;
import cz.homeoffice.taskproject.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

    public User toDao(UserRest rest) {
        User dao = new User();
        dao.setUserName(rest.getUserName());
        dao.setEmail(rest.getEmail());
        dao.setPassword(rest.getPassword());
        dao.setAccessToken(rest.getAccessToken());
        return dao;
    }

    public UserRest toRest(User dao) {
        UserRest rest = new UserRest();
        rest.setId(dao.getId());
        rest.setUserName(dao.getUserName());
        rest.setEmail(dao.getEmail());
        rest.setPassword(dao.getPassword());
        rest.setAccessToken(dao.getAccessToken());
        return rest;
    }
}
