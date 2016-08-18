package board.places.places;

import board.places.helper.Places;
import board.places.helper.Action;
import board.game.Player;

public class SpecialPlace implements Places {
    private String name;
    
    public SpecialPlace(String name) {
        this.name = name;
    }
    
    @Override
    public Action landPlayer(Player player) {

//        System.out.println(player.getName() + " landed on " + this.getName());   
        
        Action action = null;        
        switch (name) {            
            case "Community Chest":
                action = Action.COMMUNITY_CHEST;
                break;
            case "Chance":
                action = Action.CHANCE;
                break;
            case "Income Tax":
                action = Action.PAY_INCOME_TAX;
                break;
            case "Luxury Tax":
                action = Action.PAY_LUXURY_TAX;
                break;
            case "Go To Jail":
                action = Action.GO_TO_JAIL;                
                break;
            default:
                action = Action.DO_NOTHING;
        }
        return action;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
}
