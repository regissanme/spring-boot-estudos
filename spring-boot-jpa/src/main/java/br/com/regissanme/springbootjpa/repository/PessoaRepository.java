package br.com.regissanme.springbootjpa.repository;

import br.com.regissanme.springbootjpa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Projeto: spring-boot-estudos-jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 14/01/2022
 * Hora: 16:20
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
