package main.java.apress.UserRegistrationSystem.dto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/*
This USER is user of application like admin or ordinary user
not connected with UserDTO(bussines object)
 */
@Entity
@Table(name = "t_application_users")
public class UserInfo {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    @NotEmpty
    private String userName;

    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @Column(name = "enabled")
    private boolean isEnabled;

    @Column(name = "role")
    private String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return encryptedPassword;
    }

    public void setPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
