package com.brendacopatti.crudfuncionarioapi.features.funcionario.validators;

import org.springframework.web.client.RestClientException;

import javax.annotation.ManagedBean;

/**
 * Realiza validação da regra de NIS
 * - Deve ser composto somente de números
 * - Deve possuir 11 dígitos (regra adicionada por conta de limitação no campo do banco)
 *
 * @author Brenda
 */
@ManagedBean
public class FuncionarioNisValidator {
    public void execute(String nis) {
        if (nis == null) {
            return;
        }

        if (nis.length() != 11) {
            throw new RestClientException("O NIS deve possuir 11 dígitos.");
        }
        if (!nis.matches("[0-9]*")) {
            throw new RestClientException("O NIS deve ser composto apenas de números.");
        }
    }
}
