package bo.edu.ucb.fithubwelness.bl;

//import bo.edu.ucb.fithubwelness.dao.ExerciseDAO;
import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
//import bo.edu.ucb.fithubwelness.entity.ExerciseEntity;
import bo.edu.ucb.fithubwelness.service.WgerService;
//import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExerciseBL {

    private final WgerService wgerService;
    //private ExerciseDAO exerciseDAO;

    @Autowired
    public ExerciseBL(WgerService wgerService /*ExerciseDAO exerciseDAO*/) {
        this.wgerService = wgerService;
        //this.exerciseDAO = exerciseDAO;
    }
/*
    @PostConstruct
    public void init() {
        if (exerciseDAO.count() == 0) {
            insertInitialData();
        }
    }

    private void insertInitialData() {
        List<ExerciseDTO> exercisesFromAPI = wgerService.getAllExercises();

        for (ExerciseDTO exerciseDTO : exercisesFromAPI) {
            ExerciseEntity exerciseEntity = new ExerciseEntity();
            exerciseEntity.setName(exerciseDTO.getName());
            exerciseEntity.setDescription(exerciseDTO.getDescription());
            exerciseEntity.setLinkPicture(exerciseDTO.getLinkPicture());
            exerciseDAO.save(exerciseEntity);
        }
    }
*/
    public List<ExerciseDTO> findAllExercises() {
        return wgerService.getAllExercises();
    }
}
