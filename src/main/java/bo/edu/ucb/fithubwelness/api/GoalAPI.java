package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.GoalBL;
import bo.edu.ucb.fithubwelness.bl.TypeGoalBL;
import bo.edu.ucb.fithubwelness.bl.UserBL;
import bo.edu.ucb.fithubwelness.dto.GoalDTO;
import bo.edu.ucb.fithubwelness.dto.TypeGoalDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.TypeGoalEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/goal")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GoalAPI {

    private final GoalBL goalBL;
    private final UserBL userBL;
    private final TypeGoalBL typeGoalBL;
    private static final Logger LOGGER = Logger.getLogger(GoalAPI.class.getName());

    @Autowired
    public GoalAPI(GoalBL goalBL, UserBL userBL, TypeGoalBL typeGoalBL) {
        this.goalBL = goalBL;
        this.userBL = userBL;
        this.typeGoalBL = typeGoalBL;
    }

    @PostMapping("/create/user/{userId}/typeGoal/{typeGoalId}")
    public ResponseEntity<GoalDTO> createGoal(@PathVariable int userId, @PathVariable int typeGoalId,
            @RequestBody GoalDTO goalDTO, HttpServletRequest request) {
        LOGGER.info("Iniciando el proceso de creación de objetivo");
        try {
            UserEntity user = userBL.findUserById(userId);
            TypeGoalEntity typeGoal = typeGoalBL.findTypeGoalById(typeGoalId);
            goalDTO.setUserId(new UserDTO(user.getUserId(), user.getName(), user.getEmail(), user.getBirthday()));
            goalDTO.setTypeGoalId(new TypeGoalDTO(typeGoal.getTypeGoalId(), typeGoal.getTypeGoal()));
            GoalDTO createdGoal = goalBL.createGoal(goalDTO, user, typeGoal);
            LOGGER.info("Objetivo creado con éxito");
            return ResponseEntity.ok(createdGoal);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al crear el objetivo: " + e.getMessage() + e.getCause() + e.getClass());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finalizando el proceso de creación de objetivo");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> findGoalsByUserId(@PathVariable int userId) {
        LOGGER.info("Iniciando el proceso de búsqueda de objetivos por id de usuario");
        try {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("goals", goalBL.findGoalsByUserId(userId));
            LOGGER.info("Objetivos encontrados con éxito");
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al buscar los objetivos: " + e.getMessage() + e.getCause() + e.getClass());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finalizando el proceso de búsqueda de objetivos por id de usuario");
        }
    }
}