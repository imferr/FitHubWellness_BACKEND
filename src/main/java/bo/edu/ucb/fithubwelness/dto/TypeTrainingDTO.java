package bo.edu.ucb.fithubwelness.dto;

public class TypeTrainingDTO {
    
    private int typeTrainingId;
    private String typeTraining;

    public TypeTrainingDTO() {
    }

    public TypeTrainingDTO(int typeTrainingId, String typeTraining) {
        this.typeTrainingId = typeTrainingId;
        this.typeTraining = typeTraining;
    }

    //getters:

    public int getTypeTrainingId() {
        return typeTrainingId;
    }

    public String getTypeTraining() {
        return typeTraining;
    }

    //setters:

    public void setTypeTrainingId(int typeTrainingId) {
        this.typeTrainingId = typeTrainingId;
    }

    public void setTypeTraining(String typeTraining) {
        this.typeTraining = typeTraining;
    }

    //toString:

    @Override
    public String toString() {
        return "TypeTrainingDTO{" + "typeTrainingId=" + typeTrainingId + ", typeTraining=" + typeTraining + '}';
    }
}
