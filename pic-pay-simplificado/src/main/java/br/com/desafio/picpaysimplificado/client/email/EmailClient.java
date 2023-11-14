package br.com.desafio.picpaysimplificado.client.email;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "email-service", url = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
public interface EmailClient {
    @GetMapping
    EmailResponse getDisponibilidade();
}
