package com.brendacopatti.crudfuncionarioapi.features.funcionario.validators;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.web.client.RestClientException;

import javax.annotation.ManagedBean;

/**
 * Realiza validação da regra de E-mail
 * - deve ser um E-mail valido
 * - deve possuir no máximo 100 caracteres (regra adicionada por conta de limitação no campo do banco)
 *
 * @author Brenda
 */
@ManagedBean
public class FuncionarioEmailValidator {
    public void execute(String email) {
        if (email.length() > 100) {
            throw new RestClientException("O E-mail deve possuir no máximo 100 caracteres.");
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new RestClientException("O E-mail informado é inválido.");
        }
    }
}
