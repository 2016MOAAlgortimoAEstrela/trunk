/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.main;

import br.uem.astar.AStar;
import br.uem.heuristicas.HLinhaQuatro;
import br.uem.puzzle.Puzzle;
import br.uem.utils.Constantes;

/**
 *
 * @author Massao
 */
public class Main {

  public static void main(String[] args) {
    try {
      //Integer[][] mapa = Constantes.MAPA_VINTESEIS_MOVIMENTOS;
      String mapa = Constantes.MAPA_TESTE_01;

      double tempo = System.currentTimeMillis();
      Puzzle puzzle = new AStar().executa(mapa, new HLinhaQuatro());
      System.out.printf("Tempo: %f\n", ((System.currentTimeMillis() - tempo) / 1000.0));
      System.out.printf("%d movimentos!\n", (int)puzzle.getDistanciaOrigem());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
