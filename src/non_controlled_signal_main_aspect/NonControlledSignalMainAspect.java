package non_controlled_signal_main_aspect;

import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
 * This Class provides N/X Panel indication of a Main Aspect Signal, not under control.
 *
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public class NonControlledSignalMainAspect extends AnchorPane {

    private FXMLLoader fxmlLoader;
    private final IntegerProperty red = new SimpleIntegerProperty(135);
    public int getRed() {return red.get();}
    public void setRed(int value) {red.set(value);}
    private final IntegerProperty green = new SimpleIntegerProperty(199);
    public int getGreen() {return green.get();}
    public void setGreen(int value) {green.set(value);}
    private final IntegerProperty blue = new SimpleIntegerProperty(132);
    public int getBlue() {return blue.get();}
    public void setBlue(int value) {blue.set(value);}
    
    @FXML
    private Circle redAspect;
    
    @FXML
    private Circle greenAspect;
    
    @FXML
    private Circle shuntAspect;
    
    @FXML
    private Line orientationLineOne;
    
    @FXML
    private Line orientationLineTwo;
    
    @FXML
    private Group subSignalGroup;
    
    @FXML
    private Group signalGroup;
    
    @FXML
    private Group automatic;
    
    @FXML
    private Group semi;
    
    @FXML
    private Line gapFiller;
    
    private final ObjectProperty <LinePosition> linePosition = new SimpleObjectProperty<>(LinePosition.POSITION_ONE);
    public LinePosition getLinePosition() {return linePosition.get();}
    public void setLinePosition(LinePosition value) {linePosition.set(value);}
    
    private final BooleanProperty showSubsidiary = new SimpleBooleanProperty(false);
    public boolean isShowSubsidiary() {return showSubsidiary.get();}
    public void setShowSubsidiary(boolean value) {showSubsidiary.set(value);}
    
    private final BooleanProperty showAuto = new SimpleBooleanProperty(false);
    public boolean isShowAuto() {return showAuto.get();}
    public void setShowAuto(boolean value) {showAuto.set(value);}

    private final BooleanProperty showSemi = new SimpleBooleanProperty(false);
    public boolean isShowSemi() {return showSemi.get();}
    public void setShowSemi(boolean value) {showSemi.set(value);}

    private final DoubleProperty symbolRotate = new SimpleDoubleProperty(0.0);
    public double getSymbolRotate() {return symbolRotate.get();}
    public void setSymbolRotate(double value) {symbolRotate.set(value);}
    
    private final BooleanProperty fillGap = new SimpleBooleanProperty(true);
    public boolean isFillGap() {return fillGap.get();}
    public void setFillGap(boolean value) {fillGap.set(value);}

    public NonControlledSignalMainAspect () {
        
        // Get a reference to the FXML file.
        this.fxmlLoader = new FXMLLoader(getClass().getResource("NonControlledSignalMainAspect.fxml"));
        
        // Set the root and Controller Objects
        this.setRoot();
        this.setController();
        
        // Attempt to load the FXML file.
        try {
            
            fxmlLoader.load();
            
        } catch (IOException e) {}
        
        this.updateColor();
        
        this.red.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                updateColor();
                
            }
        });
        
        this.green.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                updateColor();
                
            }
        });
        
        this.blue.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                updateColor();
                
            }
        });
        
        this.linePosition.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            
                if (((LinePosition) newValue).equals(LinePosition.POSITION_ONE)) {
                    
                    orientationLineOne.setVisible(true);
                    orientationLineTwo.setVisible(false);
                    
                } else {
                
                    orientationLineOne.setVisible(false);
                    orientationLineTwo.setVisible(true);
                    
                }
            }
        });
        
        this.subSignalGroup.visibleProperty().bind(this.showSubsidiary);
        this.signalGroup.rotateProperty().bind(this.symbolRotate);
        this.automatic.visibleProperty().bind(this.showAuto);
        this.semi.visibleProperty().bind(this.showSemi);
        this.gapFiller.visibleProperty().bind(this.fillGap);
        
    }
    
    private void updateColor() {
        
        this.redAspect.setFill (Color.rgb(this.getRed(), this.getGreen(), this.getBlue()));
        this.greenAspect.setFill (Color.rgb(this.getRed(), this.getGreen(), this.getBlue()));
        this.shuntAspect.setFill (Color.rgb(this.getRed(), this.getGreen(), this.getBlue()));
        
    }
    
    /**
     * This method sets the root object.
     */
    private void setRoot() {this.fxmlLoader.setRoot(this);}
    
    /**
     * This method sets the FXML Controller object.
     */
    private void setController() {this.fxmlLoader.setController(this);}
    
}
