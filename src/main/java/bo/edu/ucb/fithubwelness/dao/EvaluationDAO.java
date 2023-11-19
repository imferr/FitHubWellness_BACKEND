package bo.edu.ucb.fithubwelness.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.EvaluationEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;

public interface EvaluationDAO extends JpaRepository<EvaluationEntity, Integer> {
    List<EvaluationEntity> findTop10ByUserIdOrderByDateDesc(UserEntity user);
    List<EvaluationEntity> findAllByUserId(UserEntity user);
}

