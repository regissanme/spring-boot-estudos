package br.com.regissanme.springbootjpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Projeto: spring-boot-estudos-jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 15/01/2022
 * Hora: 13:35
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventoNaoEncontradoException extends Exception {

    public EventoNaoEncontradoException(Long id) {
        super(String.format("O Evento com o ID: %d não foi encontrado!", id));
    }
}
