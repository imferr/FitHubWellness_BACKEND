package bo.edu.ucb.fithubwelness.dto;

public class TypeGoalDTO {
    
    private int typeGoalId;
    private String typeGoal;

    public TypeGoalDTO() {
    }

    public TypeGoalDTO(int typeGoalId, String typeGoal) {
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
        return "TypeGoalDTO{" + "typeGoalId=" + typeGoalId + ", typeGoal=" + typeGoal + '}';
    }
}
