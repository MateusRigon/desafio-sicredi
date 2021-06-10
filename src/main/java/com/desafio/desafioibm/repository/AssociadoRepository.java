package com.desafio.desafioibm.repository;

import com.desafio.desafioibm.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Integer> {
    Optional<Associado> findByCpf(String cpf);
    Associado findById(int id);
}
