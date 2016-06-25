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
import java.util.List;

/**
 *
 * @author Massao
 */
public class HLinhaTres implements IHeuristica{

  @Override
  public double executaCalculo(Puzzle puzzle) {
    List<Integer> mapaAtual = Conversor.ConverteArrayParaLista(Conversor.ConverteMatrizParaArray(puzzle.getMapa()));
    List<Integer> mapaFinal = Conversor.ConverteArrayParaLista(Conversor.ConverteMatrizParaArray(Constantes.ESTADOFINAL));
    int somador = 0;
    for (int valor = 0; valor < mapaAtual.size(); valor++){
      somador += Math.abs(mapaAtual.indexOf(valor) - mapaFinal.indexOf(valor));
    }
    
    return somador;
  }
  
}
