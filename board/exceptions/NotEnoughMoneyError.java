package board.exceptions;

public class NotEnoughMoneyError extends RuntimeException {
    
    public NotEnoughMoneyError(){
        super("You don't have enough money to do that.");
    }
    
}
