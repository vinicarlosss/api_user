package br.com.cwi.apiuser.service;

import br.com.cwi.apiuser.controller.request.AlterarUsuarioRequest;
import br.com.cwi.apiuser.controller.response.AlterarUsuarioResponse;
import br.com.cwi.apiuser.domain.Usuario;
import br.com.cwi.apiuser.mapper.AlterarUsuarioMapper;
import br.com.cwi.apiuser.repository.UsuarioRepository;
import br.com.cwi.apiuser.service.search.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static br.com.cwi.apiuser.mapper.AlterarUsuarioMapper.toResponse;

@Service
public class AlterarUsuarioService {

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public AlterarUsuarioResponse alterar(Long idUsuario, AlterarUsuarioRequest request) {

        Usuario usuario = buscarUsuarioService.porId(idUsuario);
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail() == null ? usuario.getEmail():request.getEmail());
        usuario.setTelefone(request.getTelefone());
        usuario.setSenha(getSenhaCriptografada(request.getSenha()));
        usuario.setFotoUrl(request.getFotoUrl());
        usuario.setFuncao(request.getFuncao());
        usuario.setAtualizadoEm(LocalDate.now());

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }

    private String getSenhaCriptografada(String senhaAberta) {
        return passwordEncoder.encode(senhaAberta);
    }
}

