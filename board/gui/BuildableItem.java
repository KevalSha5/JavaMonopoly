package board.gui;

import board.game.Board;
import board.places.places.Street;
import java.awt.Dimension;

public class BuildableItem extends PlacesItem {

    public BuildableItem(Street s) {
        super();
        setPlaceName(s.getName());
        setPrice(s.getPrice());
        setStreetColor(s.getPropertyIdentifier().getColor());

    }

    public BuildableItem(Street s, Board board) {
        super(true);
        
//        Dimension buildingItemDimension = new Dimension(175, 100);
//        setSize(buildingItemDimension);
//        setPreferredSize(buildingItemDimension);
        
        streetOfBuildableItem = (Street) board.getPropertyByName(s.getName());
        buildableItemOnBoard = board.boardGUI.getBuildableItemByName(s.getName());
        
        if (s.hasHosues()){
            for (int i = 0; i < s.getNumOfHouses(); i++){
                addHouse();
            }
        } else if (s.hasHotel()){
            addHotel();
        }


        setPlaceName(s.getName());
        setBuildingCost(s.getBuildingCost());
        setStreetColor(s.getPropertyIdentifier().getColor());

    }

    public void updateRent() {
    }
}
