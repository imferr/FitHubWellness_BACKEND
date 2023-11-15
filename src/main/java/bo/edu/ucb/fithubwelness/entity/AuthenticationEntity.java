package bo.edu.ucb.fithubwelness.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "authentication")
public class AuthenticationEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authid")
    private Integer authenticationId;

    @Column(name = "token", nullable = false, length = 100)
    private String token;

    @Column(name = "expirationdate", nullable = false)
    private Timestamp expirationDate;

    @ManyToOne
    @JoinColumn(name = "users_userid", referencedColumnName = "userid", nullable = false)
    private UserEntity userId;

    public AuthenticationEntity() {
    }

    public AuthenticationEntity(Integer authenticationId, String token, Timestamp expirationDate, UserEntity userId) {
        this.authenticationId = authenticationId;
        this.token = token;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    //getters:

    public int getAuthenticationId() {
        return authenticationId;
    }

    public String getToken() {
        return token;
    }

    public Timestamp getexpirationDate() {
        return expirationDate;
    }

    public UserEntity getUserId() {
        return userId;
    }

    //setters:

    public void setAuthenticationId(int authenticationId) {
        this.authenticationId = authenticationId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setexpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    //toString:

    @Override
    public String toString() {
        return "AuthenticationEntity{" +
                "authenticationId=" + authenticationId +
                ", token='" + token + '\'' +
                ", expirationDate=" + expirationDate +
                ", userId=" + userId +
                '}';
    }

}
