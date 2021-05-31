package com.desafio.desafioibm.controller;

import com.desafio.desafioibm.entity.Voto;
import com.desafio.desafioibm.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VotoController {

    private VotoService service;

    @Autowired
    public VotoController(VotoService service) {
        this.service = service;
    }

    @PostMapping("/salvarVoto/{pautaId}/{associadoId}")
    public ResponseEntity<Voto> salvarVoto(@PathVariable int pautaId, @PathVariable int associadoId, @RequestBody Voto voto){
        return ResponseEntity.ok(this.service.salvarVoto(pautaId, associadoId, voto));
    }

    @GetMapping("buscaVoto/{id}")
    public ResponseEntity<Voto> buscaVoto(@PathVariable int id){
        return ResponseEntity.ok(this.service.buscaVoto(id));
    }

    @GetMapping("buscaTodosVotos")
    public ResponseEntity<List<Voto>> buscaTodosVotos(){
        return ResponseEntity.ok(this.service.buscaTodosVotos());
    }

    @DeleteMapping("/deletarVoto/{id}")
    public void deletarVoto(@PathVariable int id){
        this.service.deletarVoto(id);
    }

    @GetMapping("/contagemVotos/{pautaId}")
    public ResponseEntity<Long> contagemVotos(@PathVariable int pautaId){
        return ResponseEntity.ok(this.service.contagemVotos(pautaId));
    }

    @GetMapping("/resultadoVotacao")
    public ResponseEntity<String> resultadoVotacao(){
        return ResponseEntity.ok(this.service.resultadoVotacao());
    }
}
