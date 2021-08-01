package cz.homeoffice.funtaskproject.repositories;

import cz.homeoffice.funtaskproject.entity.PersonalDataDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalDataDao, Integer> {
}
