package br.com.desafio.picpaysimplificado.usuario;

import br.com.desafio.picpaysimplificado.carteira.Carteira;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false,unique = true)
    private String cnpjOuCpf;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Column(nullable = false)
    private LocalDateTime cadastradoEm;
    @OneToOne(cascade = CascadeType.ALL)
    private Carteira carteira;

    public Usuario(String nome, String email, String senha, String cnpjOuCpf, String tipo) {
        this.nome = nome;
        this.cnpjOuCpf = cnpjOuCpf;
        this.email = email;
        this.senha = senha;
        this.tipo = Tipo.valueOf(tipo.toUpperCase());
        this.cadastradoEm = LocalDateTime.now();
        this.carteira = new Carteira();
    }

    /**
     * @deprecated uso exclusivo do hibernate
     */
    @Deprecated
    public Usuario() {
    }

    public void decrementa(BigDecimal valor) {

        if (tipo.equals(Tipo.LOJISTA)){
            throw new IllegalArgumentException("Lojista apenas recebe transferencia.");
        }

        carteira.decrementa(valor);
    }

    public void acrescenta(BigDecimal valor) {
        carteira.acrescenta(valor);
    }
}
