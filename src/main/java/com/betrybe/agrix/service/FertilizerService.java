package com.betrybe.agrix.service;

import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
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

  /**
   * Metodo que retorna todos os fertilizantes cadastrados.
   *
   * @return retorna uma lista com todos os fertilizantes.
   */

  public List<Fertilizer> getAllFertilizers() {
    return this.fertilizerRepository.findAll();
  }

  /**
   * Metodo que retorna o fertilizante baseado no id.
   *
   * @param id id do fertilizante buscado
   * @return retorna o fertilizante buscado
   * @throws FertilizerNotFoundException retorna um erro caso
   *     o fertilizante nao seja encontrado
   */
  public Fertilizer getFertilizerById(Long id) throws FertilizerNotFoundException {
    Optional<Fertilizer> optionalFertilizer = this.fertilizerRepository.findById(id);

    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    return optionalFertilizer.get();
  }
}
