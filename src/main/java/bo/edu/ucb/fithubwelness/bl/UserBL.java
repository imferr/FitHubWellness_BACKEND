package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.UserDAO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserBL {

    private final UserDAO userDAO;
    private final EvaluationBL evaluationBL;

    @Autowired
    public UserBL(UserDAO userDAO, EvaluationBL evaluationBL) {
        this.userDAO = userDAO;
        this.evaluationBL = evaluationBL;
    }

    public UserDTO findOrCreateUser(UserDTO userDTO) {
        Optional<UserEntity> existingUser = userDAO.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            UserEntity userEntity = existingUser.get();
            return new UserDTO(
                userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getBirthday()
            );
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setName(userDTO.getName());
            newUser.setEmail(userDTO.getEmail());
            newUser.setBirthday(userDTO.getBirthday());

            newUser = userDAO.save(newUser);

            return new UserDTO(
                newUser.getUserId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getBirthday()
            );
        }
    }

    public boolean emailExists(String email) {
        Optional<UserEntity> existingUser = userDAO.findByEmail(email);
        return existingUser.isPresent();
    }

    public UserDTO createUserWithEvaluation(UserDTO userDTO, EvaluationDTO evaluationDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setBirthday(userDTO.getBirthday());

        userEntity = userDAO.save(userEntity);

        evaluationDTO.setUserId(new UserDTO(userEntity.getUserId(), userEntity.getName(), userEntity.getEmail(), userEntity.getBirthday()));
        evaluationBL.createEvaluation(evaluationDTO, userEntity);

        return new UserDTO(userEntity.getUserId(), userEntity.getName(), userEntity.getEmail(), userEntity.getBirthday());
    }

    public UserEntity findUserById(int userId) {
        return userDAO.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
