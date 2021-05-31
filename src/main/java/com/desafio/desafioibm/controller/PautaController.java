package com.desafio.desafioibm.controller;

import com.desafio.desafioibm.entity.Pauta;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.handlers.SessaoHandler;
import com.desafio.desafioibm.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PautaController {

    private PautaService service;

    @Autowired
    public PautaController(PautaService service) {
        this.service = service;
    }

    @PostMapping("/cadastrarPauta")
    public ResponseEntity<Pauta> cadastrarPauta(@RequestBody Pauta pauta) throws ExceptionHandler {
        return ResponseEntity.ok(this.service.cadastrarPauta(pauta));
    }

    @GetMapping("/buscaPauta/{id}")
    public ResponseEntity<Pauta> buscaPauta(@PathVariable int id){
        return ResponseEntity.ok(this.service.buscaPauta(id));
    }

    @GetMapping("buscaTodasPautas")
    public ResponseEntity<List<Pauta>> buscaTodasPautas(){
        return ResponseEntity.ok(this.service.buscaTodasPautas());
    }

    @PutMapping("/editarPauta")
    public ResponseEntity<Pauta> editarPauta(@RequestBody Pauta pauta){
        return ResponseEntity.ok(this.service.editarPauta(pauta));
    }

    @DeleteMapping("/excluirPauta/{pautaId}")
    public void excluirPauta(@PathVariable int pautaId){
        this.service.excluirPauta(pautaId);
    }

    @PostMapping("/abrirSessao/{pautaId}")
    public ResponseEntity<Pauta> abrirSessao(@RequestBody SessaoHandler sessao, @PathVariable int pautaId){
        return ResponseEntity.ok(this.service.abrirSessao(sessao, pautaId));
    }

    @GetMapping("/fecharSessao/{pautaId}")
    public ResponseEntity<Pauta> fecharSessao(@PathVariable int pautaId){
        return ResponseEntity.ok(this.service.fecharSessao(pautaId));
    }
}
