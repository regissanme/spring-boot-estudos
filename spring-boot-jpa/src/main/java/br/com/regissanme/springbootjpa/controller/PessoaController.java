package br.com.regissanme.springbootjpa.controller;

import br.com.regissanme.springbootjpa.entity.Pessoa;
import br.com.regissanme.springbootjpa.exceptions.PessoaNaoEncontradaException;
import br.com.regissanme.springbootjpa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Projeto: spring-boot-estudos-jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 15/01/2022
 * Hora: 10:13
 */
@RestController
@RequestMapping("/api/v1/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(pessoaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) throws PessoaNaoEncontradaException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pessoaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pessoa> save(@RequestBody @Valid Pessoa pessoa) throws PessoaNaoEncontradaException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pessoaService.save(pessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) throws PessoaNaoEncontradaException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pessoaService.update(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws PessoaNaoEncontradaException {
        pessoaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
