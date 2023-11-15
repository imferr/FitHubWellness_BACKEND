package bo.edu.ucb.fithubwelness.dto;

public class ExerciseDTO {
    
    private int exerciseId;
    private String name;
    private String description;
    private String linkPicture;

    public ExerciseDTO() {
    }

    public ExerciseDTO(int exerciseId, String name, String description, String linkPicture) {
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