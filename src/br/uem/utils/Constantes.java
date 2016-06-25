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
}
