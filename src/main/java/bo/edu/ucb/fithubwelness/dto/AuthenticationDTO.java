package bo.edu.ucb.fithubwelness.dto;

import java.sql.Time;

public class AuthenticationDTO {
    
    private int authenticationId;
    private String token;
    private Time expirationTime;
    private UserDTO user;

    public AuthenticationDTO() {
    }

    public AuthenticationDTO(int authenticationId, String token, Time expirationTime, UserDTO user) {
        this.authenticationId = authenticationId;
        this.token = token;
        this.expirationTime = expirationTime;
        this.user = user;
    }

    //getters:

    public int getAuthenticationId() {
        return authenticationId;
    }

    public String getToken() {
        return token;
    }

    public Time getExpirationTime() {
        return expirationTime;
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

    public void setExpirationTime(Time expirationTime) {
        this.expirationTime = expirationTime;
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
                ", expirationTime=" + expirationTime +
                ", user=" + user +
                '}';
    }
}
