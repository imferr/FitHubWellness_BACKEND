package bo.edu.ucb.fithubwelness.dto;

import java.sql.Date;

public class UserDTO {
    private int userId;
    private String name;
    private String email;
    private Date birthday;
    private boolean isNewUser;

    public UserDTO() {
    }

    public UserDTO(int userId, String name, String email, Date birthday, boolean isNewUser) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.isNewUser = isNewUser;
    }

    //getters:

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    //setters:

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setNewUser(boolean isNewUser) {
        this.isNewUser = isNewUser;
    }

    //toString:

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", isNewUser=" + isNewUser +
                '}';
    }
}
