/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.utils;

import br.uem.puzzle.Puzzle;
import br.uem.puzzle.Tuple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Massao
 */
public class Conversor {

  public static Integer[] ConverteMatrizEspiralParaArray(Integer[][] mapaAtual) {
    Integer[] mapaEmLinha = new Integer[mapaAtual.length * mapaAtual[0].length];
    int contador = 0;
    for (int linha = 0; linha < mapaAtual.length; linha++) {
      mapaEmLinha[contador] = mapaAtual[linha][0];
      contador++;
    }

    for (int coluna = 1; coluna < mapaAtual[3].length; coluna++) {
      mapaEmLinha[contador] = mapaAtual[3][coluna];
      contador++;
    }

    for (int linha = 2; linha >= 0; linha--) {
      mapaEmLinha[contador] = mapaAtual[linha][3];
      contador++;
    }

    for (int coluna = 2; coluna > 0; coluna--) {
      mapaEmLinha[contador] = mapaAtual[0][coluna];
      contador++;
    }

    for (int linha = 1; linha < 3; linha++) {
      mapaEmLinha[contador] = mapaAtual[linha][1];
      contador++;
    }

    return mapaEmLinha;
  }

  public static Integer[] ConverteMatrizParaArray(Integer[][] mapaAtual) {
    Integer[] mapaEmLinha = new Integer[mapaAtual.length * mapaAtual[0].length];
    int contador = 0;
    for (Integer[] linhas : mapaAtual) {
      for (Integer valor : linhas) {
        mapaEmLinha[contador] = valor;
        contador++;
      }
    }

    return mapaEmLinha;
  }

  public static List<Integer> ConverteArrayParaLista(Integer[] array) {
    List<Integer> lista = new ArrayList<Integer>();
    for (int indice = 0; indice < array.length; indice++) {
      lista.add(array[indice]);
    }

    return lista;
  }

  public static List<Integer> ConverteMatrizParaLista(Integer[][] matriz, boolean espiral) {
    if (espiral) {
      return ConverteArrayParaLista(ConverteMatrizEspiralParaArray(matriz));
    }

    return ConverteArrayParaLista(ConverteMatrizParaArray(matriz));
  }
  
  public static void ConverterParaHashMap(Puzzle puzzle, HashMap<Integer, Tuple> mapaAtual, HashMap<Integer, Tuple> mapaFinal){
    mapaAtual.clear();
    mapaFinal.clear();
    for (int linha = 0; linha < puzzle.mapa.length; linha++) {
      for (int coluna = 0; coluna < puzzle.mapa[linha].length; coluna++) {
          mapaAtual.put(puzzle.mapa[linha][coluna], new Tuple(linha, coluna));
          mapaFinal.put(Constantes.ESTADOFINAL[linha][coluna], new Tuple(linha, coluna));
      }
    }
  }
}
