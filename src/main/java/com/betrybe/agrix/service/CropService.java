package com.betrybe.agrix.service;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.repositories.CropRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Camada service de tudo que interage com a entidade crop.
 */
@Service
public class CropService {

  private CropRepository cropRepository;

  /**
   * Construtor da camada service.
   *
   * @param cropRepository Repositorio recebido por injeção
   *                       de dependencia
   */
  @Autowired
  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  /**
   * Salva a Crop pela id da Farm.
   *
   * @param newCrop recebe uma nova crop a ser salva.
   * @return retorna a crop salva.
   */
  public Crop saveCropByFarmId(Crop newCrop) {

    Crop cropToCreate = this.cropRepository.save(newCrop);

    Optional<Crop> cropToFound = this.cropRepository.findById(cropToCreate.getId());

    Crop cropFound = cropToFound.get();

    return cropFound;

  }

  /**
   * Método que busca no banco de dados todas as plantacoes.
   *
   * @return retorna uma lista com todas as plantacoes.
   */
  public List<Crop> getAllCrops() {
    List<Crop> allCrops = this.cropRepository.findAll();

    return allCrops;
  }

  /**
   * Método que busca plantacao baseada no id.
   *
   * @param id recebe o id buscado.
   * @return retorna um opcional com a plantacao retornada.
   */
  public Crop getCropById(Long id) throws CropNotFoundException {
    Optional<Crop> cropToFound = this.cropRepository.findById(id);

    if (cropToFound.isEmpty()) {
      throw new CropNotFoundException();
    }

    return cropToFound.get();
  }

}
