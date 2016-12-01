package signal_button;

/**
 * This Enumeration defines Constants and related values concerning the Orientation of Button Arrows.
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public enum ArrowOrientation {

    EAST (90.0),
    WEST (270.0);
    
    private final double arrowOrientation;
    
    ArrowOrientation (double arrowOrientation) {
        
        this.arrowOrientation = arrowOrientation;
        
    }
    
    double getArrowOrientation () {
        
        return this.arrowOrientation;
        
    }
    
}
