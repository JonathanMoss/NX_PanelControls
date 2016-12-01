package general_button;

import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This Class provides the functionality of a General Purpose NX panel button.
 *
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public class GeneralButton extends AnchorPane {

    private FXMLLoader fxmlLoader;
    
    @FXML
    private Circle buttonIndication;
    
    @FXML
    private Circle clickTarget;
    
    @FXML
    private Label legend;
    
    private final BooleanProperty buttonFlashing = new SimpleBooleanProperty(false);
    public boolean isButtonFlashing() {return buttonFlashing.get();}
    public void setButtonFlashing(boolean value) {buttonFlashing.set(value);}
    
    private final ObjectProperty <IlluminationColour> illuminationColour = new SimpleObjectProperty<>(IlluminationColour.WHITE);
    public IlluminationColour getIlluminationColour() {return illuminationColour.get();}
    public void setIlluminationColour(IlluminationColour value) {illuminationColour.set(value);} 
    
    private final StringProperty legendText = new SimpleStringProperty("X");
    public String getLegendText() {return legendText.get();}
    public void setLegendText(String value) {legendText.set(value);}
    
    public void setButtonIndication (IlluminationColour illuminationColour, Boolean buttonFlashing) {
        
        this.setIlluminationColour(illuminationColour);
        this.setButtonFlashing(buttonFlashing);
 
    }
    
    public GeneralButton () {
    
        // Get a reference to the FXML file.
        this.fxmlLoader = new FXMLLoader(getClass().getResource("GeneralButton.fxml"));
        
        // Set the root and Controller Objects
        this.setRoot();
        this.setController();
        
        // Attempt to load the FXML file.
        try {
            
            fxmlLoader.load();
            
        } catch (IOException e) {}
        
        this.legend.textProperty().bind(legendText);
        
        this.buttonFlashing.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                if ((Boolean) newValue) {

                    new Thread (() -> {
                            
                        while (isButtonFlashing()) {

                            try {

                                Thread.sleep (330);

                            } catch (InterruptedException ex) {}

                            if (buttonIndication.getFill() == Color.WHITE) {

                                buttonIndication.setFill((Color) getIlluminationColour().getColour());

                            } else {

                                buttonIndication.setFill(Color.WHITE);

                            }
                        }
                            
                    }).start();
                    
                } else {
                    
                    buttonIndication.setFill((Color) getIlluminationColour().getColour());
                    
                }
                
            }
        
        });
        
        this.illuminationColour.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                buttonIndication.setFill((Color) getIlluminationColour().getColour());
                
            }
        
        });
        
    }
    
    /**
     * This method sets the root object.
     */
    private void setRoot() {this.fxmlLoader.setRoot(this);}
    
    /**
     * This method sets the FXML Controller object.
     */
    private void setController() {this.fxmlLoader.setController(this);}
    
    public Circle getClickTarget() {return this.clickTarget;}
  
}
