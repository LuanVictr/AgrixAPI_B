package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.controller.dto.FertilizerResponseDto;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @Autowired
  public FertilizersController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

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
}
