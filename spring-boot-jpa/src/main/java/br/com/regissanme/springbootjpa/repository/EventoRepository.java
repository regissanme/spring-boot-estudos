package br.com.regissanme.springbootjpa.repository;

import br.com.regissanme.springbootjpa.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Projeto: spring-boot-estudos-jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 15/01/2022
 * Hora: 13:29
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
