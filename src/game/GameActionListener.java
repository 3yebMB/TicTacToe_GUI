package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton gButton) {
        this.row = row;
        this.cell = cell;
        this.button = gButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        if (board.isTurnable(row, cell)){
            updateByPlayersData(board);

            if (board.isFull()){
                board.getGame().showMessage("Ничья!");
                board.emptyField();
            }
            else {
                updateByAIData(board);
            }
        }
        else {
            board.getGame().showMessage("Некоректный ход!");
        }
    }

    /**
     * ход человека
     * @param board GameBoard - ссылка на игровое поле
     */

    private void updateByPlayersData(GameBoard board){
        // обновить матрицу игры
        board.updateGameField(row, cell);

        // обновить содержимое кнопки
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Вы выиграли!");
            board.emptyField();
        }
        else {
            board.getGame().changeGameState();
        }
    }

    private void updateByAIData(GameBoard board){
        // генерация координат хода компьютера
        int x, y;
        Random rnd = new Random();

        do{
            x = rnd.nextInt(GameBoard.dimension);
            y = rnd.nextInt(GameBoard.dimension);
        } while (!board.isTurnable(x, y));

        // обновить матрицу игры
        board.updateGameField(x, y);

        // обновить содержимое кнопки
        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        // проверить победу
        if (board.checkWin()){
            button.getBoard().getGame().showMessage("Компьютер выиграл!");
            board.emptyField();
        }
        else {
            // передать ход
            board.getGame().changeGameState();
        }
    }
}
