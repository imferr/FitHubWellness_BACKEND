package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import bo.edu.ucb.fithubwelness.service.WgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExerciseBL {

    private final WgerService wgerService;

    @Autowired
    public ExerciseBL(WgerService wgerService) {
        this.wgerService = wgerService;
    }

    public List<ExerciseDTO> findAllExercises() {
        return wgerService.getAllExercises();
    }
}
