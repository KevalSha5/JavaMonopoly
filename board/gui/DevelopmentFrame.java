package board.gui;

import board.game.Board;
import board.game.Player;
import board.places.places.Street;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class DevelopmentFrame extends JDialog {

    private JPanel playerSelectPanel;
    private JPanel playerDevelopPanel;
    private JComboBox playersListCombo;
    private List<Player> players;
    Board board;

    public DevelopmentFrame(Board board) {
        super(board.boardGUI, true);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.board = board;

        playerSelectPanel = new JPanel();
        getContentPane().add(playerSelectPanel, BorderLayout.NORTH);

        playersListCombo = new JComboBox();
        players = board.getPlayers();
        for (Player p : players) {
            playersListCombo.addItem(p);
        }

        JButton playerSelectButton = new JButton("Show Build Options");
        playerSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player developingPlayer = players.get(playersListCombo.getSelectedIndex());
                populatePlayerDevelopPanel(developingPlayer);
            }
        });

        playerSelectPanel.add(playersListCombo);
        playerSelectPanel.add(playerSelectButton);



        playerDevelopPanel = new JPanel();
        getContentPane().add(playerDevelopPanel);


        pack();
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    private void populatePlayerDevelopPanel(Player developingPlayer) {
        playerDevelopPanel.removeAll();
//        playerDevelopPanel.revalidate();
//        playerDevelopPanel.repaint();
        this.revalidate();
        this.repaint();

        List<Street> buildableStreets;
        buildableStreets = board.getBuildableStreets(developingPlayer);


        for (Street s : buildableStreets) {
            playerDevelopPanel.add(new BuildableItem(s, board));
            pack();
        }
    }
}
