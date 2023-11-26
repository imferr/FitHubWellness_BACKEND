package bo.edu.ucb.fithubwelness.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "goal")
public class GoalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goalid")
    private Integer goalId;

    @Column(name = "accomplished", nullable = false)
    private Boolean accomplished;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "exercisename", nullable = false, length = 500)
    private String exerciseName;

    @ManyToOne
    @JoinColumn(name = "users_userid", referencedColumnName = "userid", nullable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "type_goal_typegoalid", referencedColumnName = "typegoalid", nullable = false)
    private TypeGoalEntity typeGoalId;

    public GoalEntity() {
    }

    public GoalEntity(Integer goalId, Boolean accomplished, Double quantity, String exerciseName, UserEntity userId, TypeGoalEntity typeGoalId) {
        this.goalId = goalId;
        this.accomplished = accomplished;
        this.quantity = quantity;
        this.exerciseName = exerciseName;
        this.userId = userId;
        this.typeGoalId = typeGoalId;
    }

    //getters:

    public Integer getGoalId() {
        return goalId;
    }

    public Boolean getAccomplished() {
        return accomplished;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public TypeGoalEntity getTypeGoalId() {
        return typeGoalId;
    }

    //setters:

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public void setAccomplished(Boolean accomplished) {
        this.accomplished = accomplished;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public void setTypeGoalId(TypeGoalEntity typeGoalId) {
        this.typeGoalId = typeGoalId;
    }

    @Override
    public String toString() {
        return "GoalEntity{" +
                "goalId=" + goalId +
                ", accomplished=" + accomplished +
                ", quantity=" + quantity +
                ", exerciseName='" + exerciseName + '\'' +
                ", userId=" + userId +
                ", typeGoalId=" + typeGoalId +
                '}';
    }
}