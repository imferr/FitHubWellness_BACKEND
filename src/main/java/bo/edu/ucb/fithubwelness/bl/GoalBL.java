package bo.edu.ucb.fithubwelness.bl;

import bo.edu.ucb.fithubwelness.dao.GoalDAO;
import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import bo.edu.ucb.fithubwelness.dto.GoalDTO;
import bo.edu.ucb.fithubwelness.dto.PersonalRecordDTO;
import bo.edu.ucb.fithubwelness.dto.TypeGoalDTO;
import bo.edu.ucb.fithubwelness.dto.UserDTO;
import bo.edu.ucb.fithubwelness.entity.GoalEntity;
import bo.edu.ucb.fithubwelness.entity.TypeGoalEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoalBL {
    private final GoalDAO goalDAO;
    private final ExerciseBL exerciseBL;

    @Autowired
    public GoalBL(GoalDAO goalDAO, ExerciseBL exerciseBL) {
        this.goalDAO = goalDAO;
        this.exerciseBL = exerciseBL;
    }

    public GoalDTO createGoal(GoalDTO goalDTO, UserEntity user, TypeGoalEntity typeGoal) {
        if (goalExists(goalDTO)) { // si el objetivo ya existe
            throw new RuntimeException("Un objetivo con los mismos parámetros ya existe.");
        }
        goalDTO.setAccomplished(false); // por defecto, el objetivo no está cumplido
        if (goalDTO.getTypeGoalId().getTypeGoalId() == 1 || goalDTO.getTypeGoalId().getTypeGoalId() == 2) {
            List<ExerciseDTO> exercises = exerciseBL.findExercisesByName(goalDTO.getExerciseName()); // busca el
                                                                                                     // ejercicio por
                                                                                                     // nombre
            if (!exercises.isEmpty()) { // si encuentra el ejercicio
                goalDTO.setExerciseName(exercises.get(0).getName());
            }
        } else { // si el objetivo es de tipo 3 (bajar de peso)
            goalDTO.setExerciseName("-");
        }
        GoalEntity entity = new GoalEntity();
        entity.setAccomplished(goalDTO.getAccomplished());
        entity.setQuantity(goalDTO.getQuantity());
        entity.setExerciseName(goalDTO.getExerciseName());
        entity.setDate(new java.sql.Date(System.currentTimeMillis()));
        entity.setAccomplishedDate(null); // por defecto, la fecha de cumplimiento es nula (0000-00-00));
        entity.setUserId(user);
        entity.setTypeGoalId(typeGoal);
        entity = goalDAO.save(entity);
        return convertToDTO(entity);
    }

    private GoalDTO convertToDTO(GoalEntity entity) {
        GoalDTO dto = new GoalDTO();
        dto.setGoalId(entity.getGoalId());
        dto.setAccomplished(entity.getAccomplished());
        dto.setQuantity(entity.getQuantity());
        dto.setExerciseName(entity.getExerciseName());
        dto.setDate(entity.getDate());
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(entity.getUserId().getUserId());
        dto.setUserId(userDTO);
        TypeGoalDTO typeGoalDTO = new TypeGoalDTO();
        typeGoalDTO.setTypeGoalId(entity.getTypeGoalId().getTypeGoalId());
        typeGoalDTO.setTypeGoal(entity.getTypeGoalId().getTypeGoal());
        dto.setTypeGoalId(typeGoalDTO);
        return dto;
    }

    public List<GoalDTO> findGoalsByUserId(int userId) {
        List<GoalEntity> goalEntities = goalDAO.findByUserId_UserId(userId);
        List<GoalDTO> goalDTOs = new ArrayList<>();
        for (GoalEntity goalEntity : goalEntities) { // se hace un for para cada goal dentro de los goals del usuario
            goalDTOs.add(convertToDTO(goalEntity));
        }
        return goalDTOs;
    }

    public void checkAndAccomplishGoals(UserEntity user, PersonalRecordBL personalRecordBL) {
        List<GoalDTO> goals = findGoalsByUserId(user.getUserId());
        List<PersonalRecordDTO> personalRecords = personalRecordBL.findAllPersonalRecordsByUserId(user.getUserId());
        for (GoalDTO goal : goals) {
            if (!goal.getAccomplished()) {
                switch (goal.getTypeGoalId().getTypeGoalId()) {

                    // 1: Alzar peso
                    case 1:
                        for (PersonalRecordDTO record : personalRecords) { // se hace un for para cada record dentro de
                                                                           // los records del usuario
                            if (record.getWeight() == goal.getQuantity()
                                    && record.getExerciseName().equals(goal.getExerciseName())) {
                                goal.setAccomplished(true);
                                updateGoal(goal);
                                break;
                            }
                        }
                        break;

                    // 2: Repeticiones
                    case 2:
                        for (PersonalRecordDTO record : personalRecords) { // se hace un for para cada record dentro de
                                                                           // los records del usuario
                            if (record.getRepetitions() == goal.getQuantity()
                                    && record.getExerciseName().equals(goal.getExerciseName())) {
                                goal.setAccomplished(true);
                                updateGoal(goal);
                                break;
                            }
                        }
                        break;

                    // 3: Bajar de peso
                    case 3:
                        PersonalRecordDTO latestRecord = personalRecords.get(0);
                        if (latestRecord.getWeight() <= goal.getQuantity()) { // si el peso del record es menor o igual
                                                                              // al peso del objetivo
                            goal.setAccomplished(true);
                            updateGoal(goal);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void updateGoal(GoalDTO goalDTO) {
        GoalEntity entity = goalDAO.findByGoalId(goalDTO.getGoalId());
        entity.setAccomplished(goalDTO.getAccomplished());
        // Establecer la fecha de cumplimiento si el objetivo se marca como cumplido
        if (goalDTO.getAccomplished()) {
            entity.setAccomplishedDate(new java.sql.Date(System.currentTimeMillis()));
        } else {
            // Restablecer la fecha de cumplimiento si el objetivo se marca como no cumplido
            entity.setAccomplishedDate(null);
        }
        goalDAO.save(entity);
    }

    public boolean goalExists(GoalDTO goalDTO) {
        List<GoalEntity> existingGoals;
        // Si el objetivo es del tipo 3, verificar específicamente para este tipo
        if (goalDTO.getTypeGoalId().getTypeGoalId() == 3) {
            existingGoals = goalDAO.findSimilarGoalsForTypeGoalId3(
                    goalDTO.getUserId().getUserId(),
                    goalDTO.getTypeGoalId().getTypeGoalId(),
                    goalDTO.getQuantity());

            // Devuelve true si hay algún objetivo de tipo 3 no cumplido
            return existingGoals.stream().anyMatch(goal -> !goal.getAccomplished());
        } else {
            // Para otros tipos de objetivos, utilizar la lógica existente
            existingGoals = goalDAO.findSimilarGoals(
                    goalDTO.getUserId().getUserId(),
                    goalDTO.getTypeGoalId().getTypeGoalId(),
                    goalDTO.getQuantity(),
                    goalDTO.getExerciseName());

            // Devuelve true si hay algún objetivo similar no cumplido
            return existingGoals.stream().anyMatch(goal -> !goal.getAccomplished());
        }
    }

}
