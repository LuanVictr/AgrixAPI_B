package com.betrybe.agrix.exceptions;

/**
 * Excecao customizada para caso fertilizante nao seja encontrado.
 */
public class FertilizerNotFoundException extends Exception {

  /**
   * Construtor da excecao customizada.
   */
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
