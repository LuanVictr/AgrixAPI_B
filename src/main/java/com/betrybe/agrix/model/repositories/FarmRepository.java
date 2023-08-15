package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Cria o repositorio Farm.
 */
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
