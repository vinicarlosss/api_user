package br.com.cwi.apiuser.service;

import br.com.cwi.apiuser.controller.request.AlterarUsuarioRequest;
import br.com.cwi.apiuser.controller.response.AlterarUsuarioResponse;
import br.com.cwi.apiuser.domain.Usuario;
import br.com.cwi.apiuser.mapper.AlterarUsuarioMapper;
import br.com.cwi.apiuser.repository.UsuarioRepository;
import br.com.cwi.apiuser.service.search.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static br.com.cwi.apiuser.mapper.AlterarUsuarioMapper.toResponse;

@Service
public class AlterarUsuarioService {

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public AlterarUsuarioResponse alterar(Long idUsuario, AlterarUsuarioRequest request) {

        Usuario usuario = buscarUsuarioService.porId(idUsuario);
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setTelefone(request.getTelefone());
        usuario.setSenha(request.getSenha());
        usuario.setFotoUrl(request.getFotoUrl());
        usuario.setFuncao(request.getFuncao());
        usuario.setCriadoEm(request.getCriadoEm());
        usuario.setAtualizadoEm(request.getAtualizadoEm());

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }
}

