package br.com.regissanme.springbootjpa.service;

import br.com.regissanme.springbootjpa.entity.Evento;
import br.com.regissanme.springbootjpa.exceptions.EventoNaoEncontradoException;
import br.com.regissanme.springbootjpa.repository.EventoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Projeto: spring-boot-estudos
 * Desenvolvedor: Reginaldo Santos de Medeiros (regissanme)
 * Data: 01/02/2022
 * Hora: 17:50
 */
@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    @Test
    @DisplayName("Busca todos os eventos")
    void deveBuscarTodosOsEventosTest() {
        // given
        Evento evento = mock(Evento.class);
        List<Evento> eventosEsperados = List.of(evento);
        int tamanhoEsperadoDaLista = 1;

        // when
        when(eventoRepository.findAll()).thenReturn(eventosEsperados);

        // then
        List<Evento> eventosRecebidos = eventoService.findAll();

        assertAll(
                () -> assertNotNull(eventosRecebidos),
                () -> assertEquals(eventosEsperados, eventosRecebidos),
                () -> assertEquals(tamanhoEsperadoDaLista, eventosRecebidos.size())
        );
        verify(eventoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Busca um evento pelo id")
    void deveBuscarUmEventoPeloIdTest() throws EventoNaoEncontradoException {
        // given
        Evento eventoEsperado = mock(Evento.class);
        Long idMock = 1L;

        // when
        when(eventoRepository.findById(idMock)).thenReturn(Optional.ofNullable(eventoEsperado));
        when(eventoEsperado.getId()).thenReturn(1L);

        // then
        Evento eventoRecebido = eventoService.findById(idMock);

        assertAll(
                () -> assertNotNull(eventoRecebido),
                () -> assertEquals(eventoEsperado, eventoRecebido),
                () -> assertEquals(idMock, eventoRecebido.getId())
        );
        verify(eventoRepository, times(1)).findById(idMock);
    }

    @Test
    @DisplayName("Lança exceção ao buscar um evento pelo id")
    void deveLancarExcecaoAoBuscarUmEventoPeloIdTest() {
        // given
        Long idMock = 1L;
        String mensagemEsperada = "O Evento com o ID: 1 não foi encontrado!";

        // when
        // then
        EventoNaoEncontradoException exception = assertThrows(
                EventoNaoEncontradoException.class, () -> eventoService.findById(idMock));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(eventoRepository, times(1)).findById(idMock);
    }

    @Test
    @DisplayName("Lança exceção ao buscar um evento pelo id nulo")
    void deveLancarExcecaoAoBuscarUmEventoPeloIdNuloTest() {
        // given
        String mensagemEsperada = "O Evento com o ID: null não foi encontrado!";

        // when
        // then
        EventoNaoEncontradoException exception = assertThrows(
                EventoNaoEncontradoException.class, () -> eventoService.findById(null));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(eventoRepository, times(1)).findById(null);
    }

    @Test
    @DisplayName("Salva um evento")
    void deveSalvarUmEventoTest() throws EventoNaoEncontradoException {
        // given
        Evento eventoEsperado = mock(Evento.class);
        Long idMock = 1L;

        // when
        when(eventoRepository.save(any(Evento.class))).thenReturn(eventoEsperado);
        when(eventoEsperado.getId()).thenReturn(1L);

        // then
        Evento eventoRecebido = eventoService.save(eventoEsperado);

        assertAll(
                () -> assertNotNull(eventoRecebido),
                () -> assertEquals(eventoEsperado, eventoRecebido),
                () -> assertEquals(idMock, eventoRecebido.getId())
        );
        verify(eventoRepository, times(1)).save(eventoEsperado);
    }

    @Test
    @DisplayName("Lança exceção ao salvar um evento nulo")
    void deveLancarExcecaoAoSalvarUmEventoNuloTest() {
        // given
        String mensagemEsperada = "O Evento com o ID: null não foi encontrado!";

        // when
        // then
        EventoNaoEncontradoException exception = assertThrows(
                EventoNaoEncontradoException.class, () -> eventoService.save(null));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(eventoRepository, never()).save(null);
    }

    @Test
    @DisplayName("Atualiza um evento")
    void deveAtualizarUmEventoTest() throws EventoNaoEncontradoException {
        // given
        Evento eventoEsperado = mock(Evento.class);
        Long idMock = 1L;

        // when
        when(eventoRepository.findById(anyLong())).thenReturn(Optional.of(eventoEsperado));
        when(eventoRepository.save(any(Evento.class))).thenReturn(eventoEsperado);
        when(eventoEsperado.getId()).thenReturn(1L);

        // then
        Evento eventoRecebido = eventoService.update(idMock, eventoEsperado);

        assertAll(
                () -> assertNotNull(eventoRecebido),
                () -> assertEquals(eventoEsperado, eventoRecebido),
                () -> assertEquals(idMock, eventoRecebido.getId())
        );
        verify(eventoRepository, times(1)).findById(idMock);
        verify(eventoRepository, times(1)).save(eventoEsperado);
    }

    @Test
    @DisplayName("Lança uma exceção ao atualizar um evento")
    void deveLancarUmaExcecaoAoAtualizarUmEventoTest() {
        // given
        Evento eventoEsperado = mock(Evento.class);
        String mensagemEsperada = "O Evento com o ID: 1 não foi encontrado!";
        Long idMock = 1L;

        // when
        // then
        EventoNaoEncontradoException exception = assertThrows(
                EventoNaoEncontradoException.class, () -> eventoService.update(idMock, eventoEsperado));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(eventoRepository, times(1)).findById(idMock);
        verify(eventoRepository, never()).save(eventoEsperado);
    }

    @Test
    @DisplayName("Remove um evento")
    void deveRemoverUmEventoTest() throws EventoNaoEncontradoException {
        // given
        Evento eventoEsperado = mock(Evento.class);
        Long idMock = 1L;

        // when
        when(eventoRepository.findById(anyLong())).thenReturn(Optional.of(eventoEsperado));

        // then
        eventoService.delete(idMock);

        verify(eventoRepository, times(1)).findById(idMock);
        verify(eventoRepository, times(1)).deleteById(idMock);
    }

    @Test
    @DisplayName("Lança um exceção ao remover um evento")
    void deveLancarExcecaoAoRemoverUmEventoTest() {
        // given
        Long idMock = 1L;
        String mensagemEsperada = "O Evento com o ID: 1 não foi encontrado!";

        // when
        // then
        EventoNaoEncontradoException exception = assertThrows(
                EventoNaoEncontradoException.class, () -> eventoService.delete(idMock));

        assertEquals(mensagemEsperada, exception.getMessage());
        verify(eventoRepository, times(1)).findById(idMock);
        verify(eventoRepository, never()).deleteById(idMock);
    }


}