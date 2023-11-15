package bo.edu.ucb.fithubwelness.dto;

import java.sql.Date;

public class UserDTO {
    private int userId;
    private String name;
    private String email;
    private Date birthday;

    public UserDTO() {
    }

    public UserDTO(int userId, String name, String email, Date birthday) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
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

    //setters:

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    //toString:

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
