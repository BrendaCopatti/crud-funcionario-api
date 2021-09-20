package com.brendacopatti.crudfuncionarioapi.features.funcionario.validators;

import org.springframework.web.client.RestClientException;

import javax.annotation.ManagedBean;

/**
 * Realiza validação da regra de sobrenome
 * - Deve possuir entre 2 e 50 caracteres
 *
 * @author Brenda
 */
@ManagedBean
public class FuncionarioSobrenomeValidator {
    public void execute(String sobrenome) {
        if (sobrenome == null) {
            return;
        }

        if (sobrenome.length() < 2 || sobrenome.length() > 50) {
            throw new RestClientException("O sobrenome do funcionário deve possuir entre 2 e 50 caracteres.");
        }
    }
}
