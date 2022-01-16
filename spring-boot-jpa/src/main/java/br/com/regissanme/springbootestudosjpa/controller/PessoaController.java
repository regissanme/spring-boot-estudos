package br.com.regissanme.springbootestudosjpa.controller;

import br.com.regissanme.springbootestudosjpa.entity.Pessoa;
import br.com.regissanme.springbootestudosjpa.exceptions.PessoaNaoEncontradaException;
import br.com.regissanme.springbootestudosjpa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) throws PessoaNaoEncontradaException {
        return pessoaService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Pessoa> save(@RequestBody @Valid Pessoa pessoa) {
        return pessoaService.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) throws PessoaNaoEncontradaException {
        return pessoaService.update(id, pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws PessoaNaoEncontradaException {
        return pessoaService.delete(id);
    }

}
