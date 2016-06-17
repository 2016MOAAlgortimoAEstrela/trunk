package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

interface IHeuristica{
    public int executar();
}

enum State{
    ABERTO, FECHADO
}
    
class Field{
    public Field(State estado, int valor) {
        this.estado = estado;
        this.valor = valor;
    }        

    State estado;
    int valor = 0;
}

class Puzzle{
    public Field[][] campos = new Field[3][3];
}

class HLinhaUm implements IHeuristica{
    String[] estadoAtual;
    String[] estadoFinal;
    
    public HLinhaUm(String[] estadoAtual, String[] estadoFinal) {
        this.estadoAtual = estadoAtual;
        this.estadoFinal = estadoFinal;
    }        
    
    @Override
    public int executar(){
        int resultado = 0;
        for (int contador = 0; (contador < estadoAtual.length); contador++)
            if (!estadoFinal[contador].equals(estadoAtual[contador]))
                resultado++;
        
        return resultado;
    }
}

public class Main {    
    static final String ESPACO = "X";    
    static String listaFinal = "1 12 11 10 2 13  9 3 14 15 8 4 5 6 7";
    static String testeSeisMovimentos = "1 12 11 10 0  13 15 9 2  14 6 8 3 4 5 7 ";
    static String testeOnzeMovimentos = "12 11  10 9 1 13 15  0 2 14 6 8 3 4 5 7 ";
    static String[] estadoFinal = listaFinal.split(" ");    
    
    public static void executarTodos(List<IHeuristica> heuristicas){
        for (IHeuristica heuristica : heuristicas) {
            System.out.printf("h'1 = %d\n", heuristica.executar());
        }
    }
    
    public static void main(String[] args) {
        String dadosEntrada[] = "1 2 3 4 5 6 7 8 9 10 11 12 13 14  15 ".split(" ");
        List<IHeuristica> listaHeuristicas = new ArrayList<IHeuristica>();
        listaHeuristicas.add(new HLinhaUm(dadosEntrada, estadoFinal));
        executarTodos(listaHeuristicas);
    }    
}
