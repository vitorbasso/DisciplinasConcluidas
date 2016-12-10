/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.truco.menuinicial;
import trabalhofinal.truco.JogoTruco;
import trabalhofinal.settings.Configuracao;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;


/**
 *
 * @author vitor
 */
public class MenuInicial extends JFrame{
    private final JButton play;
    private final JButton options;
    private final JButton exit;
    private final JPanel menu;
    private final Icon playIcon;
    private final Icon optionsIcon;
    private final Icon exitIcon;
    private Configuracao settings;
    
    public MenuInicial(){
        
        super("Truco em JAVA");
        setLayout(new GridLayout(1,3));
        
        settings = new Configuracao();
        
        playIcon = new ImageIcon(getClass().getResource("play.png"));
        optionsIcon = new ImageIcon(getClass().getResource("options.png"));
        exitIcon = new ImageIcon(getClass().getResource("exit.png"));
        
        menu = new JPanel(new GridLayout(5,1));
        
        play = new JButton("Jogar", playIcon);
        options = new JButton("Opções", optionsIcon);
        exit = new JButton("Sair", exitIcon);
        
        play.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event){
                        new JogoTruco(settings);
                        dispose();
                    }
                });
        
        options.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        new MenuInicialOption();
                    }
                }
        );
        
        exit.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        dispose();
                    }
                }
        );
        
        menu.add(new JPanel());
        menu.add(play);
        menu.add(options);
        menu.add(exit);
        menu.add(new JPanel());
        
        add(new JPanel());
        add(menu);
        add(new JPanel());
        setSize(400,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    private class MenuInicialOption extends JFrame{
        
        private final JTextField nomeTextField;
        private final JLabel labelNome;
        private final JLabel labelNumero;
        private final JTextField textFieldSave;
        private final JRadioButton doisJogadores;
        private final JRadioButton quatroJogadores;
        private final JPanel nome;
        private final JPanel jogadores;
        private final JPanel opcoesJogadores;
        private final ButtonGroup buttonGroup;
        private final JPanel confirmationPanel;
        private final JPanel carregarPanel;
        private final JButton carregarJogo;
        private final JButton saveButton;
        private final JButton cancelButton;
        
        public MenuInicialOption(){
            
            super("Configurações");
            
            setLayout(new GridLayout(4,1));
            
            nome = new JPanel(new GridLayout(1,2));
            
            labelNome = new JLabel("Nome do Jogador: ");
            nomeTextField = new JTextField("", 10);
            nome.add(labelNome);
            nome.add(nomeTextField);
            
            jogadores = new JPanel(new GridLayout(2,1));
            opcoesJogadores = new JPanel(new GridLayout(1,2));
            buttonGroup = new ButtonGroup();
            
            labelNumero = new JLabel("Quantos Jogadores?");
            doisJogadores = new JRadioButton("2", (settings.getQuantosJogadores() == 2));
            quatroJogadores = new JRadioButton("4", (settings.getQuantosJogadores() == 4));
            opcoesJogadores.add(doisJogadores);
            opcoesJogadores.add(quatroJogadores);
            buttonGroup.add(doisJogadores);
            buttonGroup.add(quatroJogadores);
            jogadores.add(labelNumero);
            jogadores.add(opcoesJogadores);
            
            confirmationPanel = new JPanel(new GridLayout(1,2));
            
            saveButton = new JButton("Salvar");
            cancelButton = new JButton("Cancelar");
            
            confirmationPanel.add(saveButton);
            confirmationPanel.add(cancelButton);
            
            carregarPanel = new JPanel(new GridLayout(2,1));
            
            carregarJogo = new JButton("Carregar um jogo salvo");
            textFieldSave = new JTextField("");
            textFieldSave.setEditable(false);
            textFieldSave.setHorizontalAlignment(JTextField.CENTER);
            
            carregarPanel.add(carregarJogo);
            carregarPanel.add(textFieldSave);
            
            
            add(nome);
            add(jogadores);
            add(carregarPanel);
            add(confirmationPanel);
            
            carregarJogo.addActionListener(
                    new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent event){
                            String nome = "JogoSalvo123";
                            settings.setLoadArchive(nome);
                            textFieldSave.setText(nome);
                        }
                    }
            );
            
            saveButton.addActionListener(
                    new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent event){
                            settings.setNomeJogador(nomeTextField.getText());
                            if(doisJogadores.isSelected())
                                settings.setQuantosJogadores(2);
                            else
                                settings.setQuantosJogadores(4);
                            dispose();
                        }
                    }
            );
            
            cancelButton.addActionListener(
                    new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent event){
                            dispose();
                        }
                    }
            );
            
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400,250);
            setVisible(true);
            
        }
    }
    
}
