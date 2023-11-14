package br.com.desafio.picpaysimplificado.client.email;

import br.com.desafio.picpaysimplificado.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EmailService {

    private final EmailClient client;

    public EmailService(EmailClient client) {
        this.client = client;
    }

    public boolean estaDisponivel() {

        try {

            return client.getDisponibilidade().message();

        } catch (RuntimeException e) {
            System.out.println("Erro ao acessar servico externo:" + e.getMessage());
        }

        return false;
    }

    public void enviaEmail(Usuario pagador, Usuario beneficiario, BigDecimal valor) {
        System.out.println("Enviando email para:");
        System.out.println(beneficiario.getEmail());
        System.out.println("Corpo do email:");
        System.out.println("Olá," + beneficiario.getNome() +"! Você recebeu uma transferencia no valor de R$"+valor +" reais de " + pagador.getNome());
    }
}