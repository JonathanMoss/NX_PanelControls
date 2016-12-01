package signal_button;

/**
 * This Enumeration defines Constants and Values that define the orientation of the Group Control.
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public enum ControlRotation {

    HORIZONTAL (0.0),
    VERTICAL (90.0),
    LEFT_DIAG (135.0),
    RIGHT_DIAG (45.0);
    
    private final double rotationAngle;
    
    ControlRotation (double rotationAngle) {
        
        this.rotationAngle = rotationAngle;
        
    }
    
    double getRotationAngle () {
        
        return this.rotationAngle;
        
    }
}
