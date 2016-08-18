package board.places.places;

import java.util.List;

public class Railroad extends Property {

    public Railroad(String name) {
        super(name);
        setRent(25);
        setPrice(200);
    }

    @Override
    public int getRent() {
        int rent = -1;

        if (isBought()) {
            List<Property> rrs = getPropertyIdentifier().getReferences();
            rent = super.getRent();
            
            for (Property p : rrs) {
                if (!p.getName().equals(this.getName())
                        && p.getOwnerId() == this.getOwnerId()) {
                    rent *= 2;
                }
            }
        }
        return rent;
    }
}