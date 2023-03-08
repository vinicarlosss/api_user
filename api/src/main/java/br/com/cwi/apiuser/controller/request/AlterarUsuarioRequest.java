package br.com.cwi.apiuser.controller.request;


import br.com.cwi.apiuser.domain.Funcao;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class AlterarUsuarioRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String telefone;
    @NotBlank
    private String senha;
    @NotBlank
    private String fotoUrl;
    private Funcao funcao;
}

