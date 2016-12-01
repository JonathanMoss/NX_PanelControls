package ips;

import java.io.IOException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.shape.Line;

/**
 * This class provides the functionality of an IndividualPointSwitch (IPS).
 * 
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public final class IndividualPointSwitch extends AnchorPane {
    
    /**
     * A Boolean flag used to determine if the centre indicator light is flashing.
     */
    private Boolean flashing = false;
    
    /**
     * The identity of the points.
     */
    private StringProperty pointID = new SimpleStringProperty();
    
    /**
     * This property holds the angle of the switch orientation.
     */
    private DoubleProperty switchOrientation = new SimpleDoubleProperty(0.0);
    
    /**
     * This method returns the value of the switchOrientation property.
     * @return 
     */
    public Double getSwitchOrientation () {return this.switchOrientation.get();}
    
    /**
     * This method sets the value of the switchOrientation property.
     * @param value 
     */
    public void setSwitchOrientation(Double value) {this.switchOrientation.set(value);}
    
    /**
     * This method returns the identity of the points that the IPS represents.
     * @return <code>String</code> The identity of the points.
     */
    public String getPointID() {return this.pointID.get();}
    
    /**
     * This method sets the identity of the points that the IPS represent.
     * @param pointIdentity <code>String</code> The identity of the points.
     */
    public void setPointID(String pointIdentity) {this.pointID.set(pointIdentity);}
    
    /**
     * A Label that shows the identity of the points.
     */
    @FXML 
    private Label pointIdentity;
    
    /**
     * The indication that the IPS displays.
     */
    private Indication indication;
    
    /**
     * The circle used to represent the IPS.
     */
    @FXML
    private Circle clickTarget;
    
    // The circle used to represent the Normal Indication Light.
    @FXML
    private Circle normalIndication;
    
    /**
     * The circle used to represent the Centre Indication Light.
     */
    @FXML
    private Circle centreIndication;
    
    /**
     * The circle used to represent the Reverse Indication Light.
     */
    @FXML
    private Circle reverseIndication;
    
    @FXML
    private Line indicationLine;
    
    /**
     * The object used to load the applicable FXML file.
     */
    private FXMLLoader fxmlLoader;
    
    /**
     * The default (constructor) method for an Individual Point Switch.
     */
    public IndividualPointSwitch () {
        
        // Get a reference to the FXML file.
        this.fxmlLoader = new FXMLLoader(getClass().getResource("IndividualPointSwitch.fxml"));
        
        // Set the root and Controller Objects
        this.setRoot();
        this.setController();
        
        // Attempt to load the FXML file.
        try {
            fxmlLoader.load();
        } catch (IOException e) {}
        
        // Set what happens when a user clicks on the IPS button.
        clickTarget.setOnMouseClicked(e -> {
            
            switch (e.getButton()) {
                case PRIMARY: // Left Mouse Button.
                    this.setSwitchOrientation(SwitchOrientation.NORMAL.getSwitchPosition());
                    break;
                case MIDDLE: // Middle Button.
                    this.setSwitchOrientation(SwitchOrientation.CENTRE.getSwitchPosition());
                    break;
                case SECONDARY: // Right Mouse Button.
                    this.setSwitchOrientation(SwitchOrientation.REVERSE.getSwitchPosition());
                    break;
            }
            
        });
        
        this.pointIdentity.textProperty().bind(pointID); // Bind the pointID property to the relevant label.
        this.setIndication(Indication.CENTRE); // Default indication.
        
        this.switchOrientation.addListener(new ChangeListener(){
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                indicationLine.setRotate((double) newValue);
                
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
     * This method is used to set the indication shown on the Individual Point Switch.
     * @param indication <code>Indication</code> A constants representing the indication state.
     */
    public void setIndication (Indication indication) {
        
        this.indication = indication;
        
        switch (this.indication) {
            
            case NORMAL:
                this.flashing = false;
                this.normalIndication.setFill (Color.YELLOW);
                this.centreIndication.setFill (Color.DARKGREY);
                this.reverseIndication.setFill (Color.DARKGREY);
                break;
                
            case CENTRE:
                this.flashing = true;
                this.normalIndication.setFill (Color.DARKGREY);
                this.reverseIndication.setFill (Color.DARKGREY);
                
                // The following code block flashes the middle light.
                new Thread (() -> {
                
                    while (this.flashing) {
                        
                        try {
                            
                            Thread.sleep (330);
                            
                        } catch (InterruptedException ex) {}
                        
                        if (this.centreIndication.getFill() == Color.DARKGREY) {
                            
                            this.centreIndication.setFill (Color.YELLOW);
                            
                        } else {
                            
                            this.centreIndication.setFill (Color.DARKGREY);
                            
                        }
                    }
                
                }).start();
                
                break;
                
            case REVERSE:
                this.flashing = false;
                this.normalIndication.setFill (Color.WHITE);
                this.centreIndication.setFill (Color.WHITE);
                this.reverseIndication.setFill (Color.YELLOW);
                break;
        }
    }
}
