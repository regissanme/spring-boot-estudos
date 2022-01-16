package br.com.regissanme.springbootjpa.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Projeto: jpa
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 14/01/2022
 * Hora: 15:25
 */
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local_evento_id")
    private LocalEvento localEvento;

    @ManyToMany
    @JoinTable(name = "evento_pessoas",
            joinColumns = @JoinColumn(name = "evento_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pessoas_id", referencedColumnName = "id"))
    private List<Pessoa> pessoas = new java.util.ArrayList<>();

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }


    public Evento() {
    }

    public Evento(Long id, String nome, LocalEvento localEvento, List<Pessoa> pessoas) {
        this.id = id;
        this.nome = nome;
        this.localEvento = localEvento;
        this.pessoas = pessoas;
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

    public void setLocalEvento(LocalEvento localEvento) {
        this.localEvento = localEvento;
    }

    public LocalEvento getLocalEvento() {
        return localEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", localEvento=" + localEvento +
                ", " + getPessoasToString() +
                '}';
    }

    private StringBuilder getPessoasToString() {
        StringBuilder sbPessoas = new StringBuilder();
        sbPessoas.append("Pessoas[");

        if (pessoas == null || pessoas.isEmpty()) {
            sbPessoas.append("]");
            return sbPessoas;
        }

        pessoas.forEach(pessoa ->
                sbPessoas.append("{").append(pessoa.toString()).append("}, "));

        sbPessoas.append("]");
        return sbPessoas;
    }
}
