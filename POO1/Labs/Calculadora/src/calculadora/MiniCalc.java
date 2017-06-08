/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 *
 * @author vitor
 */
public class MiniCalc extends JFrame{
    
    private final int valor[];
    private final JButton button1;
    private final JButton button2;
    private final JLabel label1;
    private final JLabel label2;
    private final JTextField operando[];
    private final JTextField resultado1, resultado2;
    
    public MiniCalc(){
        super("MiniCalc");
        setLayout(new FlowLayout());
        
        valor = new int[4];
        button1 = new JButton("=");
        button2 = new JButton("=");
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                resultado1.setText(String.format("%d", (valor[0] * valor[1])));
            }
        });
        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                resultado2.setText(String.format("%d", (valor[2] + valor[3])));
            }
        });
        
        resultado1 = new JTextField("0", 10);
        resultado1.setEditable(false);
        resultado2 = new JTextField("0", 10);
        resultado2.setEditable(false);
        
        operando = new JTextField[4];
        
        for(int count = 0; count<operando.length; count++){
            operando[count] = new JTextField(10);
            operando[count].addActionListener(new OperandoListener(operando[count], count));
        }
        
        label1 = new JLabel("*");
        label2 = new JLabel("+");
        add(operando[0]);
        add(label1);
        add(operando[1]);
        add(button1);
        add(resultado1);
        add(operando[2]);
        add(label2);
        add(operando[3]);
        add(button2);
        add(resultado2);
        
        
        
    }
    
    private class OperandoListener implements ActionListener{
        private JTextField valorString;
        private int operando;
        
        public OperandoListener(JTextField val, int op){
            valorString = val;
            operando = op;
        }
        @Override
        public void actionPerformed(ActionEvent event){
            valor[operando] = Integer.parseInt(valorString.getText());
        }
    }
    
}
