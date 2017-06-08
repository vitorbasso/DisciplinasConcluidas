/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab09.gui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author vitor
 */
public class GUI {
    public GUI(){
        final JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setAlignmentY(JButton.BOTTOM);
        final JButton botaoApagar = new JButton("Apagar");
        botaoApagar.setAlignmentY(JButton.BOTTOM);
        final JButton botaoProcurar = new JButton("Procurar");
        botaoProcurar.setAlignmentY(JButton.BOTTOM);
        final JTextField campoNome = new JTextField(30);
        campoNome.setText("Nome");
        campoNome.setAlignmentX(JTextField.RIGHT);
        final JLabel labelNome = new JLabel("Nome: ");
        labelNome.setAlignmentX(JLabel.LEFT);
        final JTextField campoTelefone = new JTextField(15);
        campoTelefone.setText("Telefone");
        campoTelefone.setAlignmentX(JTextField.RIGHT);
        final JLabel labelTelefone = new JLabel("Telefone: ");
        labelTelefone.setAlignmentX(JLabel.LEFT);
        final JTextField campoEndereco = new JTextField(50);
        campoEndereco.setText("Endereco");
        campoEndereco.setAlignmentX(JTextField.RIGHT);
        final JLabel labelEndereco = new JLabel("Endereço: ");
        labelEndereco.setAlignmentX(JLabel.LEFT);
        final JFrame janela = new JFrame("Agenda telefônica");
        final JLabel label = new JLabel("INSIRA OS DADOS");
        
        JPanel painel = new JPanel();
        painel.add(label);
        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(labelTelefone);
        painel.add(campoTelefone);
        painel.add(labelEndereco);
        painel.add(campoEndereco);
        painel.add(botaoProcurar);
        painel.add(botaoCadastrar);
        painel.add(botaoApagar);
        janela.getContentPane().add(painel);
        JPanel botoes = new JPanel();
        botoes.add(botaoProcurar);
        botoes.add(botaoApagar);
        botoes.add(botaoCadastrar);
        botoes.setLayout(new GridLayout(1,3));
        JPanel camposLabels = new JPanel();
        camposLabels.add(labelNome);
        camposLabels.add(campoNome);
        camposLabels.add(labelTelefone);
        camposLabels.add(campoTelefone);
        camposLabels.add(labelEndereco);
        camposLabels.add(campoEndereco);
        camposLabels.setLayout(new GridLayout(3,2));
        
        botaoApagar.addActionListener((ActionEvent e) -> {
            campoNome.setText("");
            campoTelefone.setText("");
            campoEndereco.setText("");
        });
        janela.add(label, BorderLayout.NORTH);
        janela.add(botoes, BorderLayout.SOUTH);
        janela.add(camposLabels, BorderLayout.CENTER);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(400,200);
        janela.setVisible(true);
        
        
    }
}
