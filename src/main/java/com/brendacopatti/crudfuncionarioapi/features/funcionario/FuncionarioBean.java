package com.brendacopatti.crudfuncionarioapi.features.funcionario;

import com.brendacopatti.crudfuncionarioapi.features.funcionario.validators.FuncionarioEmailValidator;
import com.brendacopatti.crudfuncionarioapi.features.funcionario.validators.FuncionarioNisValidator;
import com.brendacopatti.crudfuncionarioapi.features.funcionario.validators.FuncionarioNomeValidator;
import com.brendacopatti.crudfuncionarioapi.features.funcionario.validators.FuncionarioSobrenomeValidator;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe com regras de negócio referentes ao CRUD de usuário
 *
 * @author Brenda
 */
@ManagedBean
public class FuncionarioBean {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FuncionarioNomeValidator nomeValidator;

    @Autowired
    private FuncionarioSobrenomeValidator sobrenomeValidator;

    @Autowired
    private FuncionarioEmailValidator emailValidator;

    @Autowired
    private FuncionarioNisValidator nisValidator;

    public Funcionario post(Funcionario funcionario) {
        this.executaValidacoesFuncionario(funcionario);
        try {
            return repository.save(funcionario);
        } catch (Exception ex) {
            if (ex.getCause().getClass() == PropertyValueException.class) {
                PropertyValueException propertyValueException = (PropertyValueException) ex.getCause();
                throw new RestClientException("O campo " + propertyValueException.getPropertyName() + " deve possuir um valor válido.");
            }
            throw new RestClientException("Erro ao gravar funcionário: " + ex.getMessage());
        }
    }

    public List<Funcionario> getAll() {
        List<Funcionario> funcionarios = new ArrayList<>();
        repository.findAll().forEach(funcionarios::add);
        ;
        return funcionarios;
    }

    public Funcionario get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Funcionario update(Long id, Funcionario funcionario) {
        if (!repository.existsById(id)) {
            throw new RestClientException("Não foi encontrado registro com o identificador informado.");
        }
        this.executaValidacoesFuncionario(funcionario);
        try {
            return repository.save(funcionario);
        } catch (Exception ex) {
            if (ex.getCause().getClass() == PropertyValueException.class) {
                PropertyValueException propertyValueException = (PropertyValueException) ex.getCause();
                throw new RestClientException("O campo " + propertyValueException.getPropertyName() + " deve possuir um valor válido.");
            }
            throw new RestClientException("Erro ao gravar funcionário: " + ex.getMessage());
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RestClientException("Não foi encontrado registro com o identificador informado.");
        }
        repository.deleteById(id);
    }

    private void executaValidacoesFuncionario(Funcionario funcionario) {
        nomeValidator.execute(funcionario.getNome());
        sobrenomeValidator.execute(funcionario.getSobrenome());
        emailValidator.execute(funcionario.getEmail());
        nisValidator.execute(funcionario.getNis());
    }
}
