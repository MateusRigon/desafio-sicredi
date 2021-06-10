package com.desafio.desafioibm.service;

import com.desafio.desafioibm.entity.Pauta;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.handlers.SessaoHandler;
import com.desafio.desafioibm.repository.PautaRepository;
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
public class PautaTests {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    Pauta pauta;
    SessaoHandler sessaoHandler;

    @BeforeEach
    void setUp() {
        this.pauta = new Pauta(1, "teste", "descricao", false, 1);

        this.sessaoHandler = new SessaoHandler(5);
    }

    @Test
    void deveRetornarPautaQuandoCadastrarPauta(){
        when(pautaRepository.findByNome(anyString())).thenReturn(Optional.empty());
        when(pautaRepository.save(any())).thenReturn(any());

        Pauta pautaTeste = pautaService.cadastrarPauta(pauta);

        assertNotNull(pautaTeste);
    }

    @Test
    void deveRetornarExceptionHandlerQuandoJaCadastrada() throws ExceptionHandler {
        when(pautaRepository.findByNome(anyString())).thenReturn(Optional.ofNullable(pauta));

        Executable executable = () -> pautaService.cadastrarPauta(pauta);

        assertThrows(ExceptionHandler.class, executable);
    }

    @Test
    void deveRetornarPautaQuandoBuscaAssociado(){
        when(pautaRepository.findById(anyInt())).thenReturn(Optional.ofNullable(pauta));

        Pauta pautaTeste = pautaService.buscaPauta(1);

        assertNotNull(pautaTeste);
    }

    @Test
    void deveRetornarListaPautaQuandoBuscaTodasPautas(){
        List<Pauta> listaPauta = new ArrayList<Pauta>();

        when(pautaRepository.findAll()).thenReturn(listaPauta);

        List<Pauta> pautaTeste = pautaService.buscaTodasPautas();

        assertNotNull(pautaTeste);
    }

    @Test
    void deveRetornarPautaQuandoAbrirSessao(){
        when(pautaRepository.findById(anyInt())).thenReturn(Optional.ofNullable(pauta));
        pauta.setStatus(true);
        pauta.setTempoSessao(sessaoHandler.getTempoSessao());
        when(pautaRepository.save(any())).thenReturn(any());

        Pauta pautaTeste = pautaService.abrirSessao(sessaoHandler,1);

        assertNotNull(pautaTeste);
    }

    @Test
    void deveRetornarPautaQuandoFecharSessao(){
        when(pautaRepository.findById(anyInt())).thenReturn(Optional.ofNullable(pauta));
        pauta.setStatus(false);
        pauta.setTempoSessao(1);
        when(pautaRepository.save(any())).thenReturn(any());

        Pauta pautaTeste = pautaService.fecharSessao(1);

        assertNotNull(pautaTeste);
    }
}
