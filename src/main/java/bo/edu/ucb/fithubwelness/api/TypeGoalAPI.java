package bo.edu.ucb.fithubwelness.api;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.edu.ucb.fithubwelness.bl.TypeGoalBL;
import bo.edu.ucb.fithubwelness.entity.TypeGoalEntity;

@RestController
@RequestMapping("/api/v1/typegoal")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TypeGoalAPI {
    
    private final TypeGoalBL typeGoalBL;
    private static final Logger LOGGER = Logger.getLogger(TypeGoalAPI.class.getName());

    @Autowired
    public TypeGoalAPI(TypeGoalBL typeGoalBL) {
        this.typeGoalBL = typeGoalBL;
    }

    @GetMapping()
    public ResponseEntity<List<TypeGoalEntity>> getAllTypeGoal() {
        LOGGER.info("Iniciando el proceso de obtener todos los tipos de metas");
        try {
            List<TypeGoalEntity> typeGoals = typeGoalBL.getAllTypeGoal();
            return ResponseEntity.ok(typeGoals);
        } catch (Exception e) {
            LOGGER.info("Ocurrió un error al obtener los tipos de metas");
            return ResponseEntity.badRequest().build();
        } finally {
            LOGGER.info("Finalizando el proceso de obtener todos los tipos de metas");
        }
    }
}
