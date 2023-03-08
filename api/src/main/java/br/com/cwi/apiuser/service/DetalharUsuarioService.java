package br.com.cwi.apiuser.service;

import br.com.cwi.apiuser.controller.response.DetalharUsuarioResponse;
import br.com.cwi.apiuser.domain.Usuario;
import br.com.cwi.apiuser.mapper.DetalharUsuarioMapper;
import br.com.cwi.apiuser.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalharUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public DetalharUsuarioResponse detalhar() {

        Usuario usuario = usuarioAutenticadoService.get();

        return DetalharUsuarioMapper.toResponse(usuario);
    }
}
