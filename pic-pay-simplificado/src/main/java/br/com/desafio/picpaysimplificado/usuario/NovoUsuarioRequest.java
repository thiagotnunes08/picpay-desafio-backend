package br.com.desafio.picpaysimplificado.usuario;

import br.com.desafio.picpaysimplificado.compartilhado.CPFouCNPJ;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//TODO: adicionar validacao de cnpj valido E cnpj valido
public record NovoUsuarioRequest(@NotBlank String nome, @Email String email, @NotBlank String senha, @NotBlank @CPFouCNPJ String cpfOuCnpj, @NotBlank String tipo) {

    public Usuario toModel() {
        return new Usuario(nome,email,senha,cpfOuCnpj,tipo);
    }
}
