package board.gui;

import board.game.Board;
import board.game.Player;
import board.gui.helper.VerticallPanel;
import board.gui.helper.HorizontalPanel;
import board.places.helper.Places;
import board.places.places.Property;
import board.places.places.SpecialPlace;
import board.places.places.Street;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class BoardGUI extends JFrame {

    private List<PlacesItem> placesItemList;
    private CenterPanel centerPanel;
    private int width;
    private int height;
    private Board board;

    public BoardGUI(Board board) {
        super("Monopoly");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(0, 0));

        this.board = board;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();



        placesItemList = new ArrayList<PlacesItem>();
        List<Places> placesList = board.getPlaces();




        for (Places p : placesList) {
            if (p instanceof Street) {
                Street s = (Street) p;
                placesItemList.add(new BuildableItem(s));
            } else if (p instanceof SpecialPlace) {
                SpecialPlace sp = (SpecialPlace) p;
                placesItemList.add(new NonBuyableItem(sp));

            } else {
                Property property = (Property) p;
                placesItemList.add(new NonBuildableItem(property));
            }
        }

        HorizontalPanel bottomPanel = new HorizontalPanel(width, height);
        VerticallPanel leftPanel = new VerticallPanel(width, height);
        HorizontalPanel topPanel = new HorizontalPanel(width, height);
        VerticallPanel rightPanel = new VerticallPanel(width, height);

        for (int i = 10; i >= 0; i--) {
            bottomPanel.add(placesItemList.get(i));
        }
        for (int i = 19; i >= 11; i--) {
            leftPanel.add(placesItemList.get(i));
        }
        for (int i = 20; i <= 30; i++) {
            topPanel.add(placesItemList.get(i));
        }
        for (int i = 31; i <= 39; i++) {
            rightPanel.add(placesItemList.get(i));
        }

        add(bottomPanel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);
        add(rightPanel, BorderLayout.EAST);

        setAllPlayersOnGo();

        centerPanel = new CenterPanel(board);
        add(centerPanel, BorderLayout.CENTER);


        setVisible(true);
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    public void movePlayerOnGUI(Player player, int oldLocation, int newLocation) {

        JLabel playerLabel = player.getPlayerLabel();
        placesItemList.get(oldLocation).removeToken(playerLabel);
        placesItemList.get(newLocation).addToken(playerLabel);
    }

    private void setAllPlayersOnGo() {
        List<Player> players = board.getPlayers();
        for (Player p : players) {
            placesItemList.get(0).addToken(p.getPlayerLabel());
        }
    }

    public void write(String string) {
        centerPanel.textWriter.append(string + "\n");
    }

    public void updateCurentPlayerLabel(Player p) {
//        centerPanel.playerStatusPanel.add(new JLabel("Current Player: " + p.getName()));
        centerPanel.playerStatusPanelBorder.setTitle("Current Player: " + p.getName());
    }

    public void updatePlayerStatuses() {
//        centerPanel.playerStatusArea.setText("");
        List<Player> players = board.getPlayers();
        centerPanel.playerStatusPanel.removeAll();
        for (Player p : players) {
//            centerPanel.playerStatusArea.append(p.getName() + "\n");
//            centerPanel.playerStatusArea.append("$" + p.getMoney());
//            centerPanel.playerStatusArea.append("\n");
//            centerPanel.playerStatusArea.append("\n");
            JPanel playerPanel = new JPanel();
            JLabel playerMoney = new JLabel();
            JLabel playerLocation = new JLabel();


            playerPanel.setBorder(new TitledBorder(new EtchedBorder(), p.getName()));
            playerPanel.setLayout(new GridLayout(2, 1));
            playerMoney.setText("$" + p.getMoney());
            playerLocation.setText(board.getPlaceAt(p.getLocation()).getName());

            centerPanel.playerStatusPanel.add(playerPanel);
            playerPanel.add(playerMoney);
            playerPanel.add(playerLocation);

            centerPanel.playerStatusPanel.revalidate();
            centerPanel.playerStatusPanel.repaint();

        }
    }

    public List<PlacesItem> getPlacesItemList() {
        return placesItemList;
    }

    public BuildableItem getBuildableItemByName(String buildableItemName) {
        for (PlacesItem placesItem : placesItemList) {
            if (placesItem instanceof BuildableItem) {
                if (placesItem.getPlaceName().equals(buildableItemName)) {
                    return (BuildableItem) placesItem;
                }
            }
        }
        return null;
    }
}
