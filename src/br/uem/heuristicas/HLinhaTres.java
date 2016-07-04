/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.heuristicas;

import br.uem.interfaces.IHeuristica;
import br.uem.puzzle.Puzzle;
import br.uem.puzzle.Tuple;

/**
 *
 * @author Massao
 */
public class HLinhaTres implements IHeuristica{

  @Override
  public double executaCalculo(Puzzle puzzle) {    
    int somador = 0;
    Tuple posicaoAtual;
    Tuple posicaoFinal;
    
    for (int valor = 0; valor < puzzle.mapaAtual.size(); valor++){
       posicaoAtual = puzzle.mapaAtual.get(valor);
       posicaoFinal = puzzle.mapaFinal.get(valor);
       somador += (Math.abs(posicaoFinal.getLinha() - posicaoAtual.getLinha())) + (Math.abs(posicaoFinal.getColuna()- posicaoAtual.getColuna()));
    }
    
    return somador;
  }
  
}
