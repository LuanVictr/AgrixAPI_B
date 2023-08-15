package com.betrybe.agrix.exceptions;

/**
 * Cria uma excecao customizada para casos em que a fazenda
 * nao e encontrada.
 */
public class FarmNotFoundException extends Exception {

  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
