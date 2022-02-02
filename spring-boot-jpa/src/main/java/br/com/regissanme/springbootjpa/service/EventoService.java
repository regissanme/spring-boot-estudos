package br.com.regissanme.springbootjpa.service;

import br.com.regissanme.springbootjpa.entity.Evento;
import br.com.regissanme.springbootjpa.exceptions.EventoNaoEncontradoException;
import br.com.regissanme.springbootjpa.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Evento save(Evento evento) throws EventoNaoEncontradoException {
        if(evento == null){
            throw new EventoNaoEncontradoException(null);
        }
        return eventoRepository.save(evento);
    }

    public Evento update(Long id, Evento evento) throws EventoNaoEncontradoException {
        findById(id);
        return eventoRepository.save(evento);
    }

    public void delete(Long id) throws EventoNaoEncontradoException {
        findById(id);
        eventoRepository.deleteById(id);
    }

    public Evento findById(Long id) throws EventoNaoEncontradoException {
        return eventoRepository.findById(id).orElseThrow(() -> new EventoNaoEncontradoException(id));
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

}
