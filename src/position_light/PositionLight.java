package position_light;

import java.io.IOException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * This Class provides an object that represents a Ground Position Light symbol/indication for an N/X Panel.
 *
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public class PositionLight extends AnchorPane {

    private FXMLLoader fxmlLoader;
    
    @FXML
    private Group signalGroup;
    
    private final DoubleProperty signalRotation = new SimpleDoubleProperty(0.0);
    public double getSignalRotation() {return signalRotation.get();}
    public void setSignalRotation(double value) {signalRotation.set(value);}
    
    @FXML
    private Line orientationLineOne;
    
    @FXML
    private Line orientationLineTwo;
    
    private final ObjectProperty <LinePosition> linePosition = new SimpleObjectProperty <> (LinePosition.POSITION_ONE);
    public LinePosition getLinePosition () {return this.linePosition.get();}
    public void setLinePosition (LinePosition linePosition) {this.linePosition.set(linePosition);}
    
    private final ObjectProperty <DisplayAspect> showAspect = new SimpleObjectProperty<>(DisplayAspect.BLACK);
    public DisplayAspect getShowAspect () {return showAspect.get();}
    public void setShowAspect(DisplayAspect value) {showAspect.set(value);}  
    
    @FXML
    private Circle shuntAspect;
    
    @FXML
    private Circle redAspect;
   
    public PositionLight () {
    
        // Get a reference to the FXML file.
        this.fxmlLoader = new FXMLLoader(getClass().getResource("PositionLight.fxml"));
        
        // Set the root and Controller Objects
        this.setRoot();
        this.setController();
        
        // Attempt to load the FXML file.
        try {
            fxmlLoader.load();
        } catch (IOException e) {}
        
        this.signalGroup.rotateProperty().bind(signalRotation);
        
        // This code block listens for changes to the linePosition Property, and displays the appropriate LinePosition.
        this.linePosition.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                switch ((LinePosition) newValue) {
                    
                    case POSITION_ONE:
                        orientationLineOne.setVisible(true);
                        orientationLineTwo.setVisible(false);
                        break;
                    case POSITION_TWO:
                        orientationLineOne.setVisible(false);
                        orientationLineTwo.setVisible(true);
                        break;
                }
                
            }

        });
        
        // This code block listens for changes to the showAspect Property, and displays the appropriate aspect.
        this.showAspect.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            
                switch ((DisplayAspect) newValue) {
                    
                    case SUB_OFF:
                        shuntAspect.setFill (Color.WHITE);
                        redAspect.setFill (Color.SLATEGRAY);
                        break;
                    case SUB_ON:
                        shuntAspect.setFill (Color.SLATEGRAY);
                        redAspect.setFill (Color.RED);
                        break;
                    case BLACK:
                        shuntAspect.setFill (Color.SLATEGRAY);
                        redAspect.setFill (Color.SLATEGRAY);
                        break;
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
    
    /**
     * This method is used to display the specified aspect.
     * 
     * @param aspect <code>DisplayAspect</code> The aspect that the Position Light is required to display.
     */
    public void setAspectToDisplay (DisplayAspect aspect) {
        
        this.showAspect.set(aspect);
        
    }
    
}
