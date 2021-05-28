package com.desafio.desafioibm.repository;

import com.desafio.desafioibm.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer> {
    Optional<Pauta> findByNome(String nome);
}
