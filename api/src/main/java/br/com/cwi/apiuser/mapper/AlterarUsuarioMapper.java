package br.com.cwi.apiuser.mapper;

import br.com.cwi.apiuser.controller.response.AlterarUsuarioResponse;
import br.com.cwi.apiuser.domain.Usuario;

public class AlterarUsuarioMapper {
    public static AlterarUsuarioResponse toResponse(Usuario usuario) {
        return AlterarUsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .fotoUrl(usuario.getFotoUrl())
                .criadoEm(usuario.getCriadoEm())
                .atualizadoEm(usuario.getAtualizadoEm())
                .build();
    }
}

