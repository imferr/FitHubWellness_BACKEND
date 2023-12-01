package bo.edu.ucb.fithubwelness.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.edu.ucb.fithubwelness.entity.ChatGptResponseEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;

public interface ChatGptResponseDAO extends JpaRepository<ChatGptResponseEntity, Integer> {
    List<ChatGptResponseEntity> findByUserId(UserEntity user);
}
