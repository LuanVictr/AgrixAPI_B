package com.betrybe.agrix.exceptions;

/**
 * Excecao customizada que sera lancada caso a plantacao
 * buscada nao seja encontrada.
 */
public class CropNotFoundException extends Exception {

  /**
   * Construtor da excecao customizada chamando o metodo super.
   */
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
