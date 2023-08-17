package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.controller.dto.FertilizerResponseDto;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Cria a camada de controle das rotas /fertilizers.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizersController {

  private FertilizerService fertilizerService;

  /**
   * Construtor da camada de controle das rotas /fertilizers.
   *
   * @param fertilizerService recebe uma instancia da camada de
   *                          servico por injecao de dependencia
   */
  @Autowired
  public FertilizersController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Método que mapea a rota POST /fertilizers para a criacao de um novo
   *     fertilizante.
   *
   * @param newFertilizerInfo informacoes do fertilizante a ser criado
   * @return retorna o novo fertilizante criado
   */
  @PostMapping
  public ResponseEntity<FertilizerResponseDto>
      createFertilizer(@RequestBody FertilizerDto newFertilizerInfo) {
    Fertilizer newFertilizer = newFertilizerInfo.toFertilizer();

    Fertilizer createdFertilizer = this.fertilizerService.createFertilizer(newFertilizer);
    FertilizerResponseDto fertilizerResponse = new FertilizerResponseDto(
          createdFertilizer.getId(), createdFertilizer.getName(), createdFertilizer.getBrand(),
          createdFertilizer.getComposition()
      );

    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerResponse);
  }

  /**
   * Metodo que retorna todos os fertilizantes mapeado na rota
   *     GET /fertilizers.
   *
   * @return retorna todos os fertilizantes
   */
  @GetMapping
  public ResponseEntity<List<Fertilizer>> getAllFertilizers() {
    List<Fertilizer> allFertilizers = this.fertilizerService.getAllFertilizers();
    return ResponseEntity.status(HttpStatus.OK).body(allFertilizers);
  }

  /**
   * Metodo que retorna o fertilizante buscado pelo id, mapeado
   *     na rota GET /fertilizers/id.
   *
   * @param fertilizerId id do fertilizante buscado
   * @return retorna o fertilizante buscado
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity getFertilizerById(@PathVariable Long fertilizerId) {
    try {

      Fertilizer fertilizerFound = this.fertilizerService.getFertilizerById(fertilizerId);
      return ResponseEntity.status(HttpStatus.OK).body(fertilizerFound);

    } catch (FertilizerNotFoundException fertilizerNotFoundException) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(fertilizerNotFoundException.getMessage());

    }
  }
}
