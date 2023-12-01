package bo.edu.ucb.fithubwelness.bl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.edu.ucb.fithubwelness.dao.PersonalRecordDAO;
import bo.edu.ucb.fithubwelness.dto.ExerciseDTO;
import bo.edu.ucb.fithubwelness.dto.PersonalRecordDTO;
import bo.edu.ucb.fithubwelness.entity.PersonalRecordEntity;
import bo.edu.ucb.fithubwelness.entity.UserEntity;

@Service
public class PersonalRecordBL {
    private final PersonalRecordDAO personalRecordDAO;
    private final ExerciseBL exerciseBL;
    private final GoalBL goalBL;

    @Autowired
    public PersonalRecordBL(PersonalRecordDAO personalRecordDAO, ExerciseBL exerciseBL, GoalBL goalBL) {
        this.personalRecordDAO = personalRecordDAO;
        this.exerciseBL = exerciseBL;
        this.goalBL = goalBL;
    }

    public PersonalRecordDTO createPersonalRecord(PersonalRecordDTO personalRecordDTO, UserEntity user) {
        if (checkForDuplicateRecord(personalRecordDTO, user)) {
            throw new RuntimeException("Ya existe un registro con estos valores.");
        }
        List<ExerciseDTO> exercises = exerciseBL.findExercisesByName(personalRecordDTO.getExerciseName());
        if (!exercises.isEmpty()) {
            personalRecordDTO.setExerciseName(exercises.get(0).getName());
        }
        PersonalRecordEntity entity = new PersonalRecordEntity();
        entity.setWeight(personalRecordDTO.getWeight());
        entity.setRepetitions(personalRecordDTO.getRepetitions());
        entity.setDate(new java.sql.Date(System.currentTimeMillis()));
        entity.setExerciseName(personalRecordDTO.getExerciseName());
        entity.setUserId(user);
        entity = personalRecordDAO.save(entity);
        goalBL.checkAndAccomplishGoals(user, this);
        return convertToDTO(entity);
    }

    public PersonalRecordDTO updatePersonalRecord(PersonalRecordDTO personalRecordDTO, UserEntity user) {
        if (checkForDuplicateRecord(personalRecordDTO, user)) {
            throw new RuntimeException("Ya existe un registro con estos valores.");
        }
        PersonalRecordEntity existingPersonalRecord = personalRecordDAO
                .findByPersonalRecordId(personalRecordDTO.getPersonalRecordId());
        if (existingPersonalRecord == null) {
            throw new RuntimeException("No se encontró un registro personal para actualizar.");
        }
        existingPersonalRecord.setWeight(personalRecordDTO.getWeight());
        existingPersonalRecord.setRepetitions(personalRecordDTO.getRepetitions());
        existingPersonalRecord.setExerciseName(personalRecordDTO.getExerciseName());
        existingPersonalRecord.setDate(new java.sql.Date(System.currentTimeMillis()));
        personalRecordDAO.save(existingPersonalRecord);
        goalBL.checkAndAccomplishGoals(user, this);
        return convertToDTO(existingPersonalRecord);
    }

    private boolean checkForDuplicateRecord(PersonalRecordDTO personalRecordDTO, UserEntity user) {
        List<PersonalRecordEntity> records = personalRecordDAO.findByUserIdUserId(user.getUserId());
        return records.stream().anyMatch(record -> record.getWeight().equals(personalRecordDTO.getWeight()) &&
                record.getRepetitions().equals(personalRecordDTO.getRepetitions()) &&
                record.getExerciseName().equalsIgnoreCase(personalRecordDTO.getExerciseName()));
    }

    public PersonalRecordDTO convertToDTO(PersonalRecordEntity entity) {
        return new PersonalRecordDTO(
                entity.getPersonalRecordId(),
                entity.getWeight(),
                entity.getRepetitions(),
                entity.getDate(),
                entity.getExerciseName(),
                null);
    }

    public List<PersonalRecordDTO> findAllPersonalRecordsByUserId(int userId) {
        List<PersonalRecordEntity> personalRecords = personalRecordDAO.findByUserIdUserId(userId);
        List<PersonalRecordDTO> personalRecordsDTO = new ArrayList<>();
        for (PersonalRecordEntity personalRecord : personalRecords) {
            personalRecordsDTO.add(convertToDTO(personalRecord));
        }
        return personalRecordsDTO;
    }

    public PersonalRecordDTO findPersonalRecordById(int recordId) {
        PersonalRecordEntity record = personalRecordDAO.findByPersonalRecordId(recordId);
        if (record == null) {
            throw new RuntimeException("Registro personal no encontrado con ID: " + recordId);
        }
        return convertToDTO(record);
    }
}
