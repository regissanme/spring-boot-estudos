package br.com.regissanme.springbootjpa.service;

import br.com.regissanme.springbootjpa.entity.Evento;
import br.com.regissanme.springbootjpa.exceptions.EventoNaoEncontradoException;
import br.com.regissanme.springbootjpa.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Projeto: spring-boot-estudos-jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 15/01/2022
 * Hora: 13:33
 */
@Service
public class EventoService {

    @Autowired
    public EventoRepository eventoRepository;

    public ResponseEntity<Evento> save(Evento evento) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventoRepository.save(evento));
    }

    public ResponseEntity<Evento> update(Long id, Evento evento) throws EventoNaoEncontradoException {
        verificaSeExiste(id);
        return ResponseEntity.ok()
                .body(eventoRepository.save(evento));
    }

    public ResponseEntity<String> delete(Long id) throws EventoNaoEncontradoException {
        verificaSeExiste(id);
        eventoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(String.format("Evento com ID: %d deletado com sucesso!", id));
    }

    public ResponseEntity<Evento> findById(Long id) throws EventoNaoEncontradoException {
        return ResponseEntity.ok(verificaSeExiste(id));
    }

    public ResponseEntity<List<Evento>> findAll(){
        return ResponseEntity.ok(eventoRepository.findAll());
    }

    private Evento verificaSeExiste(Long id) throws EventoNaoEncontradoException {
        return eventoRepository.findById(id).orElseThrow(() -> new EventoNaoEncontradoException(id));
    }


}
