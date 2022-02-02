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

    public Pessoa save(Pessoa pessoa) throws PessoaNaoEncontradaException {
        if(pessoa == null) throw new PessoaNaoEncontradaException(null);
        return pessoaRepository.save(pessoa);
    }

    public Pessoa update(Long id, Pessoa pessoa) throws PessoaNaoEncontradaException {
        findById(id);
        return pessoaRepository.save(pessoa);
    }

    public void delete(Long id) throws PessoaNaoEncontradaException {
        findById(id);
        pessoaRepository.deleteById(id);
    }

    public Pessoa findById(Long id) throws PessoaNaoEncontradaException {
        return pessoaRepository.findById(id).orElseThrow(() -> new PessoaNaoEncontradaException(id));
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

}
