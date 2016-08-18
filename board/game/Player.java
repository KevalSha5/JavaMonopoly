package board.game;

import javax.swing.ImageIcon;
import board.places.places.Property;
import board.exceptions.NotEnoughMoneyError;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Player {

    private static int playerIdCounter = 0;
    private String name;
    private int playerId;
    private int location;
    private int money;
    private JLabel playerLabel;
    
    private boolean jailed;
    private int timesRolledDoubles;
    private boolean rolledDouble;
    private boolean getOutOfJailCard;

    public Player(String name) {
        this.name = name;
        location = 0;
        money = 1500;
        playerLabel = new JLabel();
        
        playerId = playerIdCounter;
        
        jailed = false;
        rolledDouble = false;
        timesRolledDoubles = 0;
        
        playerIdCounter++;
    }
    
    public Player(String name, Color color){
        this(name);
        setColor(color);
    }

    public void buy(Property property) {
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void subtractMoney(int amount) {
//        if (money - amount < 0){
//            throw new NotEnoughMoneyError();
//        }
        money -= amount;
    }

    public int getMoney() {
        return money;
    }

    public int getId() {
        return playerId;
    }

    public void setLocation(int newLocation) {
        this.location = newLocation;
    }

    public int getLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName() {
        return name;
    }

    public boolean isPlaying() {
        return false;
    }

    public void setWon() {
    }

    public int getTimesRolledDoubles() {
        return timesRolledDoubles;
    }

    public void incrementTimesRolledDoubles() {
        timesRolledDoubles++;
    }

    public void resetTimesRolledDoubles() {
        timesRolledDoubles = 0;
    }

    public void setJailed(boolean jailed) {
        this.jailed = jailed;
    }

    public boolean isJailed() {
        return jailed;
    }

    public void setGetOutOfJail(boolean getOutOfJailCard) {
        this.getOutOfJailCard = getOutOfJailCard;
    }

    void setRolledDoubles(boolean rolledDouble) {
        this.rolledDouble = rolledDouble;
    }

    boolean rolledDouble() {
        return rolledDouble;
    }

    public JLabel getPlayerLabel() {
        return playerLabel;
    }

    public void setPlayerLabel(JLabel playerLabel) {
        this.playerLabel = playerLabel;
    }

    public void setColor(Color color) {
        playerLabel.setOpaque(true);
        playerLabel.setBackground(color);
        playerLabel.setForeground(color);
        playerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        playerLabel.setPreferredSize(new Dimension(20, 6));
    }   
    
}