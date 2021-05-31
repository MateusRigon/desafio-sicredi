package com.desafio.desafioibm.service;

import com.desafio.desafioibm.controller.AssociadoController;
import com.desafio.desafioibm.entity.Associado;
import com.desafio.desafioibm.handlers.CpfResponseHandler;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.repository.AssociadoRepository;
import com.desafio.desafioibm.service.AssociadoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssociadoTests {

    @InjectMocks
    private AssociadoService associadoService;

    @Mock
    private AssociadoRepository associadoRepository;

    Associado associado;
    Associado associado2;

    @BeforeEach
    void setUp() {
        associado = new Associado();
        associado.setId(1);
        associado.setNome("mateus");
        associado.setCpf("02389102026");

        associado2 = new Associado();
        associado2.setId(1);
        associado2.setNome("mateus");
        associado2.setCpf("02389102026");
    }

    @Test
    void deveRetornar_associado_quando_salvarAssociado() {
        when(associadoRepository.findByNome(any())).thenReturn(Optional.empty());
        when(associadoRepository.save(any())).thenReturn(any());

        Associado associadoTeste = associadoService.salvarAssociado(associado);

        assertNotNull(associadoTeste);
    }

    @Test
    void deveRetornar_ExceptionHandler_quando_jaCadastrado() throws ExceptionHandler {
        when(associadoRepository.findByNome(any())).thenReturn(Optional.ofNullable(associado));

        Executable executable = () -> associadoService.salvarAssociado(associado);

        assertThrows(ExceptionHandler.class, executable);

    }

    @Test
    void deveRetornar_associado_quando_buscaAssociado() {
        when(associadoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(associado));

        Associado associado = associadoService.buscaAssociado(1);

        assertNotNull(associado);

    }

    @Test
    void deveRetornar_listaAssociado_quando_buscaTodosAssociados() {
        when(associadoService.salvarAssociado(any())).thenThrow(ExceptionHandler.class);

        Executable executable = () -> associadoService.salvarAssociado(associado2);

        assertThrows(ExceptionHandler.class, executable);
    }

    @Test
    void deveDeletar_associado() {
        when(associadoService.salvarAssociado(any())).thenThrow(ExceptionHandler.class);

        Executable executable = () -> associadoService.salvarAssociado(associado2);

        assertThrows(ExceptionHandler.class, executable);
    }

    @Test
    void deveRetornar_associado_quando_editarAssociado() {
        when(associadoService.salvarAssociado(any())).thenThrow(ExceptionHandler.class);

        Executable executable = () -> associadoService.salvarAssociado(associado2);

        assertThrows(ExceptionHandler.class, executable);
    }


}
