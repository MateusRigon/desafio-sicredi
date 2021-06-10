package com.desafio.desafioibm.service;

import com.desafio.desafioibm.entity.Pauta;
import com.desafio.desafioibm.handlers.ExceptionHandler;
import com.desafio.desafioibm.handlers.SessaoHandler;
import com.desafio.desafioibm.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class PautaService {

    private PautaRepository repositorio;

    @Autowired
    public PautaService(PautaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Pauta cadastrarPauta(Pauta pauta) throws ExceptionHandler {
        Optional<Pauta> pautaExistente = this.repositorio.findByNome(pauta.getNome());

        if (pautaExistente.isEmpty()){
            this.repositorio.save(pauta);
            return pauta;
        }
        throw new ExceptionHandler("Pauta já cadastrada");
    }

    public Pauta buscaPauta(int id) {
        return this.repositorio.findById(id).orElse(null);
    }

    public List<Pauta> buscaTodasPautas() {
        return this.repositorio.findAll();
    }

    public Pauta editarPauta(Pauta pauta) {
        return this.repositorio.save(pauta);
    }

    public void excluirPauta(int pautaId){
        this.repositorio.deleteById(pautaId);
    }

    public Pauta abrirSessao(SessaoHandler sessao, int pautaId){
        Pauta pauta = this.repositorio.findById(pautaId).orElse(null);

        pauta.setStatus(true);

        if ( ! isEmpty(sessao.getTempoSessao())){
            pauta.setTempoSessao(sessao.getTempoSessao());
        }
        this.repositorio.save(pauta);
        return pauta;
    }

    public Pauta fecharSessao(int pautaId){
        Pauta pauta = this.repositorio.findById(pautaId).orElse(null);

        pauta.setStatus(false);
        pauta.setTempoSessao(1);

        this.repositorio.save(pauta);

        return pauta;
    }
}
