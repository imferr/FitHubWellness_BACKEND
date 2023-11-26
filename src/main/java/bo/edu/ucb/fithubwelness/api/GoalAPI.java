package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.GoalBL;
import bo.edu.ucb.fithubwelness.dto.GoalDTO;

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
    private static final Logger LOGGER = Logger.getLogger(GoalAPI.class.getName());

    @Autowired
    public GoalAPI(GoalBL goalBL) {
        this.goalBL = goalBL;
    }

    @PostMapping("/create")
    public ResponseEntity<GoalDTO> createGoal(@RequestBody GoalDTO goalDTO) {
        LOGGER.info("Iniciando el proceso de creación de objetivo");
        try {
            GoalDTO createdGoalDTO = goalBL.createGoal(goalDTO);
            LOGGER.info("Objetivo creado con éxito");
            return new ResponseEntity<>(createdGoalDTO, HttpStatus.CREATED);
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
