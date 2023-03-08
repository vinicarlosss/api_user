package br.com.cwi.apiuser.security.mapper;

import br.com.cwi.apiuser.controller.response.UsuarioResponse;
import br.com.cwi.apiuser.domain.Usuario;

public class UsuarioMapper {

    public static UsuarioResponse toResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}
