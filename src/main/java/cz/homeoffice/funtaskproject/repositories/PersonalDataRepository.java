package cz.homeoffice.funtaskproject.repositories;

import cz.homeoffice.funtaskproject.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
}
