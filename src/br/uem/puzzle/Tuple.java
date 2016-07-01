/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.puzzle;

/**
 *
 * @author Massao
 */
public class Tuple {
  private final int linha;
  private final int coluna;

  public Tuple(int linha, int coluna) {
      this.linha = linha;
      this.coluna = coluna;
  }

  public int getLinha() {
      return linha;
  }

  public int getColuna() {
      return coluna;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 29 * hash + this.linha;
    hash = 29 * hash + this.coluna;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    
    if (getClass() != obj.getClass()) {
      return false;
    }
    
    final Tuple other = (Tuple) obj;
    if (this.linha != other.linha) {
      return false;
    }
    
    return this.coluna == other.coluna;
  }
}