package bo.edu.ucb.fithubwelness.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "personal_record")
public class PersonalRecordEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personalrecordid")
    private Integer personalRecordId;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "repetitions", nullable = false)
    private Integer repetitions;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "goal_goalid", referencedColumnName = "goalid", nullable = false)
    private GoalEntity goalId;

    public PersonalRecordEntity() {
    }

    public PersonalRecordEntity(Integer personalRecordId, Double weight, Integer repetitions, Date date, GoalEntity goalId) {
        this.personalRecordId = personalRecordId;
        this.weight = weight;
        this.repetitions = repetitions;
        this.date = date;
        this.goalId = goalId;
    }

    //getters:

    public int getPersonalRecordId() {
        return personalRecordId;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public Date getDate() {
        return date;
    }

    public GoalEntity getGoalId() {
        return goalId;
    }

    //setters:

    public void setPersonalRecordId(int personalRecordId) {
        this.personalRecordId = personalRecordId;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGoalId(GoalEntity goalId) {
        this.goalId = goalId;
    }

    //toString:

    @Override
    public String toString() {
        return "PersonalRecordEntity{" + 
                "personalRecordId=" + personalRecordId + 
                ", weight=" + weight + 
                ", repetitions=" + repetitions + 
                ", date='" + date + '\'' + 
                ", goalId=" + goalId + 
                '}';
    }
}
