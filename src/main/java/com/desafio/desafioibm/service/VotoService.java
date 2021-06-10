package com.desafio.desafioibm.service;

import com.desafio.desafioibm.entity.Associado;
import com.desafio.desafioibm.entity.Voto;
import com.desafio.desafioibm.handlers.CpfResponseHandler;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoService {

    private VotoRepository repositorio;

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    public VotoService(VotoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Voto salvarVoto(int pautaId, int associadoId, Voto voto){
        Optional<Voto> votoExistente = this.repositorio.findByAssociadoId(associadoId);

        Associado associado = this.associadoService.buscaAssociado(associadoId);

        ResponseEntity<CpfResponseHandler> associadoCpf = this.associadoService.verificaCpf(associado.getCpf());

        String body = associadoCpf.getBody().getStatus();
        String podeVotar = "ABLE_TO_VOTE";

        if( body.equals(podeVotar) ){
            if (votoExistente.isEmpty()){
                voto.setPautaId(pautaId);
                voto.setAssociadoId(associadoId);
                this.repositorio.save(voto);
                return voto;
            }
            throw new ExceptionHandler("Associado já votou nesta pauta");
        }
        throw new ExceptionHandler("CPF do associado não está habilitado para votar nesta pauta");
    }

    public Long contagemVotos(int pautaId){
        List<Voto> voto = this.repositorio.findByPautaId(pautaId);

        return voto.stream().count();
    }

    public String resultadoVotacao(){
        List<Voto> votosPositivos = this.repositorio.findByResultado("Sim");
        List<Voto> votosNegativos = this.repositorio.findByResultado("Não");

        long resultadoVotosPositivos = votosPositivos.stream().count();
        long resultadoVotosNegativos = votosNegativos.stream().count();

        String resultado = "O resultado da votação é: "+resultadoVotosPositivos+" votos para 'Sim' e " +
                resultadoVotosNegativos+" votos para 'Não'";

        return resultado;
    }

    public Voto buscaVoto(int id) {
        return this.repositorio.findById(id).orElse(null);
    }

    public void deletarVoto(int id) {
        this.repositorio.deleteById(id);
    }

    public List<Voto> buscaTodosVotos() {
        return this.repositorio.findAll();
    }
}
