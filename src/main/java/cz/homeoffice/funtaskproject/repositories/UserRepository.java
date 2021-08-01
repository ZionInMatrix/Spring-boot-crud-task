package cz.homeoffice.funtaskproject.repositories;

import cz.homeoffice.funtaskproject.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDao, Integer> {

    @Query(value = "SELECT u FROM UserDao u where u.userName = ?1 and u.password = ?2")
    Optional<UserDao> login(String username, String password);

    Optional<UserDao> findByAccessToken(String accessToken);
}
