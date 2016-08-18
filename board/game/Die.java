package board.game;

import java.util.Random;

public class Die {

    private int diceOne;
    private int diceTwo;
    private Random random;

    public Die() {
        random = new Random();
    }

    public int roll() {        
        diceOne = random.nextInt(5) + 1;
        diceTwo = random.nextInt(5) + 1;
        
        return diceOne + diceTwo;
    }

    public boolean isDoubles() {
        return diceOne == diceTwo;
    }
    
    public String getRoll(){
        return "[" + diceOne +  "][" + diceTwo + "]";
    }
    
}
