package br.com.cwi.apiuser.controller;

import br.com.cwi.apiuser.controller.request.AlterarUsuarioRequest;
import br.com.cwi.apiuser.controller.request.UsuarioRequest;
import br.com.cwi.apiuser.controller.response.AlterarUsuarioResponse;
import br.com.cwi.apiuser.controller.response.DetalharUsuarioResponse;
import br.com.cwi.apiuser.controller.response.UsuarioResponse;
import br.com.cwi.apiuser.service.AlterarUsuarioService;
import br.com.cwi.apiuser.service.DetalharUsuarioService;
import br.com.cwi.apiuser.service.IncluirUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;
    @Autowired
    private AlterarUsuarioService alterarUsuarioService;
    @Autowired
    private DetalharUsuarioService detalharUsuarioService;

    @PostMapping
    @ResponseStatus(CREATED)
    public UsuarioResponse incluir(@RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @PutMapping("/alterar/{idUsuario}")
    @ResponseStatus(CREATED)
    public AlterarUsuarioResponse alterar(@PathVariable Long idUsuario, @Valid @RequestBody AlterarUsuarioRequest request){
        return alterarUsuarioService.alterar(idUsuario, request);
    }

   @GetMapping("/detalhar/me")
    @ResponseStatus(CREATED)
    public DetalharUsuarioResponse detalhar(){
        return detalharUsuarioService.detalhar();
    }
}

