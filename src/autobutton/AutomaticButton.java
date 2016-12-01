package autobutton;

import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This Class provides the functionality of an N/X Panel 'Automatic Working' button
 *
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public class AutomaticButton extends AnchorPane {

    @FXML
    private Circle buttonIndication;
    private final BooleanProperty buttonIlluminated = new SimpleBooleanProperty(false);
    public boolean isButtonIlluminated() {return buttonIlluminated.get();}
    public void setButtonIlluminated(boolean value) {buttonIlluminated.set(value);}

    @FXML
    private Circle clickTarget;
    
    public void setButtonIlluminated (Boolean buttonIlluminated) {
    
        this.buttonIlluminated.set (buttonIlluminated);
        
    }
    
    private FXMLLoader fxmlLoader;
    
    public Circle getClickTarget () {return this.clickTarget;}
 
    public AutomaticButton () {
        
        // Get a reference to the FXML file.
        this.fxmlLoader = new FXMLLoader(getClass().getResource("AutomaticButton.fxml"));
        
        // Set the root and Controller Objects
        this.setRoot();
        this.setController();
        
        // Attempt to load the FXML file.
        try {
            fxmlLoader.load();
        } catch (IOException e) {}
        
        buttonIlluminated.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                if ((Boolean) newValue) {
                    
                    buttonIndication.setFill(Color.YELLOW);
                    
                } else {
                    
                    buttonIndication.setFill (Color.WHITE);
                }
                
            }

        });
        
    }
    
    /**
     * This method sets the root object.
     */
    private void setRoot() {
        
        this.fxmlLoader.setRoot(this);

    }
    
    /**
     * This method sets the FXML Controller object.
     */
    private void setController() {
        
        this.fxmlLoader.setController(this);
        
    }
    
}
