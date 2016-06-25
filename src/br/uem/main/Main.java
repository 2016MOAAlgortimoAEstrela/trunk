/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.main;

import br.uem.astar.AStar;
import br.uem.puzzle.Puzzle;
import br.uem.utils.Constantes;

/**
 *
 * @author Massao
 */
public class Main {

  public static void main(String[] args) {
    try {
      Integer[][] mapa = Constantes.MAPA_ONZE_MOVIMENTOS;

      AStar aStar = new AStar();
      Puzzle puzzle = aStar.executa(mapa);
      System.out.printf("%d movimentos!\n", puzzle.contadorIteracoes);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
