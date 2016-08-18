package board.gui;

import board.places.places.Property;
import javax.swing.JPanel;

public class NonBuildableItem extends PlacesItem {

    private JPanel developmentPanel;
    
    public NonBuildableItem(Property property){
        super();
//        
//        setTopPanel(property.getName());
//        
//        setBottomPanel();
//        priceLabel.setText("$" + property.getPrice());
        
        setPlaceName(property.getName());
        setPrice(property.getPrice());
    }    

    @Override
    public void addHouse() {
    }

    @Override
    public void removeHouse() {
    }

    @Override
    public void addHotel() {
    }

    @Override
    public void removeHotel() {
    }
}
