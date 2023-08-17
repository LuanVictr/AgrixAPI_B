package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Cria a camada de servico para as rotas /Fertilizer.
 */
@Service
public class FertilizerService {

  private FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Cria um novo fertilizante.
   *
   * @param fertilizerInfo informacoes do fertilizante a ser criado
   * @return retorna as informacoes do fertilizante criado
   */
  public Fertilizer createFertilizer(Fertilizer fertilizerInfo) {

    Fertilizer createdFertilizer = this.fertilizerRepository.save(fertilizerInfo);
    return createdFertilizer;
  }

  public List<Fertilizer> getAllFertilizers() {
    return this.fertilizerRepository.findAll();
  }
}
