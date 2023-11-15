package bo.edu.ucb.fithubwelness.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_training")
public class TypeTrainingEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typetrainingid")
    private Integer typeTrainingId;

    @Column(name = "type", nullable = false, length = 100)
    private String typeTraining;

    public TypeTrainingEntity() {
    }

    public TypeTrainingEntity(Integer typeTrainingId, String typeTraining) {
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
        return "TypeTrainingDTO{" + 
                "typeTrainingId=" + typeTrainingId + 
                ", typeTraining=" + typeTraining + 
                '}';
    }
}
