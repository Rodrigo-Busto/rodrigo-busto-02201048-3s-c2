package com.bandtec.webcontinuada02.repository;

import com.bandtec.webcontinuada02.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {

    List<Lutador> findAllOrderByForcaGolpeAsc();

    Integer countByVivoTrue();

    List<Lutador> findByVivoFalse();
}
