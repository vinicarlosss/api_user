package br.com.cwi.apiuser.controller.request;


import br.com.cwi.apiuser.domain.Funcao;
import lombok.Data;

import java.time.LocalDate;


@Data
public class AlterarUsuarioRequest {

    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String fotoUrl;
    private Funcao funcao;
    private LocalDate criadoEm;
    private LocalDate atualizadoEm;
}

