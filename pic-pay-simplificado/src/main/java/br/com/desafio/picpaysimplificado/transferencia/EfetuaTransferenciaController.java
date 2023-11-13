package br.com.desafio.picpaysimplificado.transferencia;

import br.com.desafio.picpaysimplificado.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EfetuaTransferenciaController {

    private final UsuarioRepository usuarioRepository;
    private final TransferenciaRepository transferenciaRepository;

    public EfetuaTransferenciaController(UsuarioRepository usuarioRepository, TransferenciaRepository transferenciaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.transferenciaRepository = transferenciaRepository;
    }

    @PostMapping("api/v1/transacao")
    @Transactional
    public ResponseEntity<?> transfere(@RequestBody @Valid NovaTransferenciaRequest request) {

        var pagador = usuarioRepository
                .findById(request.pagadorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var beneficiario = usuarioRepository
                .findById(request.beneficiarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var novaTransferencia = request.toModel(pagador, beneficiario);

        transferenciaRepository.save(novaTransferencia);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
