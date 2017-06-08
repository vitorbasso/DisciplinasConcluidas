/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;
import javax.swing.JFrame;


/**
 *
 * @author vitor
 */
public class Calculadora{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       MiniCalc calculadora = new MiniCalc();
       calculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       calculadora.setSize(500,125);
       calculadora.setVisible(true);
    }
    
}
