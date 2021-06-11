package com.desafio.desafioibm.service;

import com.desafio.desafioibm.entity.Voto;
import com.desafio.desafioibm.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VotoTests {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    private Voto voto;

    @BeforeEach
    void setUp() {
        this.voto = new Voto(1,"Sim");
    }

    @Test
    void deveRetornarLongQuandoContagemVotos() {
        List<Voto> votos = new ArrayList<Voto>();
        votos.add(this.voto);
        votos.add(this.voto);
        when(votoRepository.findByPautaId(anyInt())).thenReturn(votos);
        Long votosTeste = votoService.contagemVotos(1);

        assertNotNull(votosTeste);
    }

    @Test
    void deveRetornarStringQuandoResultadoVotacao() {
        List<Voto> votosPositivos = new ArrayList<Voto>();
        List<Voto> votosNegativos = new ArrayList<Voto>();

        votosPositivos.add(this.voto);

        when(votoRepository.findByResultado("Sim")).thenReturn(votosPositivos);
        when(votoRepository.findByResultado("Não")).thenReturn(votosNegativos);

        String resultado = votoService.resultadoVotacao();

        assertNotNull(resultado);
    }

    @Test
    void deveRetornarVotoQuandoBuscaVoto() {
        when(votoRepository.findById(anyInt())).thenReturn(Optional.ofNullable(voto));

        Voto votoTeste = votoService.buscaVoto(1);

        assertNotNull(votoTeste);
    }



}
