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

    @ManyToOne
    @JoinColumn(name = "users_userid", referencedColumnName = "userid", nullable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "type_goal_typegoalid", referencedColumnName = "typegoalid", nullable = false)
    private TypeGoalEntity typeGoalId;

    @ManyToOne
    @JoinColumn(name = "exercise_exerciseid", referencedColumnName = "exerciseid", nullable = false)
    private ExerciseEntity exerciseId;

    public GoalEntity() {
    }

    public GoalEntity(Integer goalId, Boolean accomplished, Double quantity, UserEntity userId, TypeGoalEntity typeGoalId, ExerciseEntity exerciseId) {
        this.goalId = goalId;
        this.accomplished = accomplished;
        this.quantity = quantity;
        this.userId = userId;
        this.typeGoalId = typeGoalId;
        this.exerciseId = exerciseId;
    }

    //getters:

    public int getGoalId() {
        return goalId;
    }

    public Boolean getAccomplished() {
        return accomplished;
    }

    public Double getQuantity() {
        return quantity;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public TypeGoalEntity getTypeGoalId() {
        return typeGoalId;
    }

    public ExerciseEntity getExerciseId() {
        return exerciseId;
    }

    //setters:

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setAccomplished(Boolean accomplished) {
        this.accomplished = accomplished;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public void setTypeGoalId(TypeGoalEntity typeGoalId) {
        this.typeGoalId = typeGoalId;
    }

    public void setExerciseId(ExerciseEntity exerciseId) {
        this.exerciseId = exerciseId;
    }

    //toString:

    @Override
    public String toString() {
        return "GoalDTO{" + 
                "goalId=" + goalId + 
                ", accomplished=" + accomplished + 
                ", quantity=" + quantity + 
                ", userId=" + userId + 
                ", typeGoalId=" + typeGoalId + 
                ", exerciseId=" + exerciseId + 
                '}';
    }
}
