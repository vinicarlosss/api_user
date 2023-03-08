package br.com.cwi.apiuser.controller.response;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlterarUsuarioResponse {

    private Long id;
    private String nome;
    private String telefone;
    private String fotoUrl;
    private LocalDate criadoEm;
    private LocalDate atualizadoEm;
}

