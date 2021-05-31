package com.desafio.desafioibm.controller;

import com.desafio.desafioibm.entity.Associado;
import com.desafio.desafioibm.handlers.CpfResponseHandler;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AssociadoController {

    private AssociadoService service;

    @Autowired
    public AssociadoController(AssociadoService service) {
        this.service = service;
    }

    @PostMapping("/salvarAssociado")
    public ResponseEntity<Associado> salvarAssociado(@RequestBody Associado associado) throws ExceptionHandler {
        return ResponseEntity.ok(this.service.salvarAssociado(associado));
    }

    @GetMapping("/retornaAssociado/{id}")
    public ResponseEntity<Associado> buscaAssociado(@PathVariable int id){
        return ResponseEntity.ok(this.service.buscaAssociado(id));
    }

    @GetMapping("/buscaTodosAssociados")
    public ResponseEntity<List<Associado>> buscaTodosAssociados(){
        return ResponseEntity.ok(this.service.buscaTodosAssociados());
    }

    @DeleteMapping("/deletarAssociado/{id}")
    public void deletarAssociado(@PathVariable int id){
        this.service.deletarAssociado(id);
    }

    @PutMapping("editarAssociado")
    public ResponseEntity<Associado> editarAssociado(@RequestBody Associado associado){
        return ResponseEntity.ok(this.service.editarAssociado(associado));
    }

    @PostMapping("/verificaCpf")
    public ResponseEntity<CpfResponseHandler> verificaCpf(@RequestBody String cpf){
        return this.service.verificaCpf(cpf);
    }

}
