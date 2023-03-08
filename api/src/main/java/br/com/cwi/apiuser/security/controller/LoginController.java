package br.com.cwi.apiuser.security.controller;

import br.com.cwi.apiuser.controller.response.UsuarioResponse;
import br.com.cwi.apiuser.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioAutenticadoService service;

    @PostMapping
    public UsuarioResponse login() {
        return service.getResponse();
    }
}

