package board.game;

class Bank {
    
    private int money = 1000000000; //Note, Bank technically has infinite money.
    
    void addMoney(int amount){
        money += amount;
    }
    
    void subtractMoney(int amount){
        money -= amount;
    }
}