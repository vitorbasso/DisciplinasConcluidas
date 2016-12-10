/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.truco.corpo;
import trabalhofinal.settings.Configuracao;
import javax.swing.JOptionPane;
import trabalhofinal.truco.JogoTruco;

/**
 *
 * @author vitor
 */
public class Tracker {
    
    public static final int PLAYER_TEAM = 0;
    public static final int BOT_TEAM = 1;
    private int teamPlayerScore;
    private int teamBotScore;
    private int totalTurn;
    private int turn;
    private int greaterCard;
    private int greaterCardTeam;
    private final Configuracao settings;
    private int playerVictories = 0;
    private int botVictories = 0;
    private boolean tied;
    private int whoWonFirstRound;
    private boolean firstRound;
    private boolean askedTruco;
    private int timesTruco;
    public static final int WIN = 0;
    public static final int LOSE = 1;
    public static final int DRAW = 2;
    public static final int NOT_YET = -1;
    private int playerFirstRound;
    private int playerSecondRound;
    private int playerThirdRound;
    private final JogoTruco jogoAtual;
    private boolean finishEarlyArgument;
    private int nextFirstToPlay;
    
    public Tracker(Configuracao config, JogoTruco jog){
        settings = config;
        teamPlayerScore = 0;
        teamBotScore = 0;
        totalTurn = 1;
        turn = 1;
        greaterCard = 0;
        askedTruco = false;
        timesTruco = 0;
        jogoAtual = jog;
        setTie(false);
        firstRound = true;
        finishEarlyArgument = false;
        whoWonFirstRound = PLAYER_TEAM;
    }
    
    public void newTracker(){
        
    teamPlayerScore = 0;
    nextFirstToPlay = 0;
    teamBotScore = 0;
    totalTurn = 1;
    turn = 1;
    greaterCard = 0;
    greaterCardTeam = 3;
    playerVictories = 0;
    botVictories = 0;
    tied = false;
    whoWonFirstRound = 3;
    firstRound = false;
    askedTruco = false;
    timesTruco = 0;
    playerFirstRound = NOT_YET;
    playerSecondRound = NOT_YET;
    playerThirdRound = NOT_YET;
    finishEarlyArgument = false;
        
    }
    
    public int getNextFirstToPlay(){
        return nextFirstToPlay;
    }
    
    public void setNextFirstToPlay(int player){
        nextFirstToPlay = player;
    }
    
    public boolean getFinishEarlyArgument(){
        return finishEarlyArgument;
    }
    
    public void setFinishEarlyArgument(boolean teste){
        finishEarlyArgument = teste;
    }
    
    public boolean finishEarly(){
        if(!getFinishEarlyArgument()){
            if((playerFirstRound == WIN && playerSecondRound == WIN) || (playerFirstRound == LOSE && playerSecondRound == LOSE)){
                endRound();
                return true;
            }else if(playerFirstRound == DRAW && (playerSecondRound == WIN || playerSecondRound == LOSE)){
                endRound();
                return true;
            }else if((playerFirstRound == LOSE || playerFirstRound == WIN) && (playerSecondRound == DRAW)){
                endRound();
                return true;
            }else
                return false;
        }else{
            finishEarlyArgument = !finishEarlyArgument;
            return !finishEarlyArgument;
        }
        
    }
    
    public void resetGame(){
        teamPlayerScore = 0;
        teamBotScore = 0;
        resetRound();
    }
    
    public void resetRound(){
        totalTurn = 1;
        setTie(false);
        firstRound = true;
        whoWonFirstRound = PLAYER_TEAM;
        playerVictories = 0;
        askedTruco = false;
        timesTruco = 0;
        botVictories = 0;
        playerFirstRound = NOT_YET;
        playerSecondRound = NOT_YET;
        playerThirdRound = NOT_YET;
        finishEarlyArgument = false;
        timesTruco = 0;
        askedTruco = false;
        resetTurn();
    }
    
    public void resetTurn(){
        turn = 1;
        greaterCard = 0;
        setGreaterCardTeam(3);
    }
    
    public void nextRound(){
        totalTurn++;
        if(totalTurn > ((settings.getQuantosJogadores()*3) / (settings.getQuantosJogadores()))){
            endRound();
        }
    }
    
    public void finish(){
        jogoAtual.playerUpdate();
    }
    
    public int getTimesTruco(){
        return timesTruco;
    }
    
    public int getTurnValue(){
        if(timesTruco == 1)
            return 3;
        else if(timesTruco == 2)
            return 6;
        else if(timesTruco == 3)
            return 9;
        else if(timesTruco == 4)
            return 12;
        else
            return 1;
    }
    
    public void pedirTruco(){
        askedTruco = true;
    }
    
    public boolean isAskedTruco(){
        return askedTruco;
    }
    
    public void aceitarTruco(){
        askedTruco = false;
        truco();
    }
    
    public void truco(){
        if(timesTruco < 5){
            timesTruco++;
        }
    }
    
    public int getRound(){
        return (totalTurn);
    }
    
    public int getTeamPlayerScore(){
        return teamPlayerScore;
    }
    
    public int getTeamBotScore(){
        return teamBotScore;
    }
    
    public void nextTurn(){
        turn++;
        if(turn > settings.getQuantosJogadores()){
            endTurn();
            nextRound();
        }
    }
    
    public void setGreaterCardTeam(int team){
        greaterCardTeam = team;
    }
    
    public void compareCard(int card, int team, int player){
        if(card > greaterCard){
            greaterCard = card;
            setNextFirstToPlay(player);
            if(team == PLAYER_TEAM)
                setGreaterCardTeam(PLAYER_TEAM);
            else
                setGreaterCardTeam(BOT_TEAM);
            setTie(false);
        }else if(card == greaterCard){
            if(team != getGreaterCardTeam())
                setTie(true);
        }
    }
    
    public void addPlayerVictory(){
        playerVictories++;
    }
    
    public int getPlayerVictories(){
        return playerVictories;
    }
    
    public void addBotVictory(){
        
        botVictories++;
    }
    
    public int getBotVictories(){
        return botVictories;
    }
    
    public int getGreaterCardTeam(){
        return greaterCardTeam;
    }
    
    public void endTurn(){
        int vencedor = BOT_TEAM;
        
        if(getTie()){
            if(playerFirstRound == NOT_YET)
                playerFirstRound = DRAW;
            else if(playerSecondRound == NOT_YET)
                playerSecondRound = DRAW;
            else if(playerThirdRound == NOT_YET)
                playerThirdRound = DRAW;
        }else if(getGreaterCardTeam() == PLAYER_TEAM){
            vencedor = PLAYER_TEAM;
            if(playerFirstRound == NOT_YET)
                playerFirstRound = WIN;
            else if(playerSecondRound == NOT_YET)
                playerSecondRound = WIN;
            else if(playerThirdRound == NOT_YET)
                playerThirdRound = WIN;
        }else{
            if(playerFirstRound == NOT_YET)
                playerFirstRound = LOSE;
            else if(playerSecondRound == NOT_YET)
                playerSecondRound = LOSE;
            else if(playerThirdRound == NOT_YET)
                playerThirdRound = LOSE;
        }
        
        if(getGreaterCardTeam() == PLAYER_TEAM)
            vencedor = PLAYER_TEAM;
        
        if(isFirstRound())
            firstRoundWin(vencedor);
        
        if(!getTie())
            giveVictory(vencedor);
        
        resetTurn();
    }
    
    public boolean isFirstRound(){
        return firstRound;
    }
    
    public void firstRoundWin(int vencedor){
        firstRound = !firstRound;
        whoWonFirstRound = vencedor;
    }
    
    public int whoWonFirst(){
        return whoWonFirstRound;
    }
    
    public boolean getTie(){
       return tied;
    }
    
    public void setTie(boolean bol){
        
        tied = bol;
    }
    
    public void giveVictory(int vencedor){
        jogoAtual.setVez(getNextFirstToPlay());
        if(vencedor == PLAYER_TEAM)
            addPlayerVictory();
        else
            addBotVictory();
    }
    
    public void giveWin(int team){
        if(team == PLAYER_TEAM)
            for(int i = 0; i<=5; i++)
                giveVictory(team);
        else if(team == BOT_TEAM)
            for(int i = 0; i<=5; i++)
                giveVictory(team);
        
        finish();
    }
    
    public void givePoints(int vencedor, int value){
        if(vencedor == PLAYER_TEAM)
            teamPlayerScore += value;
        else
            teamBotScore += value;
    }
    
    public void endRound(){
        String aux = "BOT";
        if(whoWon() == PLAYER_TEAM)
            aux = String.format("%s", settings.getNomeJogador());
        
        JOptionPane.showMessageDialog(jogoAtual, String.format("O time %s venceu a rodada!\nGanharam: %d Pontos!", aux, getTurnValue()));
        
        givePoints(whoWon(), getTurnValue());
        jogoAtual.checkWin();
        resetRound();
        jogoAtual.cleanTable();
    }
    
    public int whoWon(){
        setTie(false);
        if(getPlayerVictories() > getBotVictories())
            return PLAYER_TEAM;
        else if (getPlayerVictories() == getBotVictories()){
            return whoWonFirst();
        }
        else
            return BOT_TEAM;
    }
    
    public boolean gotWinner(){
        
        return (getTeamPlayerScore() >= 12 || getTeamBotScore() >= 12);
        
    }
    
    public int teamWinner(){
        if(getTeamPlayerScore() >= 12)
            return PLAYER_TEAM;
        else
            return BOT_TEAM;
    }
    
}
