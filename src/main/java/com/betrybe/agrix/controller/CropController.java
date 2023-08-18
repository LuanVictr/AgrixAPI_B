package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropResponseDto;
import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de todos os metodos da rota /crops.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private CropService cropService;
  private FertilizerService fertilizerService;

  /**
   * Construtor do controller CropController.
   *
   * @param cropService recebe a camada de servico por
   *                    injecao de dependencia.
   */
  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Mapea a rota GET /crops com a funcao de retornar uma lista com
   * todas as plantacoes existentes no banco.
   *
   * @return retorna uma lista de CropResponseDto de todas as plantacoes.
   */
  @GetMapping
  public ResponseEntity<List<CropResponseDto>> getAllCrops() {

    List<Crop> allCrops = this.cropService.getAllCrops();

    List<CropResponseDto> cropsResponse = allCrops.stream()
        .map(crop -> new CropResponseDto(crop.getId(), crop.getName(),
        crop.getPlantedArea(), crop.getFarm().getId(), crop.getPlantedDate(),
            crop.getHarverstDate()))
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(cropsResponse);
  }

  /**
   * Mapea a rota GET /crops/id para retornar as informacoes de uma crop
   * especifica.
   *
   * @param id id da crop buscada passada por Path
   * @return retorna um ResponseEntity com a crop especifica ou um erro
   *     customizado caso ela nao seja encontrada.
   */
  @GetMapping("/{id}")
  public ResponseEntity getCropById(@PathVariable Long id) {
    try {

      Crop cropFound = this.cropService.getCropById(id);

      CropResponseDto cropResponse = new CropResponseDto(cropFound.getId(),
          cropFound.getName(), cropFound.getPlantedArea(), cropFound.getFarm().getId(),
          cropFound.getPlantedDate(), cropFound.getHarverstDate());

      return ResponseEntity.status(HttpStatus.OK).body(cropResponse);

    } catch (CropNotFoundException cropNotFoundException) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cropNotFoundException.getMessage());

    }
  }

  /**
   * Rota que permite a busca de plantacoes baseadas na data de colheita.
   *
   * @param start data de inicio da busca
   * @param end data de fim da busca
   * @return retorna uma lista de plantacoes que atendem o requisito
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropResponseDto>> searchCropByDate(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end) {

    List<Crop> allCrops = this.cropService.searchCropByDate(start, end);

    List<CropResponseDto> allCropsConverted = allCrops.stream()
        .map(crop -> new CropResponseDto(crop.getId(), crop.getName(),
            crop.getPlantedArea(), crop.getFarm().getId(), crop.getPlantedDate(),
            crop.getHarverstDate()))
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(allCropsConverted);

  }

  /**
   * Método que associa plantacao a um fertilizante.
   *
   * @param cropId Id da plantacao a ser associada
   * @param fertilizerId id do fertilizante a ser associado
   * @return retorna se a operacao foi bem sucedida
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity createFertilizerByCropId(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    try {
      Crop cropFound = this.cropService.getCropById(cropId);
      Fertilizer fertilizerFound = this.fertilizerService.getFertilizerById(
          fertilizerId
      );
      cropFound.setFertilizers(fertilizerFound);
      return ResponseEntity.status(HttpStatus.CREATED).body(
          "Fertilizante e plantação associados com sucesso!"
      );
    } catch (FertilizerNotFoundException fertilizerNotFoundException) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
          fertilizerNotFoundException.getMessage()
      );

    } catch (CropNotFoundException cropNotFoundException) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
          cropNotFoundException.getMessage()
      );

    }

  }
}
