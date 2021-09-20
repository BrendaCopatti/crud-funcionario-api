package com.brendacopatti.crudfuncionarioapi.features.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping(path = "/api/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioBean resource;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Funcionario funcionario) {
        try {
            return new ResponseEntity<>(resource.post(funcionario), HttpStatus.OK);
        } catch (RestClientException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(resource.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(resource.get(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Funcionario funcionario) {
        try {
            return new ResponseEntity<>(resource.update(id, funcionario), HttpStatus.OK);
        } catch (RestClientException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            resource.delete(id);
            return new ResponseEntity<>("Registro removido", HttpStatus.OK);
        } catch (RestClientException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
