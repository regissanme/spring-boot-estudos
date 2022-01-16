package br.com.regissanme.springbootestudosjpa.repository;

import br.com.regissanme.springbootestudosjpa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Projeto: spring-boot-estudos-jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 14/01/2022
 * Hora: 16:20
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
