/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.puzzle;

import br.uem.heuristicas.HLinhaUm;
import br.uem.interfaces.IHeuristica;
import br.uem.utils.Constantes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 *
 * @author Massao
 */
public class Puzzle implements Comparable<Puzzle> {

  private static long contadorInstancia = 0;
  public static long contadorIteracoes = 0;
  private long id;
  private Puzzle predecessor;
  private Integer[][] mapa = new Integer[4][4];
  private double totalHeuristicas = 0;
  private double distanciaOrigem = 0;
  private Tuple posicaoZero;

  List<IHeuristica> listaHeuristicas = new ArrayList<>();

  public Puzzle(Integer[][] mapa, Puzzle predecessor) {
    this.setMapa(mapa);
    this.setPredecessor(predecessor);
    this.posicaoZero = posicaoCampoZero();
    carregarHeuristicas();
    id = ++contadorInstancia;
  }

  private void carregarHeuristicas() {
    listaHeuristicas.add(new HLinhaUm());
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
    if (totalHeuristicas > 0) {
      return totalHeuristicas;
    }

    double peso = 0;
    for (IHeuristica heuristica : listaHeuristicas) {
      totalHeuristicas += heuristica.executaCalculo(this);
      peso++;
    }

    if (peso > 0) {
      totalHeuristicas /= peso;
    }

    totalHeuristicas += distanciaOrigem;
    return totalHeuristicas;
  }

  public double getCalculoHeuristicas() {
    return totalHeuristicas - distanciaOrigem;
  }

  public double getDistanciaOrigem() {
    return distanciaOrigem;
  }

  private Tuple posicaoCampoZero() {
    return posicaoValor(Constantes.CAMPOABERTO);
  }

  private void setPredecessor(Puzzle predecessor) {
    this.predecessor = predecessor;
    if (predecessor != null) {
      this.distanciaOrigem = predecessor.distanciaOrigem + 1;
    }
  }

  private Integer[][] novoMapa(Tuple novaPosicaoAberta) {
    Integer[][] novoMapa = getMapa();
    novoMapa[posicaoZero.getColuna()][posicaoZero.getColuna()] = novoMapa[novaPosicaoAberta.getLinha()][novaPosicaoAberta.getColuna()];
    novoMapa[novaPosicaoAberta.getLinha()][novaPosicaoAberta.getColuna()] = Constantes.CAMPOABERTO;
    return novoMapa;
  }

  private Integer[][] criarMapaNovaLinha(Integer novaLinha) {
    return novoMapa(new Tuple(novaLinha, posicaoZero.getColuna()));
  }

  private Integer[][] criarMapaNovaColuna(Integer novaColuna) {
    return novoMapa(new Tuple(posicaoZero.getLinha(), novaColuna));
  }

  private Puzzle movimento(Direcao direcao) {
    Integer[][] novoMapa = null;
    int colunaAtual = posicaoZero.getColuna();
    int linhaAtual = posicaoZero.getLinha();
    switch (direcao) {
      case DIREITA: {
        if (colunaAtual < 3) {
          novoMapa = criarMapaNovaColuna(colunaAtual + 1);
        }

        break;
      }
      case ESQUERDA: {
        if (colunaAtual > 0) {
          novoMapa = criarMapaNovaColuna(colunaAtual - 1);
        }

        break;
      }
      case CIMA: {
        if (linhaAtual > 0) {
          novoMapa = criarMapaNovaLinha(linhaAtual - 1);
        }

        break;
      }
      case BAIXO: {
        if (linhaAtual < 3) {
          novoMapa = criarMapaNovaLinha(linhaAtual + 1);
        }

        break;
      }

      default: {
        return null;
      }
    }
    if (novoMapa != null) {
      return new Puzzle(novoMapa, this);
    }

    return null;
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

  public Tuple posicaoValor(Integer valor) {
    for (int linha = 0; linha < mapa.length; linha++) {
      for (int coluna = 0; coluna < mapa[linha].length; coluna++) {
        if (this.mapa[linha][coluna] == valor) {
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
  public int compareTo(Puzzle puzzle) {
    double diferenca = this.calculoHeuristicas();
    diferenca -= puzzle.calculoHeuristicas();

    if (diferenca == 0) {
      return 0;
    }

    return (int) (diferenca / Math.abs(diferenca));
  }
}
