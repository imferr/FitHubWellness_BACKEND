package bo.edu.ucb.fithubwelness.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.EvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;

public interface EvaluationDAO extends JpaRepository<EvaluationEntity, Integer> {
    EvaluationEntity findByUserId(UserEntity user);
}

