package com.betrybe.agrix.service;

import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe FarmService, camada de serviço dos endpoints /farm.
 */
@Service
public class FarmService {

  private FarmRepository farmRepository;

  /**
   * Funcao construtora da classe FarmService.
   *
   * @param farmRepository repositorio recebido do spring
   *                       por injeção de dependencia
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public List<Farm> getFarms() {
    List<Farm> allFarms = this.farmRepository.findAll();
    return allFarms;
  }

  public Farm createFarm(Farm newFarm) {
    Farm createdFarm = this.farmRepository.save(newFarm);
    return createdFarm;
  }

  public Optional<Farm> getFarmById(Long id) {
    Optional<Farm> farmFound = this.farmRepository.findById(id);
    return farmFound;
  }

  /**
   * Metodo que retorna todas as plantacoes de uma fazenda.
   *
   * @param id id da fazenda buscada
   * @return Retorna uma lista com todas as plantacoes
   * @throws FarmNotFoundException caso a fazenda nao seja
   *     encontrada, cria-se uma excecao.
   */
  public List<Crop> getAllCropsFromFarm(Long id) throws FarmNotFoundException {
    Optional<Farm> farmToFound = this.farmRepository.findById(id);

    if (farmToFound.isEmpty()) {
      throw new FarmNotFoundException();
    }

    Farm farmFound = farmToFound.get();
    return farmFound.getCrops();
  }

}
