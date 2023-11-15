package bo.edu.ucb.fithubwelness.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class ExerciseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exerciseid")
    private Integer exerciseId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "linkpicture", nullable = false, length = 500)
    private String linkPicture;

    public ExerciseEntity() {
    }

    public ExerciseEntity(Integer exerciseId, String name, String description, String linkPicture) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.description = description;
        this.linkPicture = linkPicture;
    }

    //getters:

    public int getExerciseId() {
        return exerciseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLinkPicture() {
        return linkPicture;
    }

    //setters:

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description= description;
    }

    public void setLinkPicture(String linkPicture) {
        this.linkPicture = linkPicture;
    }

    //toString:

    @Override
    public String toString() {
        return "ExerciseDTO{" +
                "exerciseId=" + exerciseId + 
                ", name=" + name + 
                ", description=" + description + 
                ", linkPicture=" + linkPicture + 
                '}';
    }
}
