package signal_button;

import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * This is a Controller Class for an N/X (Entry/Exit) Panel Entry and/or Exit button.
 *
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public final class SignalButton extends AnchorPane {

    /**
     * The Circle object that represents the level Collar.
     */
    @FXML
    private Circle collar;
    
    /**
     * The Circle object that provides indication as to the state of operation.
     */
    @FXML
    private Circle buttonIndication;
    
    /**
     * Part of the button graphics, this is the left aligned arrow.
     */
    @FXML
    private Polygon leftArrow;
    
    /**
     * Part of the button graphics, this is the right aligned arrow.
     */
    @FXML
    private Polygon rightArrow;
    
    /**
     * Part of the button graphics, this is the centre aligned arrow.
     */
    @FXML
    private Polygon centreArrow;
    
    /**
     * This Group is used to rotate the button graphics to reflect orientation on the panel.
     */
    @FXML
    private Group rotation;
    
    /**
     * This Circle is provided to act as a target for the click event.
     */
    @FXML
    private Circle clickTarget;
    
    /**
     * The controlRotation is used to set the Rotation of the rotation Group.
     */
    private final ObjectProperty <ControlRotation> controlRotation = new SimpleObjectProperty<>(ControlRotation.HORIZONTAL);
    
    /**
     * This method returns the controlRotation value.
     * @return <code>ControlRotation</code> The controlRotation constant value.
     */
    public ControlRotation getControlRotation() {return this.controlRotation.get();}
    
    /**
     * This method sets the controlRotation constant value.
     * @param controlRotation <code>ControlRotation</code> The controlRotation constant value.
     */
    public void setControlRotation (ControlRotation controlRotation) {this.controlRotation.set(controlRotation);}

    /**
     * This Property represents the type of Arrow.
     */
    private final ObjectProperty <ArrowType> leftArrowType = new SimpleObjectProperty<>(ArrowType.HOLLOW);
    
    /**
     * This method gets the type of Arrow - HOLLOW or SOLID.
     * @return <code>ArrowType</code> The type of Arrow to be displayed.
     */
    public ArrowType getLeftArrowType () {return this.leftArrowType.get();}
    
    /**
     * This method sets the type of Arrow - HOLLOW or SOLID.
     * @param arrowType <code>ArrowType</code> The type of Arrow to be displayed.
     */
    public void setLeftArrowType (ArrowType arrowType) {this.leftArrowType.set(arrowType);}
    
    /**
     * This Property represents the type of Arrow.
     */
    private final ObjectProperty <ArrowType> centreArrowType = new SimpleObjectProperty<>(ArrowType.HOLLOW);
    
    /**
     * This method gets the type of Arrow - HOLLOW or SOLID.
     * @return <code>ArrowType</code> The type of Arrow to be displayed.
     */
    public ArrowType getCentreArrowType () {return this.centreArrowType.get();}
    
    /**
     * This method sets the type of Arrow - HOLLOW or SOLID.
     * @param arrowType <code>ArrowType</code> The type of Arrow to be displayed.
     */    
    public void setCentreArrowType (ArrowType arrowType) {this.centreArrowType.set(arrowType);}
    
    /**
     * This Property represents the type of Arrow.
     */
    private final ObjectProperty <ArrowType> rightArrowType = new SimpleObjectProperty<>(ArrowType.HOLLOW);
    
    /**
     * This method gets the type of Arrow - HOLLOW or SOLID.
     * @return <code>ArrowType</code> The type of Arrow to be displayed.
     */    
    public ArrowType getRightArrowType () {return this.rightArrowType.get();}
    
    /**
     * This method sets the type of Arrow - HOLLOW or SOLID.
     * @param arrowType <code>ArrowType</code> The type of Arrow to be displayed.
     */ 
    public void setRightArrowType (ArrowType arrowType) {this.rightArrowType.set(arrowType);}
    
    /**
     * This Property defines the Colour of the Button Collar.
     */
    private final ObjectProperty <CollarColour> collarColour = new SimpleObjectProperty<>(CollarColour.RED);
    
    /**
     * This method returns the value of the collarColour Property.
     * @return <code>CollarColour</code> The value of the collarColour Property.
     */
    public CollarColour getCollarColour() {return this.collarColour.get();}
    
    /**
     * This method sets the value of the collarColour Property.
     * @param collarColour <code>CollarColour</code> The value of the collarColour Property.
     */
    public void setCollarColour(CollarColour collarColour) {this.collarColour.set(collarColour);}
    
    /**
     * This Property defines if the arrow shall be displayed.
     */
    private final BooleanProperty displayLeftArrow = new SimpleBooleanProperty(false);
    
    /**
     * This method returns whether or not the arrow is displayed.
     * @return <code>Boolean</code> <i>'true'</i> indicates that the arrow is displayed, otherwise <i>'false'</i>.
     */
    public Boolean getDisplayLeftArrow () {return this.displayLeftArrow.get();}
    
    /**
     * This method sets whether or not the arrow is displayed.
     * @param displayLeftArrow <code>Boolean</code> <i>'true'</i> indicates that the arrow is displayed, otherwise <i>'false'</i>.
     */   
    public void setDisplayLeftArrow (Boolean displayLeftArrow) {this.displayLeftArrow.set(displayLeftArrow);}
    
    /**
     * This Property defines if the arrow shall be displayed.
     */
    private final BooleanProperty displayCentreArrow = new SimpleBooleanProperty(false);
    
    /**
     * This method returns whether or not the arrow is displayed.
     * @return <code>Boolean</code> <i>'true'</i> indicates that the arrow is displayed, otherwise <i>'false'</i>.
     */
    public Boolean getDisplayCentreArrow () {return this.displayCentreArrow.get();}
    
    /**
     * This method sets whether or not the arrow is displayed.
     * @param displayCentreArrow <code>Boolean</code> <i>'true'</i> indicates that the arrow is displayed, otherwise <i>'false'</i>.
     */ 
    public void setDisplayCentreArrow (Boolean displayCentreArrow) {this.displayCentreArrow.set(displayCentreArrow);} 
    
    /**
     * This Property defines if the arrow shall be displayed.
     */
    private final BooleanProperty displayRightArrow = new SimpleBooleanProperty(false);
    
    /**
     * This method returns whether or not the arrow is displayed.
     * @return <code>Boolean</code> <i>'true'</i> indicates that the arrow is displayed, otherwise <i>'false'</i>.
     */
    public Boolean getDisplayRightArrow () {return this.displayRightArrow.get();}
    
    /**
     * This method sets whether or not the arrow is displayed.
     * @param displayRightArrow <code>Boolean</code> <i>'true'</i> indicates that the arrow is displayed, otherwise <i>'false'</i>.
     */ 
    public void setDisplayRightArrow (Boolean displayRightArrow) {this.displayRightArrow.set(displayRightArrow);} 
    
    /**
     * This Property defines the Button Illumination status.
     */
    private final ObjectProperty <ButtonIllumination> buttonIllumination = new SimpleObjectProperty(ButtonIllumination.OFF);
    
    /**
     * This method sets the button illumination status.
     * @param bi <code>ButtonIllumination</code> A constant representing the button illumination state.
     */
    public void setButtonIllumination (ButtonIllumination bi) {this.buttonIllumination.set(bi);}
    
    /**
     * This method returns the button illumination status.
     * @return <code>ButtonIllumination</code> A constant representing the button illumination state.
     */
    public ButtonIllumination getButtonIllumination() {return this.buttonIllumination.get();}
    
    /**
     * A Boolean value indicating if the illuminationButton is flashing, or not.
     */
    private Boolean buttonFlashing = false;
    
    /**
     * This Property defines the Orientation of the Arrow.
     */
    private final ObjectProperty <ArrowOrientation> leftArrowOrientation = new SimpleObjectProperty<>(ArrowOrientation.EAST);
    
    /**
     * This method sets the orientation of the Arrow.
     * @param ao <code>ArrowOrientation</code> A constant value representing the orientation of the Arrow.
     */
    public void setLeftArrowOrientation (ArrowOrientation ao) {this.leftArrowOrientation.set(ao);}
    
    /**
     * This method returns the orientation of the Arrow.
     * @return <code>ArrowOrientation</code> A constant value representing the orientation of the Arrow.
     */
    public ArrowOrientation getLeftArrowOrientation () {return this.leftArrowOrientation.get();}
    
    /**
     * This Property defines the Orientation of the Arrow.
     */
    private final ObjectProperty <ArrowOrientation> centreArrowOrientation = new SimpleObjectProperty<>(ArrowOrientation.EAST);
    
    /**
     * This method sets the orientation of the Arrow.
     * @param ao <code>ArrowOrientation</code> A constant value representing the orientation of the Arrow.
     */
    public void setCentreArrowOrientation (ArrowOrientation ao) {this.centreArrowOrientation.set(ao);}
    
    /**
     * This method returns the orientation of the Arrow.
     * @return <code>ArrowOrientation</code> A constant value representing the orientation of the Arrow.
     */
    public ArrowOrientation getCentreArrowOrientation () {return this.centreArrowOrientation.get();}
    
    /**
     * This Property defines the Orientation of the Arrow.
     */
    private final ObjectProperty <ArrowOrientation> rightArrowOrientation = new SimpleObjectProperty<>(ArrowOrientation.EAST);
    
    /**
     * This method sets the orientation of the Arrow.
     * @param ao <code>ArrowOrientation</code> A constant value representing the orientation of the Arrow.
     */
    public void setRightArrowOrientation (ArrowOrientation ao) {this.rightArrowOrientation.set(ao);}
    
    /**
     * This method returns the orientation of the Arrow.
     * @return <code>ArrowOrientation</code> A constant value representing the orientation of the Arrow.
     */
    public ArrowOrientation getRightArrowOrientation () {return this.rightArrowOrientation.get();}
    
    /**
     * The object is used to load the applicable FXML file.
     */
    private FXMLLoader fxmlLoader;
    
    /**
     * This method returns the Circle Object that is used as a click target.
     * @return <code>Circle</code> The object used as a click target.
     */
    public Circle getClickTarget() {
        
        return this.clickTarget;
        
    }
    
    /**
     * This is the 'Constructor' class for a SignalButton Object.
     */
    public SignalButton () {
    
        // Get a reference to the FXML file.
        this.fxmlLoader = new FXMLLoader(getClass().getResource("SignalButton.fxml"));
        
        // Set the root and Controller Objects
        this.setRoot();
        this.setController();
        
        // Attempt to load the FXML file.
        try {
            fxmlLoader.load();
        } catch (IOException e) {}
        
        // This code block adds a listener to the 'controlRotation' property.
        this.controlRotation.addListener (new ChangeListener () {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                // Set the rotation of the Group 'rotation'. This affects the orientation of all 3 arrows.
                rotation.setRotate(((ControlRotation) newValue).getRotationAngle());
                
            }
        });
        
        // This code block adds a listener to the 'leftArrowType' property.
        this.leftArrowType.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                // Show either a HOLLOW or SOLID arrow, based on the value of the leftArrowType.
                switch ((ArrowType) newValue) {
                    case HOLLOW:
                        leftArrow.setFill(Color.TRANSPARENT);
                        leftArrow.setStrokeWidth(3.0);
                        leftArrow.setStroke(Color.BLACK);
                        break;
                    case SOLID:
                        leftArrow.setFill(Color.BLACK);
                        leftArrow.setStrokeWidth(0.0);
                        break;
                }
                
            }
        });
        
        // This code block adds a listener to the 'centreArrowType' property.
        this.centreArrowType.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                 // Show either a HOLLOW or SOLID arrow, based on the value of the centreArrowType.
                switch ((ArrowType) newValue) {
                    case HOLLOW:
                        centreArrow.setFill(Color.TRANSPARENT);
                        centreArrow.setStrokeWidth(2.0);
                        centreArrow.setStroke(Color.BLACK);
                        break;
                    case SOLID:
                        centreArrow.setFill(Color.BLACK);
                        centreArrow.setStrokeWidth(0.0);
                        break;
                }
                
            }
        });
        
        // This code block adds a listener to the 'rightArrowType' property.
        this.rightArrowType.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                // Show either a HOLLOW or SOLID arrow, based on the value of the rightArrowType.
                switch ((ArrowType) newValue) {
                    case HOLLOW:
                        rightArrow.setFill(Color.TRANSPARENT);
                        rightArrow.setStrokeWidth(2.0);
                        rightArrow.setStroke(Color.BLACK);
                        break;
                    case SOLID:
                        rightArrow.setFill(Color.BLACK);
                        rightArrow.setStrokeWidth(0.0);
                        break;
                }
                
            }
        });
        
        // This code block adds a listener to the 'collarColour' property.
        this.collarColour.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               
                // Show the correct level collar colour based on the value of collarColour.
                collar.setFill(((CollarColour) newValue).getColour());
                
            }        
        });
        
        // Bind the visability of the arrow objects the relevant properties.
        this.leftArrow.visibleProperty().bind(this.displayLeftArrow);
        this.centreArrow.visibleProperty().bind(this.displayCentreArrow);
        this.rightArrow.visibleProperty().bind(this.displayRightArrow);
        
        // This code block adds a listener to the 'leftArrowOrientation' property.
        this.leftArrowOrientation.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            
                // Set the rotate angle (double) based on the leftArrowOrientation property.
                leftArrow.setRotate(((ArrowOrientation) newValue).getArrowOrientation());
  
            }
        });
        
        // This code block adds a listener to the 'centreArrowOrientation' property.
        this.centreArrowOrientation.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            
                // Set the rotate angle (double) based on the centreArrowOrientation property.
                centreArrow.setRotate(((ArrowOrientation) newValue).getArrowOrientation());
               
            }
        });
        
        // This code block adds a listener to the 'rightArrowOrientation' property.
        this.rightArrowOrientation.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            
                // Set the rotate angle (double) based on the rightArrowOrientation property.
                rightArrow.setRotate(((ArrowOrientation) newValue).getArrowOrientation());
              
            }
        });
        
        // This code block adds a listener to the 'buttonIllumination' property.
        this.buttonIllumination.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                switch ((ButtonIllumination)newValue) { 
                    
                    case OFF: // Show no Illumination.
                        buttonFlashing = false;
                        buttonIndication.setFill(Color.WHITE);
                        break;
                        
                    case STEADY: // Show steady button illumination.
                        buttonFlashing = false;
                        buttonIndication.setFill(Color.YELLOW);
                        break;
                        
                    case FLASHING: // Show a flashing button illumination.
                        buttonFlashing = true;
                        new Thread (() -> {
                            
                            while (buttonFlashing) {
                                
                                try {
                                    
                                    Thread.sleep (330);
                                    
                                } catch (InterruptedException ex) {}
                                
                                if (buttonIndication.getFill() == Color.WHITE) {
                                    
                                    buttonIndication.setFill(Color.YELLOW);
                                    
                                } else {
                                    
                                    buttonIndication.setFill(Color.WHITE);
                                    
                                }
                            }
                        }).start();
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
    
}
