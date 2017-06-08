/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import javax.swing.JFrame;


/**
 *
 * @author vitor
 */
public class Cap11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Calculadora calc = new Calculadora();
        calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calc.setSize(500,300);
        calc.setVisible(true);
        
    }
    
}
