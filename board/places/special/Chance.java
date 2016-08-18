package board.places.special;

import java.util.ArrayList;
import java.util.List;

public enum Chance {

    ADVANCE_READING("Take a ride on the Reading Railroad\n"
    + "If you pass go collect $200"),
    BANK_PAYS_YOU_50("Bank pays you dividend of $50"),
    ADVANCE_ILLINOIS("Advance to Illinois Avenue"),
    BUILDING_LOAN_MATURES("Your building and loan matures \n"
    + "Collect $150"),
    GET_OUT_OF_JAIL("Get out of jail free card"),
    MAKE_GENERAL_REPAIRS("Make general repairs on all your property\n"
    + "Pay $25 for each house\n"
    + "Pay $100 for each hotel"),
    ADVANCE_NEAREST_RAILROAD("Advance token to the nearest railroad and pay the\n"
    + "owner twice the rental to which he is otherwise entitled. If railroad is\n"
    + "unowned, you may buy it from the bank."),
    POOR_TAX_15("Pay poor tax of $15"),
    WALK_ON_BOARDWALK("Take a walk on the Boardwalk"),
    ADVANCE_ST_CHARLES("Advance to St. Charles Place"),
    ELECTED_CHAIRMAN("You have been elected chairman of the board\n"
    + "Pay each player $50"),
    ADVANCE_NEAREST_UTILITY("Advance token to nearest utility \n"
    + "If unowned, you may buy it from the bank\n"
    + "If owned, throw the dice and pay owner a total of"
    + "10 times the amount thrown"),
    GO_BACK_3_SPACES("Go back 3 spaces"),
    ADVANCE_TO_GO("Advance to Go\n"
    + "Collect $200 dollars"),
    GO_TO_JAIL("Go directly to jail");
    
    private String stringRep;

    Chance(String stringRep) {
        this.stringRep = stringRep;
    }

    @Override
    public String toString() {
        return stringRep;
    }
}
