package br.com.regissanme.springbootjpa.controller;

import br.com.regissanme.springbootjpa.entity.Evento;
import br.com.regissanme.springbootjpa.exceptions.EventoNaoEncontradoException;
import br.com.regissanme.springbootjpa.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Projeto: spring-boot-estudos-jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 15/01/2022
 * Hora: 13:32
 */
@RestController
@RequestMapping("/api/v1/evento")
public class EventoController {

    @Autowired
    public EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> findAll() {
        return eventoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> findById(@PathVariable Long id) throws EventoNaoEncontradoException {
        return eventoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Evento> save(@RequestBody @Valid Evento evento) {
        return eventoService.save(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> update(@PathVariable Long id, @RequestBody @Valid Evento evento) throws EventoNaoEncontradoException {
        return eventoService.update(id, evento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws EventoNaoEncontradoException {
        return eventoService.delete(id);
    }


}
