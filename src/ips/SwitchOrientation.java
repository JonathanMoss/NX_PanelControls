package ips;

/**
 * This Enumeration provides constants and double values for the IPS switch position.
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public enum SwitchOrientation {

    NORMAL (-45.0),
    CENTRE (0.0),
    REVERSE (45.0);
    
    private final double switchOrientation;
    
    SwitchOrientation (Double switchOrientation) {
        
        this.switchOrientation = switchOrientation;
        
    }
    
    Double getSwitchPosition() {
        
        return this.switchOrientation;
        
    }
    
}
