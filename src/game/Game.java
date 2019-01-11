package game;

import javax.swing.*;

public class Game {
    private GameBoard board;
    private GamePlayer[] gamePlayers = new GamePlayer[2];
    private int gameState = 0;

    public Game() {
        this.board = new GameBoard(this);
    }

    public void initGame(){
        gamePlayers[0] = new GamePlayer(true, 'X');
        gamePlayers[1] = new GamePlayer(false, 'O');
    }

    /**
     * метод перехода хода
     */
    void changeGameState(){
        if (gameState == 0)
            gameState = 1;
        else
            gameState = 0;
    }

    /**
     * получение объекта текущего игрока
     * @return GamePlayer объект игрока
     */
    GamePlayer gamePlayer() {   return gamePlayers[gameState];  }

    /**
     * метод показа popup-a для пользователя
     * @param messageText - текст сообщения
     */
    void showMessage(String messageText) {
        JOptionPane.showMessageDialog(board, messageText);
    }
}
