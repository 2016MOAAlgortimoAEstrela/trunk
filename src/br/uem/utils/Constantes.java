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
public class Constantes {

  public static final Integer CAMPOABERTO = 0;
  public static final Integer[][] ESTADOFINAL = new Integer[][]{
    {1, 12, 11, 10},
    {2, 13, 0, 9},
    {3, 14, 15, 8},
    {4, 5, 6, 7}};

  public static final Integer[][] MAPA_SEIS_MOVIMENTOS = new Integer[][]{
    {1, 12, 11, 10},
    {0, 13, 15, 9},
    {2, 14, 6, 8},
    {3, 4, 5, 7}};

  public static final Integer[][] MAPA_ONZE_MOVIMENTOS = new Integer[][]{
    {12, 11, 10, 9},
    {1, 13, 15, 0},
    {2, 14, 6, 8},
    {3, 4, 5, 7}};

  public static final Integer[][] MAPA_SETE_MOVIMENTOS = new Integer[][]{
    {1, 12, 0, 11},
    {2, 13, 15, 10},
    {3, 14, 6, 9},
    {4, 5, 7, 8}};

  public static final Integer[][] MAPA_QUINZE_MOVIMENTOS = new Integer[][]{
    {2, 1, 12, 11},
    {3, 0, 15, 10},
    {4, 13, 6, 9},
    {5, 14, 7, 8}};

  public static final Integer[][] MAPA_VINTE_MOVIMENTOS = new Integer[][]{
    {2, 1, 12, 11},
    {3, 15, 6, 10},
    {4, 0, 7, 9},
    {5, 13, 14, 8}};

  public static final Integer[][] MAPA_VINTESEIS_MOVIMENTOS = new Integer[][]{
    {12, 3, 11, 10},
    { 0, 13, 1, 15}, 
    {2, 14, 8, 7}, 
    {4, 5, 9, 6}};

  public static final Integer[][] MAPA_DESCONHECIDO = new Integer[][]{
    {11, 15, 4, 5},
    {0, 14, 2, 10},
    {3, 6, 1, 9},
    {13, 12, 8, 7}};
}

/*

Casos para serem utilizados no relatório.
12 2 11 10 1 13 9 8 3 5 14 15 4 0 6 7 
2 1 9 11 12 13 10 0 3 14 15 8 4 5 6 7
2 1 9 11 3 12 13 10 14 15 6 8 4 0 5 7 
1 12 10 13 2 6 11 0 3 14 15 9 4 5 7 8 
2 0 14 10 13 15 12 1 3 5 9 8 4 6 11 7 
1 14 11 10 2 6 12 8 3 0 15 9 5 4 7 13 
12 3 11 10 0 13 1 15 2 14 8 7 4 5 9 6 
13 1 11 10 0 3 9 7 14 12 4 15 2 6 8 5 
12 14 15 10 3 1 11 13 6 0 7 5 4 2 8 9 
3 1 10 8 13 2 11 14 15 0 7 12 4 6 5 9 
*/