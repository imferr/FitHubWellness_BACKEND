package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.PersonalRecordEntity;

public interface PersonalRecordDAO extends JpaRepository<PersonalRecordEntity, Integer> {
    
}
