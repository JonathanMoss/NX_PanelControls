package controlled_signal;

import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * This Class provides a Controlled Signal Indication for an N/X Panel.
 *
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public class ControlledSignal extends AnchorPane {

    @FXML
    private Line orientationLineOne;
    
    @FXML
    private Line orientationLineTwo;
    
    private final ObjectProperty <LinePosition> linePosition = new SimpleObjectProperty <> (LinePosition.POSITION_ONE);
    public LinePosition getLinePosition () {return this.linePosition.get();}
    public void setLinePosition (LinePosition linePosition) {this.linePosition.set(linePosition);}
    
    private final StringProperty applicableSignal = new SimpleStringProperty();

    public String getApplicableSignal() {return applicableSignal.get();}
    public void setApplicableSignal(String value) {applicableSignal.set(value);}
    public StringProperty applicableSignalProperty() {return applicableSignal;}
    
    @FXML
    private Circle shuntAspect;
    
    @FXML
    private Circle redAspect;

    @FXML
    private Circle greenAspect;
    private final ObjectProperty <DisplayAspect> showAspect = new SimpleObjectProperty<>(DisplayAspect.BLACK);
    public DisplayAspect getShowAspect () {return showAspect.get();}
    public void setShowAspect(DisplayAspect value) {showAspect.set(value);}  
    
    @FXML
    private Group subSignalGroup;
    
    private FXMLLoader fxmlLoader;
    
    private final BooleanProperty associatedSubSignal = new SimpleBooleanProperty(false);
    public boolean getAssociatedSubSignal() {return associatedSubSignal.get();}
    public void setAssociatedSubSignal(boolean value) {associatedSubSignal.set(value);}
    
    @FXML
    private Group signalGroup;
    
    private final DoubleProperty signalRotation = new SimpleDoubleProperty(0.0);
    public double getSignalRotation() {return signalRotation.get();}
    public void setSignalRotation(double value) {signalRotation.set(value);}

    public ControlledSignal() {
    
        // Get a reference to the FXML file.
        this.fxmlLoader = new FXMLLoader(getClass().getResource("ControlledSignal.fxml"));
        
        // Set the root and Controller Objects
        this.setRoot();
        this.setController();
        
        // Attempt to load the FXML file.
        try {
            fxmlLoader.load();
        } catch (IOException e) {}
        
        this.subSignalGroup.visibleProperty().bind(associatedSubSignal);
        this.signalGroup.rotateProperty().bind(signalRotation);
        
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
        
        this.showAspect.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            
                switch ((DisplayAspect) newValue) {
                    case MAIN_OFF:
                        shuntAspect.setFill (Color.SLATEGRAY);
                        redAspect.setFill (Color.SLATEGRAY);
                        greenAspect.setFill(Color.GREEN);
                        break;
                    case MAIN_ON:
                        shuntAspect.setFill (Color.SLATEGRAY);
                        redAspect.setFill (Color.RED);
                        greenAspect.setFill(Color.SLATEGRAY);
                        break;
                    case SUB_OFF_MAIN_ON:
                        shuntAspect.setFill (Color.WHITE);
                        redAspect.setFill (Color.RED);
                        greenAspect.setFill(Color.SLATEGRAY);
                        break;
                    case SUB_OFF_MAIN_BLACK:
                        shuntAspect.setFill (Color.WHITE);
                        redAspect.setFill (Color.SLATEGRAY);
                        greenAspect.setFill(Color.SLATEGRAY);
                        break;
                    case BLACK:
                    default:
                        shuntAspect.setFill (Color.SLATEGRAY);
                        redAspect.setFill (Color.SLATEGRAY);
                        greenAspect.setFill(Color.SLATEGRAY);
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
    
    public void setAspectToDisplay (DisplayAspect aspect) {
        
        this.showAspect.set(aspect);
        
    }
    
}
