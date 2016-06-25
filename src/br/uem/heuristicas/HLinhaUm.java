/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.heuristicas;

import br.uem.utils.Constantes;
import br.uem.interfaces.IHeuristica;
import br.uem.puzzle.Puzzle;

/**
 *
 * @author Massao
 */
public class HLinhaUm implements IHeuristica {  
  @Override
  public double executaCalculo(Puzzle puzzle) {
    double resultado = 0;
    Integer[][] estadoFinal = Constantes.ESTADOFINAL;
    Integer[][] mapaAtual = puzzle.getMapa();
    
    for (Integer linha = 0; linha < estadoFinal.length; linha++)
      for (Integer coluna = 0; coluna < estadoFinal[linha].length; coluna++) 
        if (estadoFinal[linha][coluna] != mapaAtual[linha][coluna])
          resultado++;
    
    return resultado;
  }
}
