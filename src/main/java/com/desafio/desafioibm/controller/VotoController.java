package com.desafio.desafioibm.controller;

import com.desafio.desafioibm.entity.Voto;
import com.desafio.desafioibm.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VotoController {

    private VotoService service;

    @Autowired
    public VotoController(VotoService service) {
        this.service = service;
    }

    @PostMapping("/salvarVoto/{pautaId}/{associadoId}")
    public Voto salvarVoto(@PathVariable int pautaId, @PathVariable int associadoId, @RequestBody Voto voto){
        return this.service.salvarVoto(pautaId, associadoId, voto);
    }

    @GetMapping("/contagemVotos/{pautaId}")
    public long contagemVotos(@PathVariable int pautaId){
        return this.service.contagemVotos(pautaId);
    }

    @GetMapping("/resultadoVotacao")
    public String resultadoVotacao(){
        return this.service.resultadoVotacao();
    }
}
