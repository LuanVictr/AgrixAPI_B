package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Fertilizer;

/**
 * Cria a Dto de entrada de um fertilizante.
 *
 * @param id id do fertilizante novo
 * @param name nome do fertilizante novo
 * @param brand marca do fertilizante novo
 * @param composition composicao do fertilizante novo
 */
public record FertilizerDto(Long id, String name, String brand,
                            String composition) {

  /**
   * Converte o fertilizante de Dto para fertilizante.
   *
   * @return retorna um novo fertilizante
   */
  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition);
  }
}
