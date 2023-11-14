package br.com.desafio.picpaysimplificado.transferencia;

import br.com.desafio.picpaysimplificado.client.autorizador.AutorizadorClient;
import br.com.desafio.picpaysimplificado.client.autorizador.AutorizadorService;
import br.com.desafio.picpaysimplificado.client.email.EmailService;
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
    private final AutorizadorService autorizadorService;

    private final EmailService emailService;

    public EfetuaTransferenciaController(UsuarioRepository usuarioRepository, TransferenciaRepository transferenciaRepository, AutorizadorService autorizadorService, AutorizadorClient autorizadorClient, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.transferenciaRepository = transferenciaRepository;
        this.autorizadorService = autorizadorService;
        this.emailService = emailService;
    }

    @PostMapping("api/v1/transacao")
    @Transactional
    public ResponseEntity<?> efetua(@RequestBody @Valid NovaTransferenciaRequest request) {

        var pagador = usuarioRepository
                .findById(request.pagadorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var beneficiario = usuarioRepository
                .findById(request.beneficiarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var novaTransferencia = request.toModel(pagador, beneficiario);

        if (autorizadorService.eNaoAutorizada()) {

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        transferenciaRepository.save(novaTransferencia);

        if (emailService.estaDisponivel()) {
            emailService.enviaEmail(pagador, beneficiario, novaTransferencia.getValor());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
