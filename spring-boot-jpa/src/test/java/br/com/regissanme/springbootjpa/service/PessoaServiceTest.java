package br.com.regissanme.springbootjpa.service;

import br.com.regissanme.springbootjpa.entity.Pessoa;
import br.com.regissanme.springbootjpa.exceptions.PessoaNaoEncontradaException;
import br.com.regissanme.springbootjpa.repository.PessoaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Projeto: spring-boot-estudos
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 02/02/2022
 * Hora: 15:56
 */
@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    @DisplayName("Busca todas as pessoas")
    void deveBuscarTodosAsPessoasTest() {
        // given
        Pessoa pessoa = mock(Pessoa.class);
        List<Pessoa> pessoasEsperadas = List.of(pessoa);
        int tamanhoEsperadoDaLista = 1;

        // when
        when(pessoaRepository.findAll()).thenReturn(pessoasEsperadas);

        // then
        List<Pessoa> pessoasRecebidas = pessoaService.findAll();

        assertAll(
                () -> assertNotNull(pessoasRecebidas),
                () -> assertEquals(pessoasEsperadas, pessoasRecebidas),
                () -> assertEquals(tamanhoEsperadoDaLista, pessoasRecebidas.size())
        );
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Busca uma pessoa pelo id")
    void deveBuscarUmaPessoaPeloIdTest() throws PessoaNaoEncontradaException {
        // given
        Pessoa pessoaEsperada = mock(Pessoa.class);
        Long idMock = 1L;

        // when
        when(pessoaRepository.findById(idMock)).thenReturn(Optional.ofNullable(pessoaEsperada));
        when(pessoaEsperada.getId()).thenReturn(1L);

        // then
        Pessoa pessoaRecebida = pessoaService.findById(idMock);

        assertAll(
                () -> assertNotNull(pessoaRecebida),
                () -> assertEquals(pessoaEsperada, pessoaRecebida),
                () -> assertEquals(idMock, pessoaRecebida.getId())
        );
        verify(pessoaRepository, times(1)).findById(idMock);
    }

    @Test
    @DisplayName("Lança exceção ao buscar uma pessoa pelo id")
    void deveLancarExcecaoAoBuscarUmaPessoaPeloIdTest() {
        // given
        Long idMock = 1L;
        String mensagemEsperada = "Pessoa com ID: 1 não encontrada!";

        // when
        // then
        PessoaNaoEncontradaException exception = assertThrows(
                PessoaNaoEncontradaException.class, () -> pessoaService.findById(idMock));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(pessoaRepository, times(1)).findById(idMock);
    }

    @Test
    @DisplayName("Lança exceção ao buscar uma pessoa pelo id nulo")
    void deveLancarExcecaoAoBuscarUmaPessoaPeloIdNuloTest() {
        // given
        String mensagemEsperada = "Pessoa com ID: null não encontrada!";

        // when
        // then
        PessoaNaoEncontradaException exception = assertThrows(
                PessoaNaoEncontradaException.class, () -> pessoaService.findById(null));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(pessoaRepository, times(1)).findById(null);
    }

    @Test
    @DisplayName("Salva uma pessoa")
    void deveSalvarUmaPessoaTest() throws PessoaNaoEncontradaException {
        // given
        Pessoa pessoaEsperada = mock(Pessoa.class);
        Long idMock = 1L;

        // when
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaEsperada);
        when(pessoaEsperada.getId()).thenReturn(1L);

        // then
        Pessoa pessoaRecebida = pessoaService.save(pessoaEsperada);

        assertAll(
                () -> assertNotNull(pessoaRecebida),
                () -> assertEquals(pessoaEsperada, pessoaRecebida),
                () -> assertEquals(idMock, pessoaRecebida.getId())
        );
        verify(pessoaRepository, times(1)).save(pessoaEsperada);
    }

    @Test
    @DisplayName("Lança exceção ao salvar uma pessoa nula")
    void deveLancarExcecaoAoSalvarUmaPessoaNulaTest() {
        // given
        String mensagemEsperada = "Pessoa com ID: null não encontrada!";

        // when
        // then
        PessoaNaoEncontradaException exception = assertThrows(
                PessoaNaoEncontradaException.class, () -> pessoaService.save(null));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(pessoaRepository, never()).save(null);
    }

    @Test
    @DisplayName("Atualiza uma pessoa")
    void deveAtualizarUmaPessoaTest() throws PessoaNaoEncontradaException {
        // given
        Pessoa pessoaEsperada = mock(Pessoa.class);
        Long idMock = 1L;

        // when
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(pessoaEsperada));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaEsperada);
        when(pessoaEsperada.getId()).thenReturn(1L);

        // then
        Pessoa pessoaRecebida = pessoaService.update(idMock, pessoaEsperada);

        assertAll(
                () -> assertNotNull(pessoaRecebida),
                () -> assertEquals(pessoaEsperada, pessoaRecebida),
                () -> assertEquals(idMock, pessoaRecebida.getId())
        );
        verify(pessoaRepository, times(1)).findById(idMock);
        verify(pessoaRepository, times(1)).save(pessoaEsperada);
    }

    @Test
    @DisplayName("Lança uma exceção ao atualizar uma pessoa")
    void deveLancarUmaExcecaoAoAtualizarUmaPessoaTest() {
        // given
        Pessoa pessoaEsperada = mock(Pessoa.class);
        String mensagemEsperada = "Pessoa com ID: 1 não encontrada!";
        Long idMock = 1L;

        // when
        // then
        PessoaNaoEncontradaException exception = assertThrows(
                PessoaNaoEncontradaException.class, () -> pessoaService.update(idMock, pessoaEsperada));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(pessoaRepository, times(1)).findById(idMock);
        verify(pessoaRepository, never()).save(pessoaEsperada);
    }

    @Test
    @DisplayName("Remove uma pessoa")
    void deveRemoverUmaPessoaTest() throws PessoaNaoEncontradaException {
        // given
        Pessoa pessoaEsperada = mock(Pessoa.class);
        Long idMock = 1L;

        // when
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(pessoaEsperada));

        // then
        pessoaService.delete(idMock);

        verify(pessoaRepository, times(1)).findById(idMock);
        verify(pessoaRepository, times(1)).deleteById(idMock);
    }

    @Test
    @DisplayName("Lança um exceção ao remover uma pessoa")
    void deveLancarExcecaoAoRemoverUmaPessoaTest() {
        // given
        Long idMock = 1L;
        String mensagemEsperada = "Pessoa com ID: 1 não encontrada!";

        // when
        // then
        PessoaNaoEncontradaException exception = assertThrows(
                PessoaNaoEncontradaException.class, () -> pessoaService.delete(idMock));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(pessoaRepository, times(1)).findById(idMock);
        verify(pessoaRepository, never()).deleteById(idMock);
    }

}