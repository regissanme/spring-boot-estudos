package br.com.regissanme.springbootestudosjpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Projeto: spring-boot-estudos-jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 15/01/2022
 * Hora: 10:31
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PessoaNaoEncontradaException extends Exception {

    public PessoaNaoEncontradaException(Long id) {
        super(String.format("Pessoa com ID: %d não encontrada!", id));
    }
}
