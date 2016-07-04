/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.utils;

/**
 *
 * @author Massao
 */
public class Impressora {

  public static void ImprimeMapa(Integer[][] mapa,String mensagem) {
    String spool = String.format("%s:\n", mensagem);
    int linhaAtual = 0;
    for (Integer linha = 0; linha < mapa.length; linha++) {
      for (Integer coluna = 0; coluna < mapa[linha].length; coluna++) {
        if (linha != linhaAtual) {
          spool += "\n";
          linhaAtual = linha;
        }

        spool += String.format("%02d ", mapa[linha][coluna]);
      }
    }

    spool += "\n------------\n";
    System.out.println(spool);
  }

  public static void ImprimeMapa(Integer[] mapa, String mensagem) {
    String spool = String.format("%s:\n", mensagem);
    for (int coluna = 0; coluna < mapa.length; coluna++) {
      spool += String.format("%02d ", mapa[coluna]);
    }
    spool += "\n------------\n";
    System.out.println(spool);
  }

}
