package bo.edu.ucb.fithubwelness.api;

import bo.edu.ucb.fithubwelness.bl.GoalBL;
import bo.edu.ucb.fithubwelness.dto.GoalDTO;
import bo.edu.ucb.fithubwelness.dto.TypeGoalDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/goal")
public class GoalAPI {

    private final GoalBL goalBL;

    @Autowired
    public GoalAPI(GoalBL goalBL) {
        this.goalBL = goalBL;
    }

    @PostMapping("/create")
    public ResponseEntity<GoalDTO> createGoal(@RequestBody GoalDTO goalDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(goalDTO.getUser().getUserId());
        goalDTO.setUser(userDTO);

        TypeGoalDTO typeGoalDTO = new TypeGoalDTO();
        typeGoalDTO.setTypeGoalId(goalDTO.getTypeGoal().getTypeGoalId());
        goalDTO.setTypeGoal(typeGoalDTO);

        GoalDTO createdGoal = goalBL.createGoal(goalDTO);
        return ResponseEntity.ok(createdGoal);
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<GoalDTO> getGoalById(@PathVariable int goalId) {
        GoalDTO goalDTO = goalBL.getGoalById(goalId);
        return ResponseEntity.ok(goalDTO);
    }
}
