package bo.edu.ucb.fithubwelness.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_goal")
public class TypeGoalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typegoalid")
    private Integer typeGoalId;
    
    @Column(name = "type", nullable = false, length = 100)
    private String typeGoal;

    public TypeGoalEntity() {
    }

    public TypeGoalEntity(Integer typeGoalId, String typeGoal) {
        this.typeGoalId = typeGoalId;
        this.typeGoal = typeGoal;
    }

    //getters:

    public int getTypeGoalId() {
        return typeGoalId;
    }

    public String getTypeGoal() {
        return typeGoal;
    }

    //setters:

    public void setTypeGoalId(int typeGoalId) {
        this.typeGoalId = typeGoalId;
    }

    public void setTypeGoal(String typeGoal) {
        this.typeGoal = typeGoal;
    }

    //toString:

    @Override
    public String toString() {
        return "TypeGoalDTO{" + 
                "typeGoalId=" + typeGoalId + 
                ", typeGoal=" + typeGoal + 
                '}';
    }
}
