package general_button;

import javafx.scene.paint.Color;

/**
 * This Enumeration provides Constants and Values concerning the General Button Illumination Colour.
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public enum IlluminationColour {

    RED (Color.RED),
    YELLOW (Color.YELLOW),
    WHITE (Color.WHITE);
    
    private final Color colour;
    
    IlluminationColour (Color colour) {
        
        this.colour = colour;
        
    }
    
    Color getColour() {
        
        return this.colour;
        
    }
    
    
}
