/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.astar;

import br.uem.utils.Constantes;
import br.uem.puzzle.Puzzle;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Massao
 */
public class AStar {

  /**
   * Função que executa o algoritmo A*
   *
   * @param mapa, array de string, que deve possuir 16 strings
   * @return Puzzle
   */
  public Puzzle executa(String[] mapa) throws Exception {
    if (mapa.length != 16) {
      throw new Exception("Argumento inválido, deve possuir 16 números");
    }

    Integer[][] intMapa = new Integer[4][4];
    int linha = 0;
    int coluna = 0;
    for (String str : mapa) {
      if (coluna == 3) {
        linha++;
        coluna = 0;
      }

      intMapa[linha][coluna] = Integer.parseInt(str);
      coluna++;
    }

    return executa(intMapa);
  }

  /**
   * Função que executa o algoritmo A*
   *
   * @param mapa, string, que deve possuir 16 números, estes separados por uma
   * string única.
   * @param separador, string utilizada para separar os números
   * @return Puzzle
   */
  public Puzzle executa(String mapa, String separador) throws Exception {
    if (mapa.length() != 16) {
      throw new Exception("Argumento inválido, deve possuir 16 números");
    }

    String[] strMapa = mapa.split(separador);
    return executa(strMapa);
  }

  /**
   * Função que executa o algoritmo A*
   *
   * @param mapa, matriz int
   * @return Puzzle
   */
  public Puzzle executa(Integer[][] mapa) throws Exception {
    int tamanho = 0;
    for (Integer[] linha : mapa) {
      for (Integer coluna : linha) {
        tamanho++;
      }
    }

    if ((tamanho != 16)) {
      throw new Exception("Argumento inválido, deve possuir 16 números");
    }

    PriorityQueue<Puzzle> filaAbertos = new PriorityQueue<>();
    PriorityQueue<Puzzle> filaFechados = new PriorityQueue<>();

    Puzzle puzzle = new Puzzle(mapa, null);

    filaAbertos.add(puzzle);
    while (filaAbertos.size() != 0) {
      Puzzle.contadorIteracoes++;
      Puzzle p = filaAbertos.remove();
      filaFechados.add(p);

      if (p.comparaMapa(Constantes.ESTADOFINAL)) {
        return p;
      }

      List<Puzzle> listaAdjacentes = p.filhos();
      for (Puzzle adjacente : listaAdjacentes) {
        if (!(filaAbertos.contains(adjacente) || filaFechados.contains(adjacente))
                || (adjacente.getDistanciaOrigem() < adjacente.distanciaOrigemEqual(filaAbertos))) {
          filaAbertos.add(adjacente);
          if (filaFechados.contains(adjacente)) {
            filaFechados.remove(adjacente);
          }
        }
      }
    }

    return null;
  }
}

/*

 Algoritmo A*:
 (a)
 **A <- S, F <- {Vazio}, Para {Todo} S faça:
 **  – Calcule h'(s) e g(s) <- 0; P(s) <- {Vazio}
 **
 (b)
 **Se A = {Vazio}
 **  pare com fracasso. 
 **Senão
 **  tome v {Pertencente a} A tal que f(v) = min{f(n), {Todo} n {Pertencente a} A} desempatando de qualquer forma, 
 **  mas sempre favorecendo a v {Pertencente a} T e faça A <- A - {v} e F <- F {União com} {v}.

 (c)
 **Se v {Pertencente a} T
 **  para com sucesso. 
 **Senão
 **  gere {Função Gama} (v). 
 **  Se {Função Gama}(v) = {Vazio}
 **    volte para (b)

 (d)
 **para cada m {Pertencente a} {Função Gama} (v) faça:
 **  calcula g(m);
 **  Se m {Não Pertencente a} A {União com} F ou ((m {Pertencente a} A {União com} F) e g(m) < g(m') | m' = m))
 **    A <- A {União com} {m} e F <- F-{m}(se m {Pertencente a} F)
 **    p(m) <- v; 
 **    e calcule h'(m); 
 **volte para (b). 

 1.Cria lista P
 2.Adiciona nodo inicial S à lista P
 3.Até que o primeiro caminho de P termine no nodo final ou P esteja vazia
 4.Extraia o primeiro caminho de P
 5.Extenda o primeiro caminho 1 passo para todos seus vizinhos sem loops cujos custos sejam menores que outros caminhos com mesmo nodo final
 6.Adicione cada novo caminho a P, ordenado por menor custo total (distância percorrida + heurística)
 7.Se achou o nodo final-> sucesso. Senão -> Não existe caminho.

 */
