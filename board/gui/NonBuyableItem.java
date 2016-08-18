package board.gui;

import board.places.places.SpecialPlace;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NonBuyableItem extends PlacesItem {

    private JPanel developmentPanel;
    private JLabel priceLabel;

    public NonBuyableItem(SpecialPlace sp) {
        super();
//        setTopPanel(sp.getName());
//        
//        setBottomPanel();
        
        setPlaceName(sp.getName());
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
