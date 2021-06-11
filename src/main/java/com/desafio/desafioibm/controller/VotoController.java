package com.desafio.desafioibm.controller;

import com.desafio.desafioibm.entity.Voto;
import com.desafio.desafioibm.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
@Api("API REST Votos")
public class VotoController {

    private VotoService service;

    @Autowired
    public VotoController(VotoService service) {
        this.service = service;
    }

    @PostMapping("/salvarVoto/{pautaId}/{associadoId}")
    @ApiOperation("Salva um voto")
    public ResponseEntity<Voto> salvarVoto(@PathVariable int pautaId, @PathVariable int associadoId, @RequestBody Voto voto){
        return ResponseEntity.ok(this.service.salvarVoto(pautaId, associadoId, voto));
    }

    @GetMapping("buscaVoto/{id}")
    @ApiOperation("Retorna um voto pelo seu id")
    public ResponseEntity<Voto> buscaVoto(@PathVariable int id){
        return ResponseEntity.ok(this.service.buscaVoto(id));
    }

    @GetMapping("buscaTodosVotos")
    @ApiOperation("Retorna uma lista de votos")
    public ResponseEntity<List<Voto>> buscaTodosVotos(){
        return ResponseEntity.ok(this.service.buscaTodosVotos());
    }

    @DeleteMapping("/deletarVoto/{id}")
    @ApiOperation("Deleta um voto")
    public void deletarVoto(@PathVariable int id){
        this.service.deletarVoto(id);
    }

    @GetMapping("/contagemVotos/{pautaId}")
    @ApiOperation("Retorna a contagem de votos de uma pauta")
    public ResponseEntity<Long> contagemVotos(@PathVariable int pautaId){
        return ResponseEntity.ok(this.service.contagemVotos(pautaId));
    }

    @GetMapping("/resultadoVotacao")
    @ApiOperation("Retorna o resultado da votação de uma pauta")
    public ResponseEntity<String> resultadoVotacao(){
        return ResponseEntity.ok(this.service.resultadoVotacao());
    }
}
