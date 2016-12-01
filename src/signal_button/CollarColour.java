package signal_button;

import javafx.scene.paint.Color;

/**
 * This Enumeration defines Constants and associated values that represent the available Colours of an N/X Panel Signal Button Collar.
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public enum CollarColour {

    RED (Color.RED),
    YELLOW (Color.YELLOW);
    
    private final Color colour;
    
    CollarColour (Color colour) {
        
        this.colour = colour;
        
    }
    
    Color getColour() {
        
        return this.colour;
        
    }
    
}
