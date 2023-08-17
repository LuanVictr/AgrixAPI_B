package com.betrybe.agrix.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * Cria a entidade Crop.
 */
@Entity
@Table(name = "crop")
public class Crop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  private String name;

  @Column(name = "planted_Area")
  private Double plantedArea;

  @Column(name = "planting_date")
  private LocalDate plantedDate;

  @Column(name = "harvest_date")
  private LocalDate harverstDate;

  public Crop() {}

  /**
   * Construtor da entidade Crop.
   *
   * @param name nome da Crop criada
   * @param plantedArea area plantada da crop criada
   * @param farm farm em que a crop sera relacionada
   */
  public Crop(String name, Double plantedArea, Farm farm,
      LocalDate plantedDate, LocalDate harverstDate) {
    this.name = name;
    this.plantedArea = plantedArea;
    this.farm = farm;
    this.plantedDate = plantedDate;
    this.harverstDate = harverstDate;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPlantedArea() {
    return plantedArea;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  public Farm getFarm() {
    return farm;
  }

  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getHarverstDate() {
    return harverstDate;
  }

  public void setHarverstDate(LocalDate harverstDate) {
    this.harverstDate = harverstDate;
  }
}
