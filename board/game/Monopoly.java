package board.game;

import board.gui.PlayerCreator;
import java.util.List;
import javax.swing.JFrame;

public class Monopoly {

    private Board board;

    public Monopoly() {

       
        PlayerCreator playerCreator = new PlayerCreator(null);
        List<Player> players = playerCreator.getPlayers();
        playerCreator.dispose();

        if (players != null) {
            board = new Board(players);
        }
    }

    public static void main(String[] args) {
        Monopoly monopoly = new Monopoly();
        monopoly.printResults();
    }

    public boolean gameOver() {
        return false;
    }

    public void printResults() {
    }
}
