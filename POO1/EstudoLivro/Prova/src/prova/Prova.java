/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author vitor
 */
public class Prova {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Leitura> objetos = new ArrayList<Leitura>();
        int nL, nR, volume, numero;
        Scanner input = new Scanner (System.in);
        String nome, autores;
        System.out.println("Quantos livros: ");
        nL = input.nextInt();
        System.out.println("Quantas revistas: ");
        nR = input.nextInt();
        
        for (int i=0 ; i<nL ; i++){
            nome = javax.swing.JOptionPane.showInputDialog("Nome do livro: ");
            autores = javax.swing.JOptionPane.showInputDialog("Nome dos autores: ");
            System.out.println("Quantas paginas: ");
            numero = input.nextInt();
            objetos.add(new Livro(nome,autores,numero));
        }
        for(int i = 0 ; i<nR ; i++){
            nome = javax.swing.JOptionPane.showInputDialog("Qual o nome da revista: ");
            autores = javax.swing.JOptionPane.showInputDialog("Quais os autores: ");
            System.out.println("Qual o volume: ");
            volume = input.nextInt();
            System.out.println("Qual o numero: ");
            numero = input.nextInt();
            objetos.add(new Revista(nome,autores,volume,numero));
        }
        
        for(Leitura objeto : objetos){
            System.out.printf("%s\n", objeto);
        }
        
    }
    
}
