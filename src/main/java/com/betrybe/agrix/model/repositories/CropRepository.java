package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Cria o repositorio Crop.
 */
public interface CropRepository extends JpaRepository<Crop, Long> {
}
