package bo.edu.ucb.fithubwelness.dto;

import java.sql.Date;

public class UserDTO {
    private int userId;
    private String name;
    private String email;
    private Date birthday;
    private boolean isNewUser; // Nuevo campo para indicar si el usuario es nuevo.

    public UserDTO() {
    }

    // Considera agregar un nuevo constructor que incluya el campo isNewUser si es necesario.
    public UserDTO(int userId, String name, String email, Date birthday, boolean isNewUser) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.isNewUser = isNewUser; // Inicializar el nuevo campo.
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

    public boolean isNewUser() { // Getter para el nuevo campo.
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

    public void setNewUser(boolean isNewUser) { // Setter para el nuevo campo.
        this.isNewUser = isNewUser;
    }

    //toString:

    @Override
    public String toString() {
        // Asegúrate de agregar el campo isNewUser en el método toString.
        return "UserDTO{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", isNewUser=" + isNewUser +
                '}';
    }
}
