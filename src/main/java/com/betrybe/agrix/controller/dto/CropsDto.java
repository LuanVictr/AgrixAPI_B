package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;

/**
 * Dto para criacao de Crops.
 *
 * @param name nome da Crop criada
 * @param plantedArea area plantada da Crop criada
 */
public record CropsDto(String name, Double plantedArea) {

  public Crop toCrop(Farm farm) {
    return new Crop(name, plantedArea, farm);
  }
}
