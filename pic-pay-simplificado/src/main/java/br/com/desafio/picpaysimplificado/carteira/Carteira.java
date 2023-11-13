package br.com.desafio.picpaysimplificado.carteira;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal saldo;

    public Carteira() {
        this.saldo = new BigDecimal(30);
    }

    public void decrementa(BigDecimal valor) {

        if (saldo.compareTo(valor) < 0){
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        this.saldo = this.saldo.subtract(valor);
    }

    public void acrescenta(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }
}
