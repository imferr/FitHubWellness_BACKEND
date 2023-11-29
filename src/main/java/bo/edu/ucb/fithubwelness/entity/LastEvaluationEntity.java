package bo.edu.ucb.fithubwelness.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "last_evaluation")
public class LastEvaluationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lastevaluationid")
    private Integer lastEvaluationId;
    
    @Column(name = "weight", nullable = false)
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "users_userid", referencedColumnName = "userid", nullable = false)
    private UserEntity userId;

    public LastEvaluationEntity() {
    }

    public LastEvaluationEntity(Integer lastEvaluationId, Double weight, UserEntity userId) {
        this.lastEvaluationId = lastEvaluationId;
        this.weight = weight;
        this.userId = userId;
    }

    //getters:

    public int getLastEvaluationId() {
        return lastEvaluationId;
    }

    public Double getWeight() {
        return weight;
    }

    public UserEntity getUserId() {
        return userId;
    }

    //setters:

    public void setLastEvaluationId(int lastEvaluationId) {
        this.lastEvaluationId = lastEvaluationId;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    //toString:
    @Override
    public String toString() {
        return "LastEvaluationEntity{" +
                "lastEvaluationId=" + lastEvaluationId +
                ", weight=" + weight +
                ", userId=" + userId +
                '}';
    }
}
