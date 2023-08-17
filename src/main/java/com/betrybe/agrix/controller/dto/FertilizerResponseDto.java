package com.betrybe.agrix.controller.dto;

/**
 * Cria a camada Dto de resposta para as rotas /fertilizers.
 *
 * @param id id do fertilizante
 * @param name nome do fertilizante
 * @param brand marca do fertilizante
 * @param composition composicao do fertilizante
 */
public record FertilizerResponseDto(Long id, String name, String brand,
                                    String composition) {
}
