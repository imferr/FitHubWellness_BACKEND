package bo.edu.ucb.fithubwelness.dto;

import java.sql.Date;

public class PersonalRecordDTO {
    
    private int personalRecordId;
    private Double weight;
    private int repetitions;
    private Date date;
    private GoalDTO goal;

    public PersonalRecordDTO() {
    }

    public PersonalRecordDTO(int personalRecordId, Double weight, int repetitions, Date date, GoalDTO goal) {
        this.personalRecordId = personalRecordId;
        this.weight = weight;
        this.repetitions = repetitions;
        this.date = date;
        this.goal = goal;
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

    public GoalDTO getGoal() {
        return goal;
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

    public void setGoal(GoalDTO goal) {
        this.goal = goal;
    }

    //toString:

    @Override
    public String toString() {
        return "PersonalRecordDTO{" +
                "personalRecordId=" + personalRecordId +
                ", weight=" + weight +
                ", repetitions=" + repetitions +
                ", date=" + date +
                ", goal=" + goal +
                '}';
    }
}
