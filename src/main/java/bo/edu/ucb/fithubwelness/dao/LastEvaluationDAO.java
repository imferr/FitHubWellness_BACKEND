package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.edu.ucb.fithubwelness.entity.LastEvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;

public interface LastEvaluationDAO extends JpaRepository<LastEvaluationEntity, Integer> {
    LastEvaluationEntity findByUserId(UserEntity user);
}

