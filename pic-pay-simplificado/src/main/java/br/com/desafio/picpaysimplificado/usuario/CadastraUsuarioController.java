package br.com.desafio.picpaysimplificado.usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CadastraUsuarioController {

    private final UsuarioRepository usuarioRepository;

    public CadastraUsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("api/v1/usuario")
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoUsuarioRequest request) {

        if (usuarioRepository.existsByCnpjOuCpfOrEmail(request.cpfOuCnpj(),request.email())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email, CNPJ ou CPF já cadastrado no sitema.");
        }

        var novoUsuario = request.toModel();

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
