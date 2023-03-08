package br.com.cwi.apiuser.service;

import br.com.cwi.apiuser.controller.request.UsuarioRequest;
import br.com.cwi.apiuser.controller.response.UsuarioResponse;
import br.com.cwi.apiuser.domain.Usuario;
import br.com.cwi.apiuser.mapper.IncluirUsuarioMapper;
import br.com.cwi.apiuser.repository.UsuarioRepository;
import br.com.cwi.apiuser.service.validator.ValidaEmailUnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static br.com.cwi.apiuser.mapper.IncluirUsuarioMapper.toEntity;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ValidaEmailUnicoService validaEmailUnicoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponse incluir(UsuarioRequest request) {

        validaEmailUnicoService.validar(request.getEmail());

        Usuario usuario = toEntity(request);
        usuario.setSenha(getSenhaCriptografada(request.getSenha()));

        usuarioRepository.save(usuario);

        return IncluirUsuarioMapper.toResponse(usuario);
    }

    private String getSenhaCriptografada(String senhaAberta) {
        return passwordEncoder.encode(senhaAberta);
    }

}

