package br.com.cwi.apiuser.security.service;

import br.com.cwi.apiuser.domain.PasswordTokenPublicData;
import br.com.cwi.apiuser.domain.Usuario;
import br.com.cwi.apiuser.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class UserPasswordService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @SneakyThrows
    public String generateToken(Usuario usuario){

        KeyBasedPersistenceTokenService tokenService = getInstanceFor(usuario);

        Token token = tokenService.allocateToken(usuario.getEmail());
        return token.getKey();
    }

    @SneakyThrows
    public void changePassword(String newPassword, String rawToken){
        PasswordTokenPublicData publicData = readPublicData(rawToken);

        if(isExpired(publicData)){
            throw new RuntimeException("Token expirado");
        }

        Usuario usuario = usuarioRepository.findByEmail(publicData.getEmail())
                .orElseThrow(()-> new ResponseStatusException(BAD_REQUEST, "Usuário não encontrado"));
        
        KeyBasedPersistenceTokenService tokenService = this.getInstanceFor(usuario);
        tokenService.verifyToken(rawToken);

        usuario.setSenha(this.passwordEncoder.encode(newPassword));
        usuarioRepository.save(usuario);
    }

    private boolean isExpired(PasswordTokenPublicData publicData) {
        Instant createdAt = new Date(publicData.getCreateAtTimestamp()).toInstant();
        Instant now = new Date().toInstant();
        return createdAt.plus(Duration.ofMinutes(5)).isBefore(now);
    }

    private PasswordTokenPublicData readPublicData(String rawToken) {
        byte[] bytes = Base64.getDecoder().decode(rawToken);
        String rawTokenDecoded = new String(bytes);
        String[] tokenParts = rawTokenDecoded.split(":");
        Long timestamp = Long.parseLong(tokenParts[0]);
        String email = tokenParts[2];

        return new PasswordTokenPublicData(email, timestamp);
    }

    private KeyBasedPersistenceTokenService getInstanceFor(Usuario usuario) throws Exception {
        KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();
        tokenService.setServerSecret(usuario.getSenha());
        tokenService.setServerInteger(16);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        return tokenService;
    }
}
