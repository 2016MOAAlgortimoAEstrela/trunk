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
public class HLinhaQuatro implements IHeuristica{
  
  @Override
  public double executaCalculo(Puzzle puzzle) {
    double pesoH1 = 0.05;
    double pesoH2 = 0.05;
    double pesoH3 = 0.99;
    return ((pesoH1 * new HLinhaUm().executaCalculo(puzzle)) + (pesoH2 * new HLinhaDois().executaCalculo(puzzle)) + (pesoH3 * new HLinhaTres().executaCalculo(puzzle)));
  }
  
}
