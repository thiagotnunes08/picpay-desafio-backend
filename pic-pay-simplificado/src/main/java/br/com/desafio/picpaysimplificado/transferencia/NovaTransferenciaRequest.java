package br.com.desafio.picpaysimplificado.transferencia;

import br.com.desafio.picpaysimplificado.usuario.Usuario;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record NovaTransferenciaRequest(@NotNull @Positive BigDecimal valor, @NotNull Long pagadorId, @NotNull Long beneficiarioId) {

    public Transferencia toModel(Usuario pagador, Usuario beneficiario) {

        pagador.decrementa(valor);

        beneficiario.acrescenta(valor);

        return new Transferencia(valor, pagador, beneficiario);
    }
}
