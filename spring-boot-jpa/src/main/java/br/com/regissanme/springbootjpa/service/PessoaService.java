package br.com.regissanme.springbootjpa.service;

import br.com.regissanme.springbootjpa.entity.Pessoa;
import br.com.regissanme.springbootjpa.exceptions.PessoaNaoEncontradaException;
import br.com.regissanme.springbootjpa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Projeto: spring-boot-estudos-jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 14/01/2022
 * Hora: 16:26
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public ResponseEntity<Pessoa> save(Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));
    }

    public ResponseEntity<Pessoa> update(Long id, Pessoa pessoa) throws PessoaNaoEncontradaException {
        findById(id);
        return ResponseEntity.ok()
                .body(pessoaRepository.save(pessoa));
    }

    public ResponseEntity<String> delete(Long id) throws PessoaNaoEncontradaException {
        pessoaRepository.delete(verificaSeExiste(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(String.format("Pessoa com ID: %d excluída com sucesso!", id));
    }

    public ResponseEntity<Pessoa> findById(Long id) throws PessoaNaoEncontradaException {
        return ResponseEntity.ok()
                .body(verificaSeExiste(id));
    }

    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.ok()
                .body(pessoaRepository.findAll());
    }

    private Pessoa verificaSeExiste(Long id) throws PessoaNaoEncontradaException {
        return pessoaRepository.findById(id).orElseThrow(() -> new PessoaNaoEncontradaException(id));
    }

}
