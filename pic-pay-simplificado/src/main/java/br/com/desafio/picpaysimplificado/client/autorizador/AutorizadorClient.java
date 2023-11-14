package br.com.desafio.picpaysimplificado.client.autorizador;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "autorizador", url = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc")
public interface AutorizadorClient {
    @GetMapping
    AutorizadorResponse getAutorizacao();
}
