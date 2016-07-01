/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.heuristicas;

import br.uem.interfaces.IHeuristica;
import br.uem.puzzle.Puzzle;
import br.uem.puzzle.Tuple;
import br.uem.utils.Constantes;
import br.uem.utils.Conversor;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Massao
 */
public class HLinhaTres implements IHeuristica{

  @Override
  public double executaCalculo(Puzzle puzzle) {    
    int somador = 0;
    Integer[][] mapa = puzzle.getMapa();
    Tuple posicaoAtual;
    Tuple posicaoFinal;
    HashMap<Integer, Tuple> mapaAtual = new HashMap<Integer, Tuple>();
    HashMap<Integer, Tuple> mapaFinal = new HashMap<Integer, Tuple>();
    for (int linha = 0; linha < mapa.length; linha++) {
      for (int coluna = 0; coluna < mapa[linha].length; coluna++) {
          mapaAtual.put(mapa[linha][coluna], new Tuple(linha, coluna));
      }
    }
    
    for (int linha = 0; linha < Constantes.ESTADOFINAL.length; linha++) {
      for (int coluna = 0; coluna < Constantes.ESTADOFINAL[linha].length; coluna++) {
          mapaFinal.put(Constantes.ESTADOFINAL[linha][coluna], new Tuple(linha, coluna));
      }
    }
    
    for (int valor = 0; valor < mapaAtual.size(); valor++){
       posicaoAtual = mapaAtual.get(valor);
       posicaoFinal = mapaFinal.get(valor);
       somador += (Math.abs(posicaoFinal.getLinha() - posicaoAtual.getLinha())) + (Math.abs(posicaoFinal.getColuna()- posicaoAtual.getColuna()));
    }
    
    return somador;
  }
  
}
