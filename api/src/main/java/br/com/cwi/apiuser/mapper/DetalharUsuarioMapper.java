package br.com.cwi.apiuser.mapper;

import br.com.cwi.apiuser.controller.response.DetalharUsuarioResponse;
import br.com.cwi.apiuser.domain.Usuario;

public class DetalharUsuarioMapper {
    public static DetalharUsuarioResponse toResponse(Usuario usuario) {
        return DetalharUsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .email(usuario.getEmail())
                .fotoUrl(usuario.getFotoUrl())
                .funcao(usuario.getFuncao())
                .criadoEm(usuario.getCriadoEm())
                .atualizadoEm(usuario.getAtualizadoEm())
                .build();
    }
}
