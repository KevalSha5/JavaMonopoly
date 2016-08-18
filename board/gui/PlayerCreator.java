package board.gui;

import board.exceptions.CreatePlayerError;
import board.game.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerCreator extends JDialog {  
    
    private static Color[] colorArray = {Color.PINK, Color.CYAN, Color.LIGHT_GRAY, Color.YELLOW};
    private static String[] colorArrayString = {"Pink", "Cyan", "Light Gray", "Yellow"};
    
    private List<Player> players;
    private List<JTextField> playerNameField;
    private List<JComboBox> playerColorField;
    
    private JPanel playerFieldsPanel;
    private int numOfPlayers = 0;

    public PlayerCreator(JFrame frame) {

        super(frame, true);        
        setLayout(new BorderLayout());
        
        playerNameField = new ArrayList<JTextField>();
        playerColorField = new ArrayList<JComboBox>();

        playerFieldsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));    
        playerFieldsPanel.setPreferredSize(new Dimension(300, 400));     
        
        JButton playerAddButton = new JButton("Add Player");
        playerAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = new JTextField(15);
                playerFieldsPanel.add(textField);
                playerNameField.add(textField);
                
                JComboBox comoboBox = new JComboBox(colorArrayString);
                playerFieldsPanel.add(comoboBox);
                playerColorField.add(comoboBox);
                                                
                numOfPlayers++;
                playerFieldsPanel.revalidate();
                playerFieldsPanel.repaint();
            }
        });

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                createPlayers();
                setVisible(false);
                } catch (CreatePlayerError ex){
                     JOptionPane.showMessageDialog(null,
                            ex.getMessage(),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        getContentPane().add(playerAddButton, BorderLayout.NORTH);
        getContentPane().add(playerFieldsPanel, BorderLayout.CENTER);
        getContentPane().add(doneButton, BorderLayout.SOUTH);
 
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }
    
    public List<Player> getPlayers(){
        return players;
    }
    
    public void createPlayers() throws CreatePlayerError {
        if (numOfPlayers < 2){
            throw new CreatePlayerError("You must have at least two players.");
        }
        
        players = new ArrayList<Player>();
        
        for (int i = 0; i < numOfPlayers; i++){
            String playerName = playerNameField.get(i).getText();
            Color playerColor = colorArray[playerColorField.get(i).getSelectedIndex()];           
            
            if (playerName == null || playerName.equals("")){
                throw new CreatePlayerError("Each player must have a name.");
            }
            players.add(new Player(playerName, playerColor));          
            
            
        }
    }

    
    
//    public static void main(String[] args) {
//        new PlayerCreator();
//        playerAddButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    frame.add(new PlayerCreatorPanel());
//                    numOfPlayers++;
//                    frame.revalidate();
//                    frame.repaint();
//                } catch (TooManyPlayers ex) {
//                    JOptionPane.showMessageDialog(null,
//                            ex.getMessage(),
//                            "Input Error " + numOfPlayers,
//                            JOptionPane.WARNING_MESSAGE);
//                }
//            }
//        });
//    }  
}