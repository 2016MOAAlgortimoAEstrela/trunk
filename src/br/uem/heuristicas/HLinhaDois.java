/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.heuristicas;

import br.uem.interfaces.IHeuristica;
import br.uem.puzzle.Puzzle;
import br.uem.utils.Constantes;
import br.uem.utils.Conversor;
import br.uem.utils.Impressora;

/**
 *
 * @author Massao
 */
public class HLinhaDois implements IHeuristica {

  @Override
  public double executaCalculo(Puzzle puzzle) {
    int resultado = 0;
    int proximo = -1;
    Integer[][] mapaAtual = puzzle.getMapa();
    Integer[] mapaEmLinha = Conversor.ConverteMatrizEspiralParaArray(mapaAtual);

    mapaEmLinha[14] = mapaAtual[2][2];
    mapaEmLinha[15] = mapaAtual[1][2];

    proximo = -1;
    for (int indice = 0; indice < mapaEmLinha.length; indice++) {
      proximo = (proximo < 0) ? mapaEmLinha[indice] : (proximo + 1);
      if (mapaEmLinha[indice] != proximo) {
        if (mapaEmLinha[indice] == Constantes.CAMPOABERTO) {
          proximo = -1;
        } else {
          proximo = mapaEmLinha[indice];
        }

        resultado++;
      }
    }

    return resultado;
  }
}
