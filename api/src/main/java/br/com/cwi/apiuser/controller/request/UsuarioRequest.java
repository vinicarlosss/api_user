package br.com.cwi.apiuser.controller.request;

import br.com.cwi.apiuser.domain.Funcao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequest {

    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private String fotoUrl;
    private Funcao funcao;

}

