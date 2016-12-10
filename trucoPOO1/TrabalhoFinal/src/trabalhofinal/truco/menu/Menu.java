/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.truco.menu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import trabalhofinal.settings.Configuracao;
import trabalhofinal.truco.corpo.Status;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import trabalhofinal.truco.JogoTruco;
import trabalhofinal.truco.menuinicial.MenuInicial;
import javax.swing.JScrollPane;


/**
 *
 * @author vitor
 */
public class Menu extends JMenuBar{
    
    private final JMenu gameMenu;
    private final JMenu optionsMenu;
    private final JMenu helpMenu;
    private final JMenuItem helpMenuAbout;
    private final JMenuItem helpMenuRegras;
    private final JMenuItem gameMenuRestart;
    private final JMenuItem gameMenuSave;
    private final JMenuItem gameMenuLoad;
    private final JMenuItem gameMenuExit;
    private final JMenuItem optionsMenuNome;
    private final JMenuItem optionsMenuJogadores;
    private final JMenuItem optionsMenuAI;
    private final JMenuItem optionsMenuVoltarMenu;
    private final JMenuItem helpMenuCheat;
    private final Configuracao settings;
    private final Status status;
    private final JogoTruco jogoAtual;
    
    public Menu(Configuracao config, Status stats, JogoTruco jogo){
        settings = config;
        status = stats;
        jogoAtual = jogo;
        
        gameMenu = new JMenu("Game");
        
        optionsMenu = new JMenu("Opções");
        
        helpMenu = new JMenu("Ajuda");
        
        gameMenuRestart = new JMenuItem("Novo Jogo");
        gameMenuSave = new JMenuItem("Salvar Jogo");
        gameMenuLoad = new JMenuItem("Carregar Jogo");
        gameMenuExit = new JMenuItem("Fechar");
        
        gameMenuRestart.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        jogoAtual.newGame(settings);
                    }
                }
        );
        
        gameMenuSave.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        jogoAtual.saveGame();
                    }
                }
        );
        
        gameMenuLoad.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        int displayButton = JOptionPane.YES_NO_OPTION;
                        int displayOption = JOptionPane.showConfirmDialog(null, "Carregar um outro arquivo fechará a partida atual. Deseja continuar?", "Warning", displayButton);
                        if(displayOption == JOptionPane.YES_OPTION){
                            jogoAtual.loadGame();
                        }
                    }
                }
        );
        
        gameMenuExit.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        System.exit(0);
                    }
                }
        );
        
        optionsMenuNome = new JMenuItem("Trocar nome");
        optionsMenuJogadores = new JMenuItem("Trocar Quantidade de Jogadores");
        optionsMenuAI = new JMenuItem("Toggle AI");
        optionsMenuVoltarMenu = new JMenuItem("Voltar para o menu inicial");
        
        optionsMenuNome.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        config.setNomeJogador(JOptionPane.showInputDialog( "Nome: "));
                        jogoAtual.playerUpdate();
                        status.updateStatus();
                    }
                }
        );
        
        optionsMenuJogadores.addActionListener(
                new ActionListener(){
                    private JFrame fast;
                    private JLabel descricao;
                    private JButton save;
                    private JButton cancel;
                    private ButtonGroup buttonGroup;
                    private JRadioButton radio2;
                    private JRadioButton radio4;
                    private JPanel radioPanel;
                    private JPanel donePanel;
                    
                    @Override
                    public void actionPerformed(ActionEvent event){
                        descricao = new JLabel("Quantos jogadores? ");
                        
                        radio2 = new JRadioButton("2 Jogadores", (settings.getQuantosJogadores() == 2));
                        radio4 = new JRadioButton("4 Jogadores", (settings.getQuantosJogadores() == 4));
                        buttonGroup = new ButtonGroup();
                        buttonGroup.add(radio2);
                        buttonGroup.add(radio4);
                        
                        radioPanel = new JPanel(new GridLayout(1,2));
                        
                        radioPanel.add(radio2);
                        radioPanel.add(radio4);
                        
                        save = new JButton("Salvar");
                        cancel = new JButton("Cancelar");
                        
                        donePanel = new JPanel(new GridLayout(1,2));
                        
                        donePanel.add(save);
                        donePanel.add(cancel);
                        
                        save.addActionListener(
                                new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent event){
                                        if((radio2.isSelected() && settings.getQuantosJogadores() == 2) || (radio4.isSelected() && settings.getQuantosJogadores() == 4))
                                            fast.dispose();
                                        else{
                                            int dialogButton = JOptionPane.YES_NO_OPTION;
                                            int dialogOption = JOptionPane.showConfirmDialog(jogoAtual, "Mudar o numero de jogadores começará um novo jogo. Deseja continuar?", "Warning", dialogButton);
                                            if(dialogOption == JOptionPane.YES_OPTION){
                                                if(radio2.isSelected())
                                                    settings.setQuantosJogadores(2);
                                                else
                                                    settings.setQuantosJogadores(4);
                                                status.updateStatus();
                                                fast.dispose();
                                                jogoAtual.newGame(settings);
                                            }else
                                                fast.dispose();
                                        }
                                            
                                        jogoAtual.playerUpdate();
                                        status.updateStatus();
                                        fast.dispose();
                                    }
                                }
                        );
                        cancel.addActionListener(
                                new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent event){
                                        fast.dispose();
                                    }
                                }
                        );
                        
                        fast = new JFrame("Configurações");
                        
                        fast.setLayout(new GridLayout(3,1));
                        fast.add(descricao);
                        fast.add(radioPanel);
                        fast.add(donePanel);
                        fast.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        fast.setSize(400,150);
                        fast.setVisible(true);
                    }
                }
        );
        
        optionsMenuAI.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(settings.getAI())
                            settings.setAI(false);
                        else{
                            settings.setAI(true);
                        }
                        jogoAtual.playerUpdate();
                        status.updateStatus();
                    }
                }
        );
        
        optionsMenuVoltarMenu.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogOption = JOptionPane.showConfirmDialog(jogoAtual, "Voltar para o Menu inicial irá fechar o jogo atual. Deseja continuar?", "Warning", dialogButton);
                        
                        if(dialogOption == JOptionPane.YES_OPTION){
                            jogoAtual.dispose();
                            MenuInicial menuInicial = new MenuInicial();
                        }
                    }
                }
        );
        
        helpMenuAbout = new JMenuItem("Sobre");
        helpMenuRegras = new JMenuItem("Regras do Jogo");
        
        helpMenuAbout.addActionListener(
                new ActionListener(){
                    private JLabel about;
                    private JFrame aboutFrame;
                    private JScrollPane scrollAbout;
                    @Override
                    public void actionPerformed(ActionEvent event){
                        about = new JLabel(new ImageIcon(getClass().getResource("about.jpg")));
                        aboutFrame = new JFrame("About");
                        aboutFrame.add(new JScrollPane(about));
                        aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        aboutFrame.setSize(1275,800);
                        aboutFrame.setVisible(true);
                    }
                }
        );
        
        helpMenuRegras.addActionListener(
                new ActionListener(){
                    private JLabel regras;
                    private JFrame regrasFrame;
                    private JScrollPane scrollRegras;
                    @Override
                    public void actionPerformed(ActionEvent event){
                        regras = new JLabel(new ImageIcon(getClass().getResource("regras.jpg")));
                        regrasFrame = new JFrame("Regras");
                        regrasFrame.add(new JScrollPane(regras));
                        regrasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        regrasFrame.setSize(1275,800);
                        regrasFrame.setVisible(true);
                    }
                }
        );
        
        helpMenuCheat = new JMenuItem("Toggle cheats");
        
        helpMenuCheat.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                        if(config.getCheat())
                            config.setCheat(false);
                        else
                            config.setCheat(true);
                        jogoAtual.playerUpdate();
                        status.updateStatus();
                    }
                }
        );
        
        gameMenu.add(gameMenuRestart);
        gameMenu.add(gameMenuSave);
        gameMenu.add(gameMenuLoad);
        gameMenu.add(gameMenuExit);
        
        optionsMenu.add(optionsMenuNome);
        optionsMenu.add(optionsMenuJogadores);
        optionsMenu.add(optionsMenuAI);
        optionsMenu.add(optionsMenuVoltarMenu);
        
        helpMenu.add(helpMenuAbout);
        helpMenu.add(helpMenuRegras);
        helpMenu.add(helpMenuCheat);
        
        add(gameMenu);
        add(optionsMenu);
        add(helpMenu);
    }
    
}
