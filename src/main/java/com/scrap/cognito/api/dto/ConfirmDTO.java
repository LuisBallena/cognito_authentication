package com.scrap.cognito.api.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * ConfirmDTO.
 *
 * @author Luis Alonso Ballena Garcia
 */
@Getter
@Setter
public class ConfirmDTO {

    @NotBlank
    private String email;
    @NotBlank
    private String code;

    public ConfirmDTO() {
    }
}
