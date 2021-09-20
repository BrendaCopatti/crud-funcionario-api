package com.brendacopatti.crudfuncionarioapi.features.funcionario.validators;

import org.springframework.web.client.RestClientException;

import javax.annotation.ManagedBean;

/**
 * Realiza validação da regra de nome
 * - deve possuir entre 2 e 30 caracteres
 *
 * @author Brenda
 */
@ManagedBean
public class FuncionarioNomeValidator {
    public void execute(String nome) {
        if (nome != null && nome.length() < 2 || nome.length() > 30) {
            throw new RestClientException("O nome do funcionário deve possuir entre 2 e 30 caracteres");
        }
    }
}
