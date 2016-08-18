package board.places.places;

import java.util.List;

public class Utility extends Property {

    public Utility(String name) {
        super(name);
        setPrice(150);
    }  

    @Override
    public int getRent() {        
        if (isBought()) {
            return rentModifier();
        } else {
            return -1;
        }        
    }

    private int rentModifier() {
        int modifier = 4;
        List<Property> utilities =
                super.getPropertyIdentifier().getReferences();

        for (Property p : utilities) {
            if (!p.getName().equals(this.getName())
                    && p.getOwnerId() == this.getOwnerId()) {
                modifier = 10;
            }
        }
        return modifier;
    }
}