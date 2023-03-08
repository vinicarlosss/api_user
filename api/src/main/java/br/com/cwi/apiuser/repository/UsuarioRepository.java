package br.com.cwi.apiuser.repository;

import br.com.cwi.apiuser.domain.Usuario;
import io.micrometer.core.instrument.binder.db.MetricsDSLContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
