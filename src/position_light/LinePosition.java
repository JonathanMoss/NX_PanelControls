package position_light;

/**
 *
 * @author Jonathan Moss
 */
public enum LinePosition {
    
    POSITION_ONE (21.0),
    POSITION_TWO (-14.0);
    
    private final double coordinate;
    
    LinePosition (double coordinate) {
        
        this.coordinate = coordinate;
        
    }
    
    double getCoordinate() {
        
        return this.coordinate;
        
    }
    
}
