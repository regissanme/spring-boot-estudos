package br.com.regissanme.springbootjpa.controller;

import br.com.regissanme.springbootjpa.entity.Pessoa;
import br.com.regissanme.springbootjpa.exceptions.PessoaNaoEncontradaException;
import br.com.regissanme.springbootjpa.service.PessoaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Projeto: spring-boot-estudos
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 01/02/2022
 * Hora: 16:43
 */
@ExtendWith(MockitoExtension.class)
class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    @Test
    @DisplayName("Busca todas as Pessoas")
    void deveRetornarSucessoAoBuscarTodasAsPessoasTest() {
        // given
        Pessoa pessoa = mock(Pessoa.class);
        List<Pessoa> pessoasEsperadas = List.of(pessoa);
        int tamanhoEsperadoDaLista = 1;

        // when
        when(pessoaService.findAll()).thenReturn(pessoasEsperadas);

        // then
        ResponseEntity<List<Pessoa>> responseEntity = pessoaController.findAll();

        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(tamanhoEsperadoDaLista, responseEntity.getBody().size()),
                () -> assertEquals(pessoasEsperadas, responseEntity.getBody())
        );
        verify(pessoaService, times(1)).findAll();
    }

    @Test
    @DisplayName("Busca pessoa pelo id")
    void deveRetornarSucessoAoBuscarPessoaPorIdTest() throws PessoaNaoEncontradaException {
        // given
        Pessoa pessoaEsperada = mock(Pessoa.class);
        Long idMock = 1L;

        // when
        when(pessoaService.findById(anyLong())).thenReturn(pessoaEsperada);
        when(pessoaEsperada.getId()).thenReturn(idMock);

        // then
        ResponseEntity<Pessoa> responseEntity = pessoaController.findById(idMock);

        assertAll(
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(pessoaEsperada, responseEntity.getBody()),
                () -> assertEquals(idMock, responseEntity.getBody().getId())
        );
        verify(pessoaService, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Lança Exceção ao buscar pessoa pelo id")
    void deveRetornarNaoEncontradoAoBuscarPessoaPorIdTest() throws PessoaNaoEncontradaException {
        // given
        Long idMock = 1L;
        String mensagemEsperada = "Pessoa com ID: 1 não encontrada!";

        // when
        when(pessoaService.findById(anyLong())).thenThrow(new PessoaNaoEncontradaException(idMock));

        // then
        PessoaNaoEncontradaException exception = assertThrows(PessoaNaoEncontradaException.class, () -> pessoaController.findById(idMock));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(pessoaService, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Salva pessoa")
    void deveRetornarSucessoAoSalvarPessoaTest() throws PessoaNaoEncontradaException {
        // given
        Pessoa pessoaEsperada = mock(Pessoa.class);
        Long idMock = 1L;

        // when
        when(pessoaService.save(any(Pessoa.class))).thenReturn(pessoaEsperada);
        when(pessoaEsperada.getId()).thenReturn(1L);

        // then
        ResponseEntity<Pessoa> responseEntity = pessoaController.save(pessoaEsperada);

        assertAll(
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()),
                () -> assertEquals(pessoaEsperada, responseEntity.getBody()),
                () -> assertEquals(idMock, responseEntity.getBody().getId())
        );
        verify(pessoaService, times(1)).save(pessoaEsperada);
    }

    @Test
    @DisplayName("Atualiza pessoa pelo id")
    void deveRetornarSucessoAoAtualizarPessoaPeloIdTest() throws PessoaNaoEncontradaException {
        // given
        Pessoa pessoaEsperada = mock(Pessoa.class);
        Long idMock = 1L;

        // when
        when(pessoaService.update(anyLong(), any(Pessoa.class))).thenReturn(pessoaEsperada);
        when(pessoaEsperada.getId()).thenReturn(1L);

        // then
        ResponseEntity<Pessoa> responseEntity = pessoaController.update(idMock, pessoaEsperada);

        assertAll(
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(pessoaEsperada, responseEntity.getBody()),
                () -> assertEquals(idMock, responseEntity.getBody().getId())
        );
        verify(pessoaService, times(1)).update(idMock, pessoaEsperada);
    }

    @Test
    @DisplayName("Lança Exceção ao atualizar pessoa pelo id")
    void deveRetornarNaoEncontradoAoAtualizarPessoaPorIdTest() throws PessoaNaoEncontradaException {
        // given
        Pessoa pessoaEsperada = mock(Pessoa.class);
        Long idMock = 1L;
        String mensagemEsperada = "Pessoa com ID: 1 não encontrada!";

        // when
        when(pessoaService.update(anyLong(), any(Pessoa.class))).thenThrow(new PessoaNaoEncontradaException(idMock));

        // then
        PessoaNaoEncontradaException exception = assertThrows(PessoaNaoEncontradaException.class, () -> pessoaController.update(idMock, pessoaEsperada));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(pessoaService, times(1)).update(idMock, pessoaEsperada);
    }

    @Test
    @DisplayName("Remove uma pessoa")
    void deveRemoverUmaPessoaTest() throws PessoaNaoEncontradaException {
        // given
        Long idMock = 1L;

        // when
        // then
        pessoaController.delete(idMock);

        verify(pessoaService, times(1)).delete(idMock);
    }

    @Test
    @DisplayName("Lança Exceção ao remover uma pessoa")
    void deveLancarUmaExcecaoAoRemoverUmaPessoaTest() throws PessoaNaoEncontradaException {
        // given
        Long idMock = 1L;
        String mensagemEsperada = "Pessoa com ID: 1 não encontrada!";

        // when
        doThrow(new PessoaNaoEncontradaException(idMock))
                .when(pessoaService).delete(idMock);

        // then
        PessoaNaoEncontradaException exception = assertThrows(
                PessoaNaoEncontradaException.class, () -> pessoaController.delete(idMock));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(pessoaService, times(1)).delete(idMock);
    }


}