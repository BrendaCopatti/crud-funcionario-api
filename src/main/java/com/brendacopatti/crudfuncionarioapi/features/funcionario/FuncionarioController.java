package com.brendacopatti.crudfuncionarioapi.features.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping(path = "/api/funcionario/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return repository.findById(id).map(r ->
                ResponseEntity.ok().body(r)).orElse(
                        ResponseEntity.notFound().build()
        );
    }

    @PostMapping(path = "/api/funcionario")
    public Funcionario post(@RequestBody Funcionario funcionario) {
        return repository.save(funcionario);
    }

}
