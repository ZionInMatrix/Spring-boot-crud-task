package cz.homeoffice.taskproject.repository;

import cz.homeoffice.taskproject.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData findByUsername(String name);
}
