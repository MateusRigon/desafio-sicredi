package com.desafio.desafioibm.repository;

import com.desafio.desafioibm.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {
    Optional<Voto> findByAssociadoId(int associadoId);

    List<Voto> findByPautaId(int pautaId);

    List<Voto> findByResultado(String resultado);
}
