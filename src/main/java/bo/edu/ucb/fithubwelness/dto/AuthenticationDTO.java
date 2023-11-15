package bo.edu.ucb.fithubwelness.dto;

import java.sql.Timestamp;

public class AuthenticationDTO {
    
    private int authenticationId;
    private String token;
    private Timestamp expirationDate;
    private UserDTO user;

    public AuthenticationDTO() {
    }

    public AuthenticationDTO(int authenticationId, String token, Timestamp expirationDate, UserDTO user) {
        this.authenticationId = authenticationId;
        this.token = token;
        this.expirationDate = expirationDate;
        this.user = user;
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

    public UserDTO getUser() {
        return user;
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

    public void setUser(UserDTO user) {
        this.user = user;
    }

    //toString:
    
    @Override
    public String toString() {
        return "AuthenticationDTO{" +
                "authenticationId=" + authenticationId +
                ", token='" + token + '\'' +
                ", expirationDate=" + expirationDate +
                ", user=" + user +
                '}';
    }
}
