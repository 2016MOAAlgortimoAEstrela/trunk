package main;

import java.util.Scanner;

enum State{
    ABERTO, FECHADO
}

public class Main {    
    static final String ESPACO = "X";
    
    public static class Field{
        public Field(State estado, int valor) {
            this.estado = estado;
            this.valor = valor;
        }        
        
        State estado;
        int valor = 0;
    }

    public static class Puzzle{
        public Field[][] campos = new Field[3][3];
    }
    
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();
        puzzle.campos[0][0] = new Field(State.ABERTO, 1);
        
        Scanner teclado = new Scanner(System.in);
        String dadosEntrada = teclado.nextLine();
        dadosEntrada = dadosEntrada.replace("  ", ESPACO);
        dadosEntrada = dadosEntrada.replace(" ", ";");
        String[] lista = dadosEntrada.split(";");
        int indiceColuna = 0;
        int indiceLinha = 0;
        int indiceColunaAuxiliar = 0;
        for (int contador = 0; contador < lista.length; contador++){
            indiceColunaAuxiliar = (contador / 4);
            if (indiceColunaAuxiliar != indiceColuna){
                indiceColuna++;
                indiceLinha = 0;
            }
            
            if (lista[contador].equals(ESPACO)){
                int valor = Integer.parseInt(lista[contador]);
                indiceLinha++;
            }
        }
    }    
}
