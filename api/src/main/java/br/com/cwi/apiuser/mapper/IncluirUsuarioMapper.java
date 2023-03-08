package br.com.cwi.apiuser.mapper;

import br.com.cwi.apiuser.controller.request.UsuarioRequest;
import br.com.cwi.apiuser.controller.response.UsuarioResponse;
import br.com.cwi.apiuser.domain.Usuario;

import java.time.LocalDate;

public class IncluirUsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setFotoUrl(request.getFotoUrl());
        entity.setFuncao(request.getFuncao());
        entity.setCriadoEm(LocalDate.now());
        entity.setAtivo(true);
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return UsuarioResponse
                .builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .email(usuario.getEmail())
                .criadoEm(usuario.getCriadoEm())
                .atualizadoEm(usuario.getAtualizadoEm())
                .build();
    }
}
