package track_section;

/**
 * This Enumeration provides Constant (and hex) values regarding Track Circuit Colors on an N/X Panel.
 * 
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public enum TrackCircuitColourScheme {

    YELLOW ("#FEEF35"),
    GREEN ("#0A663A"),
    BLUE ("#1DAEEC"),
    BROWN ("#744C2B");
    
    private final String hexValue;
    
    TrackCircuitColourScheme (String hexValue) {
        
        this.hexValue = hexValue;
        
    }
    
    String getHexValue () {
        
        return this.hexValue;
        
    }
    
}
