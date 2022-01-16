package br.com.regissanme.springbootestudosjpa;

import br.com.regissanme.springbootestudosjpa.entity.Evento;
import br.com.regissanme.springbootestudosjpa.entity.LocalEvento;
import br.com.regissanme.springbootestudosjpa.entity.Pessoa;
import br.com.regissanme.springbootestudosjpa.entity.Telefone;
import br.com.regissanme.springbootestudosjpa.repository.EventoRepository;
import br.com.regissanme.springbootestudosjpa.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Projeto: spring-boot-estudos-jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 15/01/2022
 * Hora: 14:20
 */
@Configuration
public class PopulaBancoDeDados {

    private static final Logger log = LoggerFactory.getLogger(PopulaBancoDeDados.class);
    private Pessoa pessoa1, pessoa2, pessoa3, pessoa4;
    private Evento evento1, evento2, evento3;

    @Bean
    CommandLineRunner popularBanco(PessoaRepository pessoaRepository,
                                   EventoRepository eventoRepository) {

        criarPessoasEtelefones();
        criarEventosELocaisEventos();
        inserirPessoasNosEventos();

        return args -> {
            log.info("Inserindo " + pessoaRepository.save(pessoa1));
            log.info("Inserindo " + pessoaRepository.save(pessoa2));
            log.info("Inserindo " + pessoaRepository.save(pessoa3));
            log.info("Inserindo " + pessoaRepository.save(pessoa4));

            log.info("Inserindo " + eventoRepository.save(evento1));
            log.info("Inserindo " + eventoRepository.save(evento2));
            log.info("Inserindo " + eventoRepository.save(evento3));
        };
    }

    private void criarPessoasEtelefones() {

        Telefone telefone1 = new Telefone(null, "11 1111-1111");
        Telefone telefone2 = new Telefone(null, "22 2222-2222");
        Telefone telefone3 = new Telefone(null, "33 3333-3333");
        Telefone telefone4 = new Telefone(null, "44 4444-4444");

        List<Telefone> telefones1 = List.of(telefone1);
        List<Telefone> telefones2 = List.of(telefone2, telefone3);
        List<Telefone> telefones3 = List.of(telefone4);

        pessoa1 = new Pessoa(null, "Oráculo", new ArrayList<>());
        pessoa2 = new Pessoa(null, "Morpheus", telefones1);
        pessoa3 = new Pessoa(null, "Neo", telefones2);
        pessoa4 = new Pessoa(null, "Trinity", telefones3);
    }

    private void criarEventosELocaisEventos() {
        LocalEvento localEvento1 = new LocalEvento(null, "Na Matrix");
        LocalEvento localEvento2 = new LocalEvento(null, "Na Realidade");
        LocalEvento localEvento3 = new LocalEvento(null, "Entre a Matrix e a Realidade");

        evento1 = new Evento(null, "Matrix", localEvento1, null);
        evento2 = new Evento(null, "Matrix Reloaded", localEvento2, null);
        evento3 = new Evento(null, "Matrix Revolutions", localEvento3, null);
    }

    private void inserirPessoasNosEventos() {
        List<Pessoa> pessoas1 = List.of(pessoa1, pessoa2, pessoa3, pessoa4);
        List<Pessoa> pessoas2 = List.of(pessoa2, pessoa3, pessoa4);
        List<Pessoa> pessoas3 = List.of(pessoa1, pessoa3);

        evento1.setPessoas(pessoas1);
        evento2.setPessoas(pessoas2);
        evento3.setPessoas(pessoas3);
    }
}


