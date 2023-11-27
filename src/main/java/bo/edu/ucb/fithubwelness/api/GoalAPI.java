package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.GoalBL;
import bo.edu.ucb.fithubwelness.bl.TypeGoalBL;
import bo.edu.ucb.fithubwelness.bl.UserBL;
import bo.edu.ucb.fithubwelness.dto.GoalDTO;
import bo.edu.ucb.fithubwelness.dto.TypeGoalDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.TypeGoalEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;

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

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createGoal(@RequestBody Map<String, Object> requestBody) {
        LOGGER.info("Iniciando el proceso de creación de objetivo");
        try {
            GoalDTO goalDTO = new GoalDTO();
            goalDTO.setQuantity((Double) requestBody.get("quantity"));
            UserEntity user = userBL.findUserById(((Integer) requestBody.get("userId")));
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setName(user.getName());
            TypeGoalEntity typeGoalEntity = typeGoalBL.findTypeGoalById(((Integer) requestBody.get("typeGoalId")));
            TypeGoalDTO typeGoalDTO = new TypeGoalDTO();
            typeGoalDTO.setTypeGoalId(typeGoalEntity.getTypeGoalId());
            goalDTO.setUserId(userDTO);
            goalDTO.setTypeGoalId(typeGoalDTO);
            goalDTO.setExerciseName((String) requestBody.get("exerciseName"));
            GoalDTO createdGoalDTO = goalBL.createGoal(goalDTO);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("goalId", createdGoalDTO.getGoalId());
            responseMap.put("accomplished", createdGoalDTO.getAccomplished());
            responseMap.put("quantity", createdGoalDTO.getQuantity());
            responseMap.put("exerciseName", createdGoalDTO.getExerciseName());
            responseMap.put("typeGoalId", createdGoalDTO.getTypeGoalId());
            LOGGER.info("Objetivo creado con éxito");
            return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al crear el objetivo: " + e.getMessage() + e.getCause() + e.getClass());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finalizando el proceso de creación de objetivo");
        }
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<GoalDTO> getGoalById(@PathVariable int goalId) {
        LOGGER.info("Iniciando el proceso de obtener objetivo por id");
        try {
            GoalDTO goalDTO = goalBL.getGoalById(goalId);
            LOGGER.info("Objetivo obtenido con éxito");
            return new ResponseEntity<>(goalDTO, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al obtener el objetivo: " + e.getMessage() + e.getCause() + e.getClass());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.info("Finalizando el proceso de obtener objetivo por id");
        }
    }
}