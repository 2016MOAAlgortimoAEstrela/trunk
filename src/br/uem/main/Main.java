package br.uem.main;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String mapa = scanner.next();

    Puzzle puzzle = new AStar().executa(mapa, new HLinhaQuatro());
    System.out.printf("%d", (int)puzzle.getDistanciaOrigem());
  }
}

class AStar {  
  public Puzzle executa(String mapa, IHeuristica heuristica) {    
    return executa(mapa, " ", heuristica);
  }
  
  public Puzzle executa(String mapa, String separador, IHeuristica heuristica) {
    String[] vetorMapa = mapa.split(separador);
    Integer[][] matrizMapa = new Integer[4][4];
    int linha = 0;
    int coluna = 0;
    for (int contador = 0; contador < vetorMapa.length; contador++){
      if (coluna == 4){
        coluna = 0;
        linha++;
      }
      
      matrizMapa[linha][coluna] = Integer.parseInt(vetorMapa[contador]);      
      coluna++;
    }
    
    return executa(matrizMapa, heuristica);
  }
  
  public Puzzle executa(Integer[][] mapa, IHeuristica heuristica) {    
    Comparator<Puzzle> comparador = new Puzzle();
    PriorityQueue<Puzzle> filaAbertos = new PriorityQueue<Puzzle>(comparador);
    PriorityQueue<Puzzle> filaFechados = new PriorityQueue<Puzzle>(comparador);
    LinkedList<Puzzle> filaAuxiliar = new LinkedList<Puzzle>();

    Puzzle puzzle = new Puzzle(mapa, null, heuristica);
    filaAbertos.add(puzzle);
    filaAuxiliar.add(puzzle);
    while (filaAuxiliar.size() != 0) {
      Puzzle p = filaAbertos.remove();
      filaFechados.add(p);

      if (p.comparaMapa(Constantes.ESTADOFINAL)) {
        return p;
      }
      
      List<Puzzle> listaAdjacentes = p.filhos();
      for (Puzzle adjacente : listaAdjacentes) {
        if (filaAbertos.contains(adjacente)){
          if (adjacente.getDistanciaOrigem() < filaAuxiliar.get(filaAuxiliar.indexOf(adjacente)).getDistanciaOrigem()){
            filaAuxiliar.remove(adjacente);
          }
        }
        
        if (!(filaAuxiliar.contains(adjacente) || filaFechados.contains(adjacente))){
          filaAbertos.add(adjacente);
          filaAuxiliar.add(adjacente);
        }
      }
    }

    return null;
  }
}

class Constantes {
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
  
  public static final String MAPA_TESTE_01 = "12 2 11 10 1 13 9 8 3 5 14 15 4 0 6 7";
  public static final String MAPA_TESTE_02 = "2 1 9 11 12 13 10 0 3 14 15 8 4 5 6 7";
  public static final String MAPA_TESTE_03 = "2 1 9 11 3 12 13 10 14 15 6 8 4 0 5 7";
  public static final String MAPA_TESTE_04 = "1 12 10 13 2 6 11 0 3 14 15 9 4 5 7 8";
  public static final String MAPA_TESTE_05 = "2 0 14 10 13 15 12 1 3 5 9 8 4 6 11 7";
  public static final String MAPA_TESTE_06 = "1 14 11 10 2 6 12 8 3 0 15 9 5 4 7 13";
  public static final String MAPA_TESTE_07 = "12 3 11 10 0 13 1 15 2 14 8 7 4 5 9 6";
  public static final String MAPA_TESTE_08 = "13 1 11 10 0 3 9 7 14 12 4 15 2 6 8 5";
  public static final String MAPA_TESTE_09 = "12 14 15 10 3 1 11 13 6 0 7 5 4 2 8 9";
  public static final String MAPA_TESTE_10 = "3 1 10 8 13 2 11 14 15 0 7 12 4 6 5 9";
}

enum Direcao {
  DIREITA, ESQUERDA, CIMA, BAIXO;
}

interface IHeuristica {
  public double executaCalculo(Puzzle puzzle);
}

class Tuple {
  private final int linha;
  private final int coluna;

  public Tuple(int linha, int coluna) {
      this.linha = linha;
      this.coluna = coluna;
  }

  public int getLinha() {
      return linha;
  }

  public int getColuna() {
      return coluna;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 29 * hash + this.linha;
    hash = 29 * hash + this.coluna;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    
    if (getClass() != obj.getClass()) {
      return false;
    }
    
    final Tuple other = (Tuple) obj;
    if (this.linha != other.linha) {
      return false;
    }
    
    return this.coluna == other.coluna;
  }
}

class HLinhaUm implements IHeuristica {
  @Override
  public double executaCalculo(Puzzle puzzle) {
    double resultado = 0;
    Integer[][] estadoFinal = Constantes.ESTADOFINAL;
    Integer[][] mapaAtual = puzzle.getMapa();

    for (Integer linha = 0; linha < estadoFinal.length; linha++) {
      for (Integer coluna = 0; coluna < estadoFinal[linha].length; coluna++) {
        if (estadoFinal[linha][coluna] != mapaAtual[linha][coluna]) {
          resultado++;
        }
      }
    }

    return resultado;
  }
}

class HLinhaDois implements IHeuristica {
  @Override
  public double executaCalculo(Puzzle puzzle) {
    int resultado = 0;
    int proximo = -1;
    Integer[][] mapaAtual = puzzle.getMapa();
    Integer[] mapaEmLinha = Conversor.ConverteMatrizEspiralParaArray(mapaAtual);

    mapaEmLinha[14] = mapaAtual[2][2];
    mapaEmLinha[15] = mapaAtual[1][2];

    proximo = -1;
    for (int indice = 0; indice < mapaEmLinha.length; indice++) {
      proximo = (proximo < 0) ? mapaEmLinha[indice] : (proximo + 1);
      if (mapaEmLinha[indice] != proximo) {
        if (mapaEmLinha[indice] == Constantes.CAMPOABERTO) {
          proximo = -1;
        } else {
          proximo = mapaEmLinha[indice];
        }

        resultado++;
      }
    }

    return resultado;
  }
}

class HLinhaTres implements IHeuristica{
  @Override
  public double executaCalculo(Puzzle puzzle) {    
    int somador = 0;
    Tuple posicaoAtual;
    Tuple posicaoFinal;
    
    for (int valor = 0; valor < puzzle.mapaAtual.size(); valor++){
       posicaoAtual = puzzle.mapaAtual.get(valor);
       posicaoFinal = puzzle.mapaFinal.get(valor);
       somador += (Math.abs(posicaoFinal.getLinha() - posicaoAtual.getLinha())) + (Math.abs(posicaoFinal.getColuna()- posicaoAtual.getColuna()));
    }
    
    return somador;
  }  
}

class HLinhaQuatro implements IHeuristica{  
  @Override
  public double executaCalculo(Puzzle puzzle) {
    double pesoH1 = 0.05;
    double pesoH2 = 0.05;
    double pesoH3 = 0.99;
    return ((pesoH1 * new HLinhaUm().executaCalculo(puzzle)) + (pesoH2 * new HLinhaDois().executaCalculo(puzzle)) + (pesoH3 * new HLinhaTres().executaCalculo(puzzle)));
  }  
}

class HLinhaCinco implements IHeuristica{
  @Override
  public double executaCalculo(Puzzle puzzle) {
    return Double.max(Double.max(new HLinhaUm().executaCalculo(puzzle), new HLinhaDois().executaCalculo(puzzle)), new HLinhaTres().executaCalculo(puzzle));
  }  
}

class Puzzle implements Comparator<Puzzle> {
  private Puzzle predecessor;
  public Integer[][] mapa = new Integer[4][4];
  private double totalHeuristicas = -1;
  private double distanciaOrigem = 0;
  private Tuple posicaoZero;
  private IHeuristica heuristica;
  public HashMap<Integer, Tuple> mapaAtual = new HashMap<Integer, Tuple>();
  public HashMap<Integer, Tuple> mapaFinal = new HashMap<Integer, Tuple>();
  
  public Puzzle(){}

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
    if (this.predecessor != null) {
      this.distanciaOrigem = this.predecessor.distanciaOrigem + 1;
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
    List<Puzzle> filhos = new ArrayList<Puzzle>();
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
        if (this.mapa[linha][coluna] != outroMapa[linha][coluna]) {
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

class Conversor {
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
