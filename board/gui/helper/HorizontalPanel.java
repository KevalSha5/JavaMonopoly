
package board.gui.helper;

import board.gui.PlacesItem;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;


public class HorizontalPanel extends JPanel {
    
    private Dimension placesDimension;
    
    public HorizontalPanel(int width, int height) {

        setLayout(new GridLayout(1, 11, 0 ,0));
        setSize(width, height/11);        
        
        placesDimension = new Dimension(width/11, height/11);
    }
}
