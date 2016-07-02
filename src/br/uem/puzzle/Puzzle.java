/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.puzzle;

import br.uem.heuristicas.HLinhaCinco;
import br.uem.heuristicas.HLinhaDois;
import br.uem.heuristicas.HLinhaQuatro;
import br.uem.heuristicas.HLinhaTres;
import br.uem.heuristicas.HLinhaUm;
import br.uem.interfaces.IHeuristica;
import br.uem.utils.Constantes;
import br.uem.utils.Conversor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import jdk.internal.dynalink.linker.ConversionComparator;

/**
 *
 * @author Massao
 */
public class Puzzle implements Comparator<Puzzle> {

  private Puzzle predecessor;
  private Integer[][] mapa = new Integer[4][4];
  private double totalHeuristicas = -1;
  private double distanciaOrigem = 0;
  private Tuple posicaoZero;
  private IHeuristica heuristica;
  private HashMap<Integer, Tuple> mapaAtual = new HashMap<Integer, Tuple>();
  private HashMap<Integer, Tuple> mapaFinal = new HashMap<Integer, Tuple>();
  
  public Puzzle(){
    
  }

  public Puzzle(Integer[][] mapa, Puzzle predecessor, IHeuristica heuristica) {
    this.setMapa(mapa);
    this.setPredecessor(predecessor);
    this.posicaoZero = posicaoCampoZero();
    this.heuristica = heuristica;
    Conversor.ConverterParaHashMap(this, mapaAtual, mapaFinal);
  }

  public Integer[][] getMapa() {
    Integer[][] copia = new Integer[4][4];
    for (int contador = 0; contador < mapa.length; contador++) {
      System.arraycopy(mapa[contador], 0, copia[contador], 0, mapa.length);
    }

    return copia;
  }

  private void setMapa(Integer[][] mapa) {
    for (int contador = 0; contador < mapa.length; contador++) {
      System.arraycopy(mapa[contador], 0, this.mapa[contador], 0, mapa.length);
    }
  }

  public double calculoHeuristicas() {
    if (!(totalHeuristicas < 0)) {
      return totalHeuristicas;
    }

    totalHeuristicas = 0;
    totalHeuristicas += heuristica.executaCalculo(this);    
    totalHeuristicas += distanciaOrigem;
    return totalHeuristicas;
  }

  public double getCalculoHeuristicas() {
    return totalHeuristicas;
  }

  public double getDistanciaOrigem() {
    return distanciaOrigem;
  }

  private Tuple posicaoCampoZero() {
    return posicaoValor(this.mapa, Constantes.CAMPOABERTO);
  }

  private void setPredecessor(Puzzle predecessor) {
    this.predecessor = predecessor;
    if (predecessor != null) {
      this.distanciaOrigem = predecessor.distanciaOrigem + 1;
    }
  }

  private Integer[][] novoMapa(Tuple novaPosicaoAberta) {
    Integer[][] novoMapa = getMapa();
    novoMapa[posicaoZero.getLinha()][posicaoZero.getColuna()] = mapa[novaPosicaoAberta.getLinha()][novaPosicaoAberta.getColuna()];
    novoMapa[novaPosicaoAberta.getLinha()][novaPosicaoAberta.getColuna()] = Constantes.CAMPOABERTO;
    return novoMapa;
  }

  private Puzzle movimento(Direcao direcao) {
    Integer[][] novoMapa = null;
    int colunaAtual = posicaoZero.getColuna();
    int linhaAtual = posicaoZero.getLinha();
    switch (direcao) {
      case DIREITA: {
        if (colunaAtual < 3) {
          novoMapa = novoMapa(new Tuple(linhaAtual, colunaAtual + 1));
        }

        break;
      }
      case ESQUERDA: {
        if (colunaAtual > 0) {
          novoMapa = novoMapa(new Tuple(linhaAtual, colunaAtual - 1));
        }

        break;
      }
      case CIMA: {
        if (linhaAtual > 0) {
          novoMapa = novoMapa(new Tuple(linhaAtual - 1, colunaAtual));
        }

        break;
      }
      case BAIXO: {
        if (linhaAtual < 3) {
          novoMapa = novoMapa(new Tuple(linhaAtual + 1, colunaAtual));
        }

        break;
      }

      default: {
        return null;
      }
    }
    
    return (novoMapa != null) ? new Puzzle(novoMapa, this, this.heuristica) : null;
  }

  public List<Puzzle> filhos() {
    List<Puzzle> filhos = new ArrayList<>();
    for (Direcao direcao : Direcao.values()) {
      Puzzle puzzle = movimento(direcao);
      if (puzzle != null) {
        filhos.add(puzzle);
      }
    }

    return filhos;
  }

  public Tuple posicaoValor(Integer[][] mapa, Integer valor) {
    for (int linha = 0; linha < mapa.length; linha++) {
      for (int coluna = 0; coluna < mapa[linha].length; coluna++) {
        if (mapa[linha][coluna] == valor) {
          return new Tuple(linha, coluna);
        }
      }
    }

    return null;
  }

  public boolean comparaMapa(Integer[][] outroMapa) {
    for (int linha = 0; linha < this.mapa.length; linha++) {
      for (int coluna = 0; coluna < this.mapa.length; coluna++) {
        if (!Objects.equals(this.mapa[linha][coluna], outroMapa[linha][coluna])) {
          return false;
        }
      }
    }

    return true;
  }

  public double distanciaOrigemEqual(PriorityQueue<Puzzle> filaAbertos) {
    for (Puzzle puzzle : filaAbertos) {
      if (puzzle.equals(this)) {
        return puzzle.getDistanciaOrigem();
      }
    }
    return 0;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    return hash;
  }

  @Override
  public boolean equals(Object puzzle) {
    if (puzzle == null) {
      return false;
    }

    if (getClass() != puzzle.getClass()) {
      return false;
    }

    Puzzle other = (Puzzle) puzzle;

    return (this.comparaMapa(other.mapa));
  }

  @Override
  public int compare(Puzzle p1, Puzzle p2) {
    double diferenca = p1.calculoHeuristicas();
    diferenca -= p2.calculoHeuristicas();

    if (diferenca == 0) {
      return 0;
    }

    return (int) (diferenca / Math.abs(diferenca));    
  }
}
