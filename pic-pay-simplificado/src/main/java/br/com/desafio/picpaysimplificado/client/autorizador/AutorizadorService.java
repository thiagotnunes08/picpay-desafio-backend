package br.com.desafio.picpaysimplificado.client.autorizador;

import org.springframework.stereotype.Service;

@Service
public class AutorizadorService {

    private final AutorizadorClient client;

    public AutorizadorService(AutorizadorClient client) {
        this.client = client;
    }

    public boolean eNaoAutorizada() {

        try {

            return !client.getAutorizacao().message().equals("Autorizado");

        } catch (RuntimeException e) {
            System.out.println("Erro ao acessar servico externo:" + e.getMessage());
        }

        return true;
    }
}