package br.com.cwi.apiuser.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordUpdateRequest {

    @NotBlank
    private String password;
    @NotBlank
    private String token;
}
