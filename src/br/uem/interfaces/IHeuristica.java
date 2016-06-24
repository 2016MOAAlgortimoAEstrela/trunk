/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.interfaces;

import br.uem.puzzle.Puzzle;

/**
 *
 * @author Massao
 */
public interface IHeuristica {
  public double executaCalculo(Puzzle puzzle);
}
