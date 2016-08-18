package board.places.places;

import board.places.helper.Places;
import board.places.helper.Action;
import board.game.Player;
import board.places.helper.PropertyIdentifier;

public class Property implements Places {

    private String name;
    private int price;
    private int rent;
    private boolean bought;
    private boolean mortgaged;
    private int ownerId;
    private PropertyIdentifier propertyIdentifier;

    public Property(String name) {
        this.name = name;
        this.price = 0;
        this.rent = 0;
        this.ownerId = -1;
        bought = false;
    }

    @Override
    public Action landPlayer(Player player) {
        Action action = Action.DO_NOTHING;

        if (isBought() && !isMortgated()) {
            if (player.getId() != this.getOwnerId()) {
                if (this.propertyIdentifier == PropertyIdentifier.UTILITY) {
                    action = Action.PAY_UTILITY_RENT;
                } else {
                    action = Action.PAY_PROPERTY_RENT;
                }
            }
        } else if (!isBought()) {
            action = Action.PROMPT_TO_BUY;
        } 
        
        return action;
    }

    @Override
    public String getName() {
        return this.name;
    }
    

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }
    

    public boolean isBought() {
        return this.bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
    

    public boolean isMortgated() {
        return this.mortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        this.mortgaged = mortgaged;
    }
    

    public void setOwner(Player player) {
        this.ownerId = player.getId();
    }

    public void resetOwner() {
        this.ownerId = -1;
    }

    public int getOwnerId() {
        return this.ownerId;
    }
    

    public int getPrice() {
        return price;
    }

    public int getMortgageValue() {
        return price / 2;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
   
    public void setPropertyIdentifier(PropertyIdentifier identifier) {
        this.propertyIdentifier = identifier;
        identifier.addReference(this);
    }

    public PropertyIdentifier getPropertyIdentifier() {
        return this.propertyIdentifier;
    }
}