package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.UserDAO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBL {

    private final UserDAO userDAO;

    @Autowired
    public UserBL(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDTO findOrCreateUser(UserDTO userDTO) {
        Optional<UserEntity> existingUser = userDAO.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            return convertToDTO(existingUser.get());
        } else {
            UserEntity newUser = convertToEntity(userDTO);
            newUser = userDAO.save(newUser);
            return convertToDTO(newUser);
        }
    }

    private UserDTO convertToDTO(UserEntity userEntity) {
        return new UserDTO(userEntity.getUserId(), userEntity.getName(), userEntity.getEmail(), userEntity.getBirthday());
    }

    private UserEntity convertToEntity(UserDTO userDTO) {
        return new UserEntity(userDTO.getUserId(), userDTO.getName(), userDTO.getEmail(), userDTO.getBirthday());
    }
}
