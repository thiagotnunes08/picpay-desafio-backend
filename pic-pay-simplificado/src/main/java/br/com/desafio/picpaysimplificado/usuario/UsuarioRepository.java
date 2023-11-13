package br.com.desafio.picpaysimplificado.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByCnpjOuCpfOrEmail(String cnpjOuEmail, String email);
}
