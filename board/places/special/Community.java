package board.places.special;

import java.util.List;

public enum Community {

    INCOME_TAX_REFUND("Income Tax Refund \n"
    + "Collect $20"),
    STREET_REPAIRS("You are assessed for street repairs\n"
    + "$40 per house\n"
    + "$115 per hotel"),
    INHERET_100("You inherit &100!"),
    GRAND_OPERA_OPENING("Grand Opera Opening\n"
    + "collect $50 from every player"),
    XMAS_FUND_MATURES("Xmas fund matures\n"
    + "Collect $100"),
    ADVANCE_TO_GO("Advance to Go\n"
    + "Collect $200"),
    BANK_ERROR_IN_FAVOR("Bank Error in your favor\n"
    + "Collect $200"),
    GET_OUT_OF_JAIL("Get out of jail free card"),
    PAY_HOSPITAL_100("Pay hospital $100"),
    RECEIVE_FOR_SERVICES("Receive for Services $25"),
    GO_TO_JAIL("Go to to Jail"),
    PAY_SCHOOL_TAX("Pay school tax of $150"),
    DOCTORS_FEE("Doctors Fee\n"
    + "Pay $50"),
    SALE_OF_STOCK("From sale of stock \n"
    + "You get $45"),
    LIFE_INSURANCE("Life insurance matures \n"
    + "Collect $100"),
    BEAUTY_CONTEST("You have won second prize in a beauty contest! \n"
    + "Collect $10 ");
    
    private String stringRep;

    Community(String stringRep) {
        this.stringRep = stringRep;
    }
        
    @Override
    public String toString() {
        return stringRep;
    }
}
