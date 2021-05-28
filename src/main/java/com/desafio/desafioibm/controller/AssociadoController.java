package com.desafio.desafioibm.controller;

import com.desafio.desafioibm.entity.Associado;
import com.desafio.desafioibm.handlers.CpfResponseHandler;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AssociadoController {

    private AssociadoService service;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public AssociadoController(AssociadoService service) {
        this.service = service;
    }

    @PostMapping("/salvarAssociado")
    public ResponseEntity<Associado> salvarAssociado(@RequestBody Associado associado) throws ExceptionHandler {
        return ResponseEntity.ok(this.service.salvarAssociado(associado));
    }

    @PostMapping("/verificaCpf")
    public ResponseEntity<CpfResponseHandler> verificaCpf(@RequestBody String cpf){
        return this.service.verificaCpf(cpf);
    }

}
