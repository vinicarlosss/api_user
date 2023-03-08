package br.com.cwi.apiuser.controller.response;

import br.com.cwi.apiuser.domain.Funcao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalharUsuarioResponse {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String fotoUrl;
    private Funcao funcao;
    private LocalDate criadoEm;
    private LocalDate atualizadoEm;
}

