/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Massao
 */
public class HLinhaUm implements IHeuristica {
  int[][] mapaAtual;

  public HLinhaUm(int[][] mapaAtual) {
    this.mapaAtual = mapaAtual;
    
  }
  @Override
  public double peso() {
    return 0;
  }

  @Override
  public double valor(AStar aStar) {
    double resultado = 0;
    int[][] estadoFinal = aStar.getMapaFinal();
    int[][] mapaAtual = aStar.getMapa();
    
    for (int linha = 0; linha < estadoFinal.length; linha++)
      for (int coluna = 0; coluna < estadoFinal[linha].length; coluna++) 
        if (estadoFinal[linha][coluna] != mapaAtual[linha][coluna])
          resultado++;
    
    return resultado;
  }
}
