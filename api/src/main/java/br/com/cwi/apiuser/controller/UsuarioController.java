package br.com.cwi.apiuser.controller;

import br.com.cwi.apiuser.controller.request.AlterarUsuarioRequest;
import br.com.cwi.apiuser.controller.request.PasswordResetRequest;
import br.com.cwi.apiuser.controller.request.PasswordUpdateRequest;
import br.com.cwi.apiuser.controller.request.UsuarioRequest;
import br.com.cwi.apiuser.controller.response.AlterarUsuarioResponse;
import br.com.cwi.apiuser.controller.response.DetalharUsuarioResponse;
import br.com.cwi.apiuser.controller.response.UsuarioResponse;
import br.com.cwi.apiuser.domain.Usuario;
import br.com.cwi.apiuser.repository.UsuarioRepository;
import br.com.cwi.apiuser.security.service.UserPasswordService;
import br.com.cwi.apiuser.service.AlterarUsuarioService;
import br.com.cwi.apiuser.service.DetalharUsuarioService;
import br.com.cwi.apiuser.service.EmailService;
import br.com.cwi.apiuser.service.IncluirUsuarioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
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
    @Autowired
    private UserPasswordService userPasswordService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmailService emailService;

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

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody @Valid PasswordResetRequest request){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(request.getEmail());
        optionalUsuario.ifPresent(user ->{
            String token = userPasswordService.generateToken(user);
            String content = emailService. getContent(token);
            try{
                emailService.sendEmailToClient("Alteração de senha", request.getEmail(), content);
            }catch (MessagingException e){
                throw new ResponseStatusException(BAD_REQUEST, "Não foi possível enviar o email");
            }
        });
    }

    @PostMapping("/change-password")
    public void changePassword(@RequestBody @Valid PasswordUpdateRequest request){
        userPasswordService.changePassword(request.getPassword(), request.getToken());
    }
}

