/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.heuristicas;

import br.uem.interfaces.IHeuristica;
import br.uem.puzzle.Puzzle;

/**
 *
 * @author Massao
 */
public class HLinhaCinco implements IHeuristica{

  @Override
  public double executaCalculo(Puzzle puzzle) {
    return Double.max(Double.max(new HLinhaUm().executaCalculo(puzzle), new HLinhaDois().executaCalculo(puzzle)), new HLinhaTres().executaCalculo(puzzle));
  }
  
}
