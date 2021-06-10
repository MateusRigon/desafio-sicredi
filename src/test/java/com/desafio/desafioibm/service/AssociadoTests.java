package com.desafio.desafioibm.service;

import com.desafio.desafioibm.entity.Associado;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssociadoTests {

    @InjectMocks
    private AssociadoService associadoService;

    @Mock
    private AssociadoRepository associadoRepository;

    private Associado associado;

    @BeforeEach
    void setUp() {
        this.associado = new Associado(1, "mateus", "123723123");
    }

    @Test
    void deveRetornarAssociadoQuandoSalvarAssociado() {
        when(associadoRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(associadoRepository.save(any())).thenReturn(any());

        Associado associadoTeste = associadoService.salvarAssociado(associado);

        assertNotNull(associadoTeste);
    }

    @Test
    void deveRetornarExceptionHandlerQuandoJaCadastrado() throws ExceptionHandler {
        when(associadoRepository.findByCpf(anyString())).thenReturn(Optional.ofNullable(associado));

        Executable executable = () -> associadoService.salvarAssociado(associado);

        assertThrows(ExceptionHandler.class, executable);
    }

    @Test
    void deveRetornarAssociadoQuandoBuscaAssociado() {
        when(associadoRepository.findById(anyInt())).thenReturn(associado);

        Associado associado = associadoService.buscaAssociado(1);

        assertNotNull(associado);
    }

    @Test
    void deveRetornarListaAssociadoQuandoBuscaTodosAssociados() {
        List<Associado> listaAssociados = new ArrayList<Associado>();

        when(associadoRepository.findAll()).thenReturn(listaAssociados);

        List<Associado> associados = associadoService.buscaTodosAssociados();

        assertNotNull(associados);
    }

    @Test
    void deveDeletarAssociado() {
        doNothing().when(associadoRepository).deleteById(anyInt());
        associadoService.deletarAssociado(1);

        verify(associadoRepository, times(1)).deleteById(1);
    }
}
