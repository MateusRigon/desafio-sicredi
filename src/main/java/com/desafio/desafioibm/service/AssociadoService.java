package com.desafio.desafioibm.service;

import com.desafio.desafioibm.entity.Associado;
import com.desafio.desafioibm.handlers.CpfResponseHandler;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AssociadoService {

    private final AssociadoRepository repositorio;

    @Autowired
    public AssociadoService(AssociadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Associado salvarAssociado(Associado associado) throws ExceptionHandler {
        Optional<Associado> associadoExistente = this.repositorio.findByCpf(associado.getCpf());

        if (associadoExistente.isEmpty()){
            this.repositorio.save(associado);
            return associado;
        }
        throw new ExceptionHandler("Associado já cadastrado");
    }

    public Associado buscaAssociado(int associadoId){
        return this.repositorio.findById(associadoId);
    }

    public ResponseEntity<CpfResponseHandler> verificaCpf(String cpf){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://user-info.herokuapp.com/users/"+cpf;
        ResponseEntity<CpfResponseHandler> response = restTemplate.getForEntity(url, CpfResponseHandler.class);

        return response;
    }

    public void deletarAssociado(int associadoId) {
        this.repositorio.deleteById(associadoId);
    }

    public List<Associado> buscaTodosAssociados() {
        return this.repositorio.findAll();
    }

    public Associado editarAssociado(Associado associado) {
        return this.repositorio.save(associado);
    }
}
