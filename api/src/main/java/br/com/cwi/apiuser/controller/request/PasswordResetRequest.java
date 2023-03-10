package br.com.cwi.apiuser.controller.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class PasswordResetRequest {

    @Email
    @NotBlank
    private String email;
}
