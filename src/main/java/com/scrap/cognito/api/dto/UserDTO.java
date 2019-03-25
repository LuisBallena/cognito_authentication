package com.scrap.cognito.api.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * UserDTO.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Getter
@Setter
public class UserDTO implements Serializable {

    @NotBlank
    private String name;
    private String lastName;
    private String email;

    public UserDTO() {
    }
}
