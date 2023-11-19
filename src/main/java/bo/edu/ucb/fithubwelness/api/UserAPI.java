package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.UserBL;
import bo.edu.ucb.fithubwelness.dto.EvaluationDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserAPI {

    private final UserBL userBL;
    private static final Logger LOGGER = Logger.getLogger(UserAPI.class.getName());
    private final ObjectMapper objectMapper;

    @Autowired
    public UserAPI(UserBL userBL, ObjectMapper objectMapper) {
        this.userBL = userBL;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/findOrCreate")
    public ResponseEntity<UserDTO> findOrCreateUser(@RequestBody UserDTO userDTO) {
        LOGGER.info("Iniciando el proceso de creación de usuario");
        try {
            boolean emailExists = userBL.emailExists(userDTO.getEmail());
            if (!emailExists) {
                LOGGER.info("Correo no encontrado en la base de datos. Redirigiendo a la página de first evaluation.");
                return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/firstevaluation").build();
            }
            UserDTO user = userBL.findOrCreateUser(userDTO);
            LOGGER.info("Usuario creado exitosamente");
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al crear el usuario");
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finalizando el proceso de creación de usuario");
        }
    }

    @PostMapping("/createWithEvaluation")
    public ResponseEntity<UserDTO> createUserWithEvaluation(@RequestBody Map<String, Object> requestData) {
        LOGGER.info("Iniciando el proceso de creación de usuario con evaluación");
        try {
            UserDTO userDTO = objectMapper.convertValue(requestData.get("user"), UserDTO.class);
            EvaluationDTO evaluationDTO = objectMapper.convertValue(requestData.get("evaluation"), EvaluationDTO.class);
            UserDTO createdUser = userBL.createUserWithEvaluation(userDTO, evaluationDTO);
            LOGGER.info("Usuario creado exitosamente");
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al crear el usuario" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
