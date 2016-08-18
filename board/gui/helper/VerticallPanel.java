
package board.gui.helper;

import board.gui.PlacesItem;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;


public class VerticallPanel extends JPanel {
    
    private Dimension placesDimension;
    
    public VerticallPanel(int width, int height) {

        setLayout(new GridLayout(9, 1, 0 ,0));
        setSize(width/11, (height/11)*9);        
        
        placesDimension = new Dimension(this.getSize().width, this.getSize().height / 9);
        
    }
    
    public void addPlaceItem() {        
//        PlacesItem placeItem = new PlacesItem();
//        placeItem.setSize(placesDimension);
//        placeItem.setPreferredSize(placesDimension);
//        placeItem.addHouse();
//        placeItem.addHouse();
//        this.add(placeItem);
    }    
}
