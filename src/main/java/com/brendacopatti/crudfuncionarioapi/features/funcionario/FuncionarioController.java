package com.brendacopatti.crudfuncionarioapi.features.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @PostMapping
    public Funcionario post(@RequestBody Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @GetMapping
    public List<Funcionario> getAll() {
        List<Funcionario> funcionarios = new ArrayList<>();
        repository.findAll().forEach(funcionarios::add);;
        return funcionarios;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return repository.findById(id).map(r ->
                ResponseEntity.ok().body(r)).orElse(
                        ResponseEntity.notFound().build()
        );
    }

    @PutMapping(path = "/{id}")
    public Funcionario update(@PathVariable("id") Long id, @RequestBody Funcionario funcionario) {
        if (!repository.existsById(id)) {
            throw new RestClientException("Não foi encontrado registro com o identificador informado.");
        }
        return repository.save(funcionario);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

}
