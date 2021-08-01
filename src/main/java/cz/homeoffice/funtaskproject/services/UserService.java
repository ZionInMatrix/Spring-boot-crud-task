package cz.homeoffice.funtaskproject.services;

import cz.homeoffice.funtaskproject.rest.models.PersonalDataRest;
import cz.homeoffice.funtaskproject.rest.models.UserRest;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserRest addUser(UserRest customerRest);

    List<UserRest> getUsers();

    void deleteUserById(Integer id);

    UserRest updateUser(Integer id, UserRest userRest);

    String login(String username, String password);

    Optional<User> findByToken(String token);

    PersonalDataRest getUserPersonalDataByAccessToken(String accessToken);
}
