/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor
 */
public class Calculadora extends JFrame{
    
    private JTextField visor;
    private int whichValue = 1;
    private String whichMod = "+";
    private int visorValue = 0;
    private int visorValue2 = 0;
    private JPanel panelMods;
    private JPanel panelValues;
    private final int valueNames[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final String modNames[] = {"CE", "+", "-", "X", "/", "="};
    private JButton buttonsValue[];
    private JButton buttonsMod[];
    private double result;
    
    public Calculadora(){
        super("Calculadora Simples");
        
        
        visor = new JTextField(String.format("%d", visorValue));
        visor.setEditable(false);
        add(visor, BorderLayout.NORTH);
        
        panelValues = new JPanel();
        panelMods = new JPanel();
        panelValues.setLayout(new GridLayout(3,3,5,5));
        panelMods.setLayout(new GridLayout(6,1,5,5));
        
        
        buttonsValue = new JButton[valueNames.length];
        for(int count = 0; count<valueNames.length; count++){
            buttonsValue[count] = new JButton(String.format("%d", valueNames[count]));
            buttonsValue[count].addActionListener(new ValueHandler(valueNames[count]));
            panelValues.add(buttonsValue[count]);
        }
        
        
        buttonsMod = new JButton[modNames.length];
        for(int count = 0; count<modNames.length; count++){
            buttonsMod[count] = new JButton(modNames[count]);
            buttonsMod[count].addActionListener(new ModHandler(modNames[count]));
            panelMods.add(buttonsMod[count]);
        }
        
        add(panelValues, BorderLayout.CENTER);
        add(panelMods, BorderLayout.EAST);
        
    }
    
    private class ValueHandler implements ActionListener{
        private int value;
        
        public ValueHandler(int v){
            value = v;
        }
        
        @Override
        public void actionPerformed(ActionEvent event){
            if(whichValue == 1){
                visorValue = visorValue*10 + value;
                visor.setText(String.format("%d", visorValue));
            }else{
                String aux;
                visorValue2 = visorValue2*10 + value;
                aux = String.format("%d", visorValue2);
                visor.setText(visor.getText() + aux);
            }
        }
        
    }
    
    private class ModHandler implements ActionListener{
        private String mod;
        
        public ModHandler(String m){
            mod = m;
        }
        
        @Override
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == buttonsMod[0]){
                visorValue = 0;
                visorValue2 = 0;
                visor.setText(String.format("%d", visorValue));
                whichValue = 1;
            }else if(event.getSource() == buttonsMod[5]){
                
                if(whichValue == 2){
                    if(whichMod == "+")
                        result = visorValue + visorValue2;
                    else if(whichMod == "-")
                        result = visorValue - visorValue2;
                    else if(whichMod == "X")
                        result = visorValue * visorValue2;
                    else
                        result = visorValue / visorValue2;
                    
                    whichValue = 1;
                    visorValue = (int) result;
                    visorValue2 = 0;
                    whichMod = "+";
                    visor.setText(String.format("%f", result));
                }else
                    visor.setText(String.format("%d", visorValue));
            }else{
                if(event.getSource() == buttonsMod[2])
                    whichMod = "-";
                else if(event.getSource() == buttonsMod[3])
                    whichMod = "X";
                else if(event.getSource() == buttonsMod[4])
                    whichMod = "/";
                
                visor.setText(visor.getText() +" " + whichMod + " ");
                whichValue = 2;
            }
        }
    }
}
