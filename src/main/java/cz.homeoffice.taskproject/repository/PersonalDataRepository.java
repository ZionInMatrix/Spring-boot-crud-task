package cz.homeoffice.taskproject.repository;

import cz.homeoffice.taskproject.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {

}
