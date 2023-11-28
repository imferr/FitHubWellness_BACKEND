package bo.edu.ucb.fithubwelness.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.edu.ucb.fithubwelness.entity.PersonalRecordEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;

public interface PersonalRecordDAO extends JpaRepository<PersonalRecordEntity, Integer> {
    PersonalRecordEntity findByUserId(UserEntity user);
    
    List<PersonalRecordEntity> findByUserIdUserId(int userId);
}
