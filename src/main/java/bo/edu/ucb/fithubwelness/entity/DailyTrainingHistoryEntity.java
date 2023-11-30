package bo.edu.ucb.fithubwelness.entity;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "h_daily_training")
public class DailyTrainingHistoryEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dailyid")
    private Integer dailyTrainingId;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "typetrainingid", nullable = false)
    private Integer typeTrainingId;

    @Column(name = "aud_date", nullable = false)
    private Timestamp audDate;

    @Column(name = "aud_host", nullable = false, length = 100)
    private String audHost;

    @Column(name = "aud_user")
    private Integer audUser;

    public DailyTrainingHistoryEntity() {
    }

    public DailyTrainingHistoryEntity(Integer dailyTrainingId, Date date, Integer typeTrainingId, Timestamp audDate, String audHost, Integer audUser) {
        this.dailyTrainingId = dailyTrainingId;
        this.date = date;
        this.typeTrainingId = typeTrainingId;
        this.audDate = audDate;
        this.audHost = audHost;
        this.audUser = audUser;
    }

    // getters:

    public Integer getDailyTrainingId() {
        return dailyTrainingId;
    }

    public Date getDate() {
        return date;
    }

    public Integer getTypeTrainingId() {
        return typeTrainingId;
    }

    public Timestamp getAudDate() {
        return audDate;
    }

    public String getAudHost() {
        return audHost;
    }

    public Integer getAudUser() {
        return audUser;
    }

    // setters:

    public void setDailyTrainingId(Integer dailyTrainingId) {
        this.dailyTrainingId = dailyTrainingId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTypeTrainingId(Integer typeTrainingId) {
        this.typeTrainingId = typeTrainingId;
    }

    public void setAudDate(Timestamp audDate) {
        this.audDate = audDate;
    }

    public void setAudHost(String audHost) {
        this.audHost = audHost;
    }

    public void setAudUser(Integer audUser) {
        this.audUser = audUser;
    }

    //toString:
    @Override
    public String toString() {
        return "DailyTrainingHistoryEntity{" +
                "dailyTrainingId=" + dailyTrainingId +
                ", date=" + date +
                ", typeTrainingId=" + typeTrainingId +
                ", audDate=" + audDate +
                ", audHost='" + audHost + '\'' +
                ", audUser=" + audUser +
                '}';
    }
}
