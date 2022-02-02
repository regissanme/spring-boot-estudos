package br.com.regissanme.springbootjpa.controller;

import br.com.regissanme.springbootjpa.entity.Evento;
import br.com.regissanme.springbootjpa.exceptions.EventoNaoEncontradoException;
import br.com.regissanme.springbootjpa.service.EventoService;
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
 * Data: 31/01/2022
 * Hora: 18:35
 */
@ExtendWith(MockitoExtension.class)
class EventoControllerTest {

    @Mock
    EventoService eventoService;

    @InjectMocks
    EventoController eventoController;

    @Test
    @DisplayName("Buscar todos os eventos")
    void deveRetornarSucessoAoBuscarTodosOsEventosTest() {
        // given
        Evento evento = mock(Evento.class);
        List<Evento> eventosEsperados = List.of(evento);
        int tamanhoLista = 1;

        // when
        when(eventoService.findAll()).thenReturn(eventosEsperados);

        // then
        ResponseEntity<List<Evento>> responseEntity = eventoController.findAll();
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(tamanhoLista, responseEntity.getBody().size()),
                () -> assertEquals(eventosEsperados, responseEntity.getBody()),
                () -> verify(eventoService, times(1)).findAll()
        );

    }

    @Test
    @DisplayName("Buscar evento pelo id")
    void deveRetornarSucessoAoBuscarUmEventoPeloIdTest() throws EventoNaoEncontradoException {
        // given
        Evento eventoEsperado = mock(Evento.class);
        Long eventoId = 1L;

        // when
        when(eventoService.findById(eventoId)).thenReturn(eventoEsperado);
        when(eventoEsperado.getId()).thenReturn(eventoId);

        // then
        ResponseEntity<Evento> responseEntity = eventoController.findById(eventoId);
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(eventoEsperado, responseEntity.getBody()),
                () -> assertEquals(eventoId, responseEntity.getBody().getId()),
                () -> verify(eventoService, times(1)).findById(eventoId)
        );

    }

    @Test
    @DisplayName("Lançar Exceção ao buscar evento pelo id")
    void deveRetornarExcecaoAoBuscarUmEventoPeloIdTest() throws EventoNaoEncontradoException {
        // given
        Long eventoId = 1L;
        String mensagemEsperada = "O Evento com o ID: 1 não foi encontrado!";

        // when
        when(eventoService.findById(eventoId)).thenThrow(new EventoNaoEncontradoException(eventoId));

        // then
        EventoNaoEncontradoException exception = assertThrows(EventoNaoEncontradoException.class,
                () -> eventoController.findById(eventoId));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(eventoService, times(1)).findById(eventoId);

    }

    @Test
    @DisplayName("Lançar Exceção ao buscar evento pelo id nulo")
    void deveRetornarExcecaoAoBuscarUmEventoPeloIdNuloTest() throws EventoNaoEncontradoException {
        // given
        Long eventoId = null;
        String mensagemEsperada = "O Evento com o ID: null não foi encontrado!";

        // when
        when(eventoService.findById(eventoId)).thenThrow(new EventoNaoEncontradoException(eventoId));

        // then
        EventoNaoEncontradoException exception = assertThrows(EventoNaoEncontradoException.class,
                () -> eventoController.findById(eventoId));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(eventoService, times(1)).findById(eventoId);

    }

    @Test
    @DisplayName("Salva um evento")
    void deveRetornarSucessoAoSalvarUmEventoTest() throws EventoNaoEncontradoException {
        // given
        Evento eventoEsperado = mock(Evento.class);
        Long eventoId = 1L;

        // when
        when(eventoService.save(eventoEsperado)).thenReturn(eventoEsperado);
        when(eventoEsperado.getId()).thenReturn(1L);

        // then
        ResponseEntity<Evento> responseEntity = eventoController.save(eventoEsperado);
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(eventoEsperado, responseEntity.getBody()),
                () -> assertEquals(eventoId, responseEntity.getBody().getId()),
                () -> verify(eventoService, times(1)).save(any(Evento.class))
        );
    }

    @Test
    @DisplayName("Salva um evento")
    void deveRetornarNaoEncontradoAoSalvarUmEventoNuloTest() throws EventoNaoEncontradoException {
        // given
        Evento eventoEsperado = null;
        String mensagemEsperada = "O Evento com o ID: null não foi encontrado!";

        // when
        when(eventoService.save(null)).thenThrow(new EventoNaoEncontradoException(null));

        // then
        EventoNaoEncontradoException exception = assertThrows(
                EventoNaoEncontradoException.class, () -> eventoController.save(null));

        assertEquals(mensagemEsperada, exception.getMessage());

        verify(eventoService, never()).save(any(Evento.class));
    }

    @Test
    @DisplayName("Atualiza um evento")
    void deveRetornarSucessoAoAtualizarUmEventoTest() throws EventoNaoEncontradoException {
        // given
        Evento eventoParaAtualizar = mock(Evento.class);
        Evento eventoEsperado = mock(Evento.class);
        Long eventoId = 1L;

        // when
        when(eventoService.update(eventoId, eventoParaAtualizar)).thenReturn(eventoEsperado);
        when(eventoEsperado.getId()).thenReturn(1L);

        // then
        ResponseEntity<Evento> responseEntity = eventoController.update(eventoId, eventoParaAtualizar);
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertNotNull(responseEntity.getBody()),
                () -> assertEquals(eventoEsperado, responseEntity.getBody()),
                () -> assertEquals(eventoId, responseEntity.getBody().getId()),
                () -> verify(eventoService, times(1)).update(eventoId, eventoParaAtualizar)
        );
    }

    @Test
    @DisplayName("Lança exceção ao atualizar um evento")
    void deveRetornarExcecaoAoAtualizarUmEventoTest() throws EventoNaoEncontradoException {
        // given
        Evento eventoParaAtualizar = mock(Evento.class);
        Long eventoId = 1L;
        String mensagemEsperada = "O Evento com o ID: 1 não foi encontrado!";

        // when
        when(eventoService.update(eventoId, eventoParaAtualizar))
                .thenThrow(new EventoNaoEncontradoException(eventoId));

        // then
        EventoNaoEncontradoException exception = assertThrows(EventoNaoEncontradoException.class,
                () -> eventoController.update(eventoId, eventoParaAtualizar));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(eventoService, times(1)).update(eventoId, eventoParaAtualizar);
    }

    @Test
    @DisplayName("Remove um evento")
    void deveRemoverUmEventoTest() throws EventoNaoEncontradoException {
        // given
        Long eventoId = 1L;

        // when
        // then
        eventoController.delete(eventoId);

        verify(eventoService, times(1)).delete(eq(eventoId));
    }

    @Test
    @DisplayName("Lança Exception ao remover um evento")
    void deveLancarExcecaoAoRemoverUmEventoTest() throws EventoNaoEncontradoException {
        // given
        Long eventoId = 1L;
        String mensagemEsperada = "O Evento com o ID: 1 não foi encontrado!";

        // when
        doThrow(new EventoNaoEncontradoException(eventoId)).when(eventoService).delete(eventoId);

        // then
        EventoNaoEncontradoException exception = assertThrows(EventoNaoEncontradoException.class,
                () -> eventoController.delete(eventoId));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(eventoService, times(1)).delete(eq(eventoId));
    }


}