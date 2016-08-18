package board.places.helper;

import java.awt.Color;
import java.util.ArrayList;
import board.places.places.Property;

public enum PropertyIdentifier {

    PURPLE(new Color(90, 36, 255)),
    LIGHTBLUE(new Color(121, 184, 255)),
    MAROON(new Color(195, 33, 72)),
    ORGANGE(new Color(255, 127, 0)),
    RED(new Color(255, 0, 0)),
    YELLOW(new Color(255, 255, 0)),
    GREEN(new Color(0, 255, 0)),
    BLUE(new Color(0, 75, 255)),
    RAILROAD,
    UTILITY;

    private ArrayList<Property> identifier = new ArrayList<Property>();
    private Color propertyColor;

    private PropertyIdentifier() {
    }

    private PropertyIdentifier(Color propertyColor) {
        this.propertyColor = propertyColor;
    }  
    
    public void addReference(Property property) {
        identifier.add(property);
    }

    public ArrayList<Property> getReferences() {
        return identifier;
    }
    
    public Color getColor(){
        return this.propertyColor;
    }
}
