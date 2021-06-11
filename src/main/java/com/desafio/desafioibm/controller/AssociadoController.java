package com.desafio.desafioibm.controller;

import com.desafio.desafioibm.entity.Associado;
import com.desafio.desafioibm.handlers.CpfResponseHandler;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.service.AssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
@Api("API REST Associados")
public class AssociadoController {

    private AssociadoService service;

    @Autowired
    public AssociadoController(AssociadoService service) {
        this.service = service;
    }

    @PostMapping("/salvarAssociado")
    @ApiOperation("Salva um associado")
    public ResponseEntity<Associado> salvarAssociado(@RequestBody Associado associado) throws ExceptionHandler {
        return ResponseEntity.ok(this.service.salvarAssociado(associado));
    }

    @GetMapping("/retornaAssociado/{id}")
    @ApiOperation("Retorna um associado pelo seu id")
    public ResponseEntity<Associado> buscaAssociado(@PathVariable int id){
        return ResponseEntity.ok(this.service.buscaAssociado(id));
    }

    @GetMapping("/buscaTodosAssociados")
    @ApiOperation("Retorna uma lista de associados")
    public ResponseEntity<List<Associado>> buscaTodosAssociados(){
        return ResponseEntity.ok(this.service.buscaTodosAssociados());
    }

    @DeleteMapping("/deletarAssociado/{id}")
    @ApiOperation("Deleta um associado")
    public void deletarAssociado(@PathVariable int id){
        this.service.deletarAssociado(id);
    }

    @PutMapping("editarAssociado")
    @ApiOperation("Edita os dados de um associado")
    public ResponseEntity<Associado> editarAssociado(@RequestBody Associado associado){
        return ResponseEntity.ok(this.service.editarAssociado(associado));
    }

    @PostMapping("/verificaCpf")
    @ApiOperation("Verifica o CPF de um associado")
    public ResponseEntity<CpfResponseHandler> verificaCpf(@RequestBody String cpf){
        return this.service.verificaCpf(cpf);
    }

}
