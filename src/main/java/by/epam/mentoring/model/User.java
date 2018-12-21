package by.epam.mentoring.model;

import by.epam.mentoring.model.enums.Role;
import lombok.Data;

import javax.persistence.Transient;
@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String confirmPassword;

    private Role role;

}
