package com.desafio.desafioibm;

import com.desafio.desafioibm.controller.AssociadoController;
import com.desafio.desafioibm.entity.Associado;
import com.desafio.desafioibm.handlers.CpfResponseHandler;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.service.AssociadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DesafioIbmApplicationTests {

    @InjectMocks
    private AssociadoController associadoController;

    @Mock
    private AssociadoService associadoService;

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
    void contextLoads() {
        when( associadoService.salvarAssociado(any()) ).thenReturn(associado);

    }

    @Test
    void contextLoads2() {
        when(associadoService.salvarAssociado(any())).thenThrow(ExceptionHandler.class);

        Executable executable = () -> associadoService.salvarAssociado(associado2);

        assertThrows(ExceptionHandler.class, executable);
    }
    


}
