package br.com.regissanme.springbootestudosjpa.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Projeto: jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 14/01/2022
 * Hora: 15:24
 */
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @Size(min = 2, max = 100)
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private List<Telefone> telefones;


    public Pessoa() {
    }

    public Pessoa(Long id, String nome, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.telefones = telefones;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Telefone> getTelefones() {
        if (telefones == null) {
            telefones = new ArrayList<>();
        }
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void addTelefone(Telefone telefone) {
        if (telefones == null) telefones = new ArrayList<>();
        telefones.add(telefone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        return new StringBuilder()
                .append("Pessoa{")
                .append("id=").append(id)
                .append(", nome=").append(nome)
                .append(", ").append(getTelefonesToString())
                .append('}')
                .toString();
    }

    private StringBuilder getTelefonesToString() {
        StringBuilder sbTelefones = new StringBuilder();
        sbTelefones.append("Telefones[");

        if (telefones == null || telefones.isEmpty()) {
            sbTelefones.append("]");
            return sbTelefones;
        }

        telefones.forEach(telefone ->
                sbTelefones.append("{").append(telefone.toString()).append("}, "));

        sbTelefones.append("]");
        return sbTelefones;
    }

}
