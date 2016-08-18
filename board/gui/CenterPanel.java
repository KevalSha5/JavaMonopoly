package board.gui;

import board.game.Board;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CenterPanel extends JPanel {

    private JButton rollButton;
    private JButton developButton;
    private JButton exitGameButton;
    private Board board;
    public JTextArea textWriter;
    public JTextArea playerStatusArea;
    public TitledBorder actionPanelBorder;
    public String currentPlayerLabel;
    public JPanel playerStatusPanel;
    public TitledBorder playerStatusPanelBorder;

    public CenterPanel(Board board) {
        setLayout(new FlowLayout());
        this.board = board;

        JPanel actionPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        actionPanel.setBorder(actionPanelBorder);

        add(actionPanel);

        rollButton = new JButton("Roll");
        actionPanel.add(rollButton);
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roll();
            }
        });

        developButton = new JButton("Develop");
        actionPanel.add(developButton);
        developButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                build();
            }
        });


        exitGameButton = new JButton("Exit Game");
        actionPanel.add(exitGameButton);
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // create the middle panel components
        textWriter = new JTextArea(16, 58);
        textWriter.setBackground(null);
        textWriter.setEditable(false); // set textArea non-editable
        JScrollPane scroll = new JScrollPane(textWriter);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //Add Textarea in to middle panel
        add(scroll);


        playerStatusPanel = new JPanel();
        playerStatusPanelBorder = new TitledBorder(new EtchedBorder());
        playerStatusPanel.setBorder(playerStatusPanelBorder);


//        playerStatusArea = new JTextArea();
//        playerStatusArea.setBackground(null);
//        playerStatusPanel.add(playerStatusArea);
        add(playerStatusPanel);
    }

    private void roll() {
        board.play();

    }

    private void build() {
        new DevelopmentFrame(board);
    }
}
