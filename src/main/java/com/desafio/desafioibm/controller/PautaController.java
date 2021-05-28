package com.desafio.desafioibm.controller;

import com.desafio.desafioibm.entity.Pauta;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.handlers.SessaoHandler;
import com.desafio.desafioibm.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PautaController {

    private PautaService service;

    @Autowired
    public PautaController(PautaService service) {
        this.service = service;
    }

    @PostMapping("/cadastrarPauta")
    public Pauta cadastrarPauta(@RequestBody Pauta pauta) throws ExceptionHandler {
        return this.service.cadastrarPauta(pauta);
    }

    @PostMapping("/abrirSessao/{pautaId}")
    public Pauta abrirSessao(@RequestBody SessaoHandler sessao, @PathVariable int pautaId){
        return this.service.abrirSessao(sessao, pautaId);
    }

    @DeleteMapping("/excluirPauta/{pautaId}")
    public String excluirPauta(@PathVariable int pautaId){
        return this.service.excluirPauta(pautaId);
    }

    @GetMapping("/fecharSessao/{pautaId}")
    public Pauta fecharSessao(@PathVariable int pautaId){
        return this.service.fecharSessao(pautaId);
    }
}
