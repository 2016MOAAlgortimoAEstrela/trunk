package main;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Massao
 */
public class AStar{
  private AStar predecessor;
  private final int[][] ESTADOFINAL = new int[][]{{1, 12, 11, 10},
                                                  {2, 13,  0,  9},
                                                  {3, 14, 15,  8},
                                                  {4,  5,  6,  7}};
  private final int CAMPOABERTO = 0;
  private int[][] mapa = new int[4][4];
  private double totalHeuristicas = 0;
  private double nivel = 0;
  private Pair posicaoAberta;
  
  List<IHeuristica> listaHeuristicas = new ArrayList<>();
  
  public AStar(int[][] mapa, AStar predecessor){
    this.setMapa(mapa);
    this.setPredecessor(predecessor);
    this.posicaoAberta = posicaoCampoAberto();
    carregarHeuristicas();
  }
  
  private void carregarHeuristicas(){    
    listaHeuristicas.add(new HLinhaUm(mapa));
  }

  public int[][] getMapa() {
    return mapa;
  }

  private void setMapa(int[][] mapa) {
    this.mapa = mapa;
  }

  public int[][] getMapaFinal() {
    return ESTADOFINAL;
  }
  
  public double calculoHeuristicas(){
    if (totalHeuristicas > 0)
      return totalHeuristicas;
    
    double somaPesos = 0;
    somaPesos = listaHeuristicas.stream().map((heuristica) -> {
      totalHeuristicas += heuristica.peso() * heuristica.valor(this);
      return heuristica;
    }).map((heuristica) -> heuristica.peso()).reduce(somaPesos, (accumulator, _item) -> accumulator + _item);
    
    totalHeuristicas /= somaPesos;
    totalHeuristicas += nivel;
    return totalHeuristicas;
  }

  private Pair posicaoCampoAberto() {
    int[][] mapa = this.getMapa();
    for (int linha = 0; linha < mapa.length; linha++) 
      for (int coluna = 0; coluna < mapa[linha].length; coluna++) 
        if (mapa[linha][coluna] == CAMPOABERTO) 
          return new Pair(linha, coluna);
    
    return null;
  }

  private void setPredecessor(AStar predecessor) {
    this.predecessor = predecessor;
    if (predecessor != null)
      this.nivel = predecessor.nivel + 1;
  }
}
