
package board.places.helper;


public enum Action {
    
    PROMPT_TO_BUY("Prompt to buy"),
    BUY_PROPERTY("Buy property"),
    PAY_PROPERTY_RENT("Pay property rent"),
    PAY_UTILITY_RENT("Pay utility rent"),
    COMMUNITY_CHEST("Community Chest"),
    CHANCE("Chance"),
    PAY_INCOME_TAX("Pay income tax"),
    PAY_LUXURY_TAX("Pay luxury tax"),
    GO_TO_JAIL("Go to jail"),
    DO_NOTHING("Do nothing");
    
    String actionString;
    
    Action(String actionString){
        this.actionString = actionString;
    }
    
    @Override
    public String toString(){        
        return this.actionString;                
    }
    
}