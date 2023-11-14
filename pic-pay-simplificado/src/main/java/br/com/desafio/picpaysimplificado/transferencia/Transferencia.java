package br.com.desafio.picpaysimplificado.transferencia;

import br.com.desafio.picpaysimplificado.usuario.Usuario;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal valor;
    @ManyToOne(optional = false)
    private Usuario pagador;
    @ManyToOne(optional = false)
    private Usuario beneficiario;
    @Column(nullable = false)
    private LocalDateTime efetuadaEm;

    public Transferencia(BigDecimal valor, Usuario pagador, Usuario beneficiario) {
        this.valor = valor;
        this.pagador = pagador;
        this.beneficiario = beneficiario;
        this.efetuadaEm = LocalDateTime.now();
    }

    public BigDecimal getValor() {
        return valor;
    }
}
