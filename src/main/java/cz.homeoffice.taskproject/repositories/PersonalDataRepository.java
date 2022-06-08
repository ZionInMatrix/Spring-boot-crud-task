package cz.homeoffice.taskproject.repositories;

import cz.homeoffice.taskproject.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {

}
