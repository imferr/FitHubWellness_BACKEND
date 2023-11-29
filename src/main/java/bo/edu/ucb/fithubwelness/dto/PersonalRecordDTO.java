package bo.edu.ucb.fithubwelness.dto;

import java.sql.Date;

public class PersonalRecordDTO {
    
    private int personalRecordId;
    private Double weight;
    private int repetitions;
    private Date date;
    private String exerciseName;
    private UserDTO userId;

    public PersonalRecordDTO() {
    }

    public PersonalRecordDTO(int personalRecordId, Double weight, int repetitions, Date date, String exerciseName, UserDTO userId) {
        this.personalRecordId = personalRecordId;
        this.weight = weight;
        this.repetitions = repetitions;
        this.date = date;
        this.exerciseName = exerciseName;
        this.userId = userId;
    }

    //getters:

    public int getPersonalRecordId() {
        return personalRecordId;
    }

    public Double getWeight() {
        return weight;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public Date getDate() {
        return date;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public UserDTO getUserId() {
        return userId;
    }

    //setters:

    public void setPersonalRecordId(int personalRecordId) {
        this.personalRecordId = personalRecordId;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setUserId(UserDTO userId) {
        this.userId = userId;
    }

    //toString:

    @Override
    public String toString() {
        return "PersonalRecordDTO{" +
                "personalRecordId=" + personalRecordId +
                ", weight=" + weight +
                ", repetitions=" + repetitions +
                ", date=" + date +
                ", exerciseName='" + exerciseName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
