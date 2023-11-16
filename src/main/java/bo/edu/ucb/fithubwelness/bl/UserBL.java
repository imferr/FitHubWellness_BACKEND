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
            UserDTO dto = convertToDTO(existingUser.get());
            dto.setNewUser(false);
            return dto;
        } else {
            UserEntity newUser = convertToEntity(userDTO);
            newUser = userDAO.save(newUser);
            UserDTO dto = convertToDTO(newUser);
            dto.setNewUser(true);
            return dto;
        }
    }

    private UserDTO convertToDTO(UserEntity userEntity) {
        // Asumimos que este método siempre se llama para usuarios existentes, por lo que isNewUser es false.
        return new UserDTO(
            userEntity.getUserId(), 
            userEntity.getName(), 
            userEntity.getEmail(), 
            userEntity.getBirthday(), 
            false // Asumimos que el usuario no es nuevo porque ya está siendo convertido de una entidad
        );
    }    

    private UserEntity convertToEntity(UserDTO userDTO) {
        return new UserEntity(userDTO.getUserId(), userDTO.getName(), userDTO.getEmail(), userDTO.getBirthday());
    }
}
