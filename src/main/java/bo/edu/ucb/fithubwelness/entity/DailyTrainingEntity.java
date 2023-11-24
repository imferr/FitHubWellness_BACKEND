package bo.edu.ucb.fithubwelness.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "daily_training")
public class DailyTrainingEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dailyid")
    private Integer dailyTrainingId;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "type_training_typetrainingid", referencedColumnName = "typetrainingid", nullable = false)
    private TypeTrainingEntity typeTrainingId;

    @ManyToOne
    @JoinColumn(name = "users_userid", referencedColumnName = "userid", nullable = false)
    private UserEntity userId;

    public DailyTrainingEntity() {
    }

    public DailyTrainingEntity(Integer dailyTrainingId, Date date, TypeTrainingEntity typeTrainingId, UserEntity userId) {
        this.dailyTrainingId = dailyTrainingId;
        this.date = date;
        this.typeTrainingId = typeTrainingId;
        this.userId = userId;
    }

    //getters:

    public int getDailyTrainingId() {
        return dailyTrainingId;
    }

    public Date getDate() {
        return date;
    }

    public TypeTrainingEntity getTypeTrainingId() {
        return typeTrainingId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    //setters:

    public void setDailyTrainingId(int dailyTrainingId) {
        this.dailyTrainingId = dailyTrainingId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTypeTrainingId(TypeTrainingEntity typeTrainingId) {
        this.typeTrainingId = typeTrainingId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    //toString:

    @Override
    public String toString() {
        return "DailyTrainingEntity{" + 
                "dailyTrainingId=" + dailyTrainingId + 
                ", date='" + date + '\'' + 
                ", typeTrainingId=" + typeTrainingId + 
                ", userId=" + userId + 
                '}';
    }
}