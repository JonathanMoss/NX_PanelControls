package track_section;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * This Class provides Diagonal Track Circuit Indications for an NX Panel.
 * 
 * @author Jonathan Moss
 * @version v1.0 November 2016
 */
public final class DiagonalTrackCircuit extends StackPane {

    private final StringProperty identifier = new SimpleStringProperty("");
    public String getIdentifier() {return identifier.get();}
    public void setIdentifier(String value) {identifier.set(value);}

    private final IntegerProperty numberOfIndications = new SimpleIntegerProperty(0);
    public int getNumberOfIndications() {return numberOfIndications.get();}
    public void setNumberOfIndications(int value) {numberOfIndications.set(value);}    

    private final ObjectProperty <TrackCircuitIndications> trackCircuitIndication = new SimpleObjectProperty <> (TrackCircuitIndications.CLEAR);
    public TrackCircuitIndications getTrackCircuitIndication() {return trackCircuitIndication.get();}
    public void setTrackCircuitIndication(TrackCircuitIndications value) {trackCircuitIndication.set(value);}

    private final ObjectProperty <TrackCircuitOrientation> trackCircuitOrientation = new SimpleObjectProperty <> (TrackCircuitOrientation.FORWARD);
    public TrackCircuitOrientation getTrackCircuitOrientation() {return trackCircuitOrientation.get();}
    public void setTrackCircuitOrientation(TrackCircuitOrientation value) {trackCircuitOrientation.set(value);}
    
    private final ObjectProperty <TrackCircuitColourScheme> trackCircuitColour = new SimpleObjectProperty <> (TrackCircuitColourScheme.BROWN);
    public TrackCircuitColourScheme getTrackCircuitColour() {return trackCircuitColour.get();}
    public void setTrackCircuitColour(TrackCircuitColourScheme value) {trackCircuitColour.set(value);}

    private final DoubleProperty length = new SimpleDoubleProperty(200);
    public double getLength() {return length.get();}
    public void setLength(double value) {length.set(value);}
    
    private final double WIDTH = 28.0;
    private double FORWARD_START_X;
    
    private Double[] forward = new Double[8];
    private Double[] backward = new Double[8];
    
    private ArrayList <Group> trackCircuitIndicationGroup = new ArrayList<>();
    private ArrayList <Rectangle> trackCircuitRouteIndications = new ArrayList<>();
    private ArrayList <Rectangle> trackCircuitOccupationIndications = new ArrayList<>();
    
    private InnerShadow innerShadow = new InnerShadow();
    
    private HBox lightBox = new HBox();

    @FXML
    private Polygon diagTrack;
    
    private FXMLLoader fxmlLoader;
    
    public DiagonalTrackCircuit() {

        this.innerShadow.setHeight(10.0);
        this.innerShadow.setRadius(7.0);
        this.innerShadow.setWidth(20.0);
        
        this.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        this.setMaxSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        this.setMinSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        
        // Get a reference to the FXML file.
        this.fxmlLoader = new FXMLLoader(getClass().getResource("DiagonalTrackCircuit.fxml"));
        
        // Set the root and Controller Objects
        this.setRoot();
        this.setController();
        
        // Attempt to load the FXML file.
        try {
            
            fxmlLoader.load();
            
        } catch (IOException e) {}
        
        this.FORWARD_START_X = this.getLength();
        
        this.trackCircuitOrientation.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                switch ((TrackCircuitOrientation) newValue) {
                    
                    case FORWARD:
                        showForward();
                        break;
                    case BACKWARD:
                        showBackward();
                        break;
                }
                
            }
        });
        
        this.trackCircuitColour.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                diagTrack.setFill(Color.web(getTrackCircuitColour().getHexValue()));
                
            }
        });
        
        this.length.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                switch (getTrackCircuitOrientation()) {
                    
                    case FORWARD:
                        showForward();
                        break;
                    case BACKWARD:
                        showBackward();
                        break;
                }
                
            }
        });
        
        this.trackCircuitIndication.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                showIndication();
                
            }
        });
        
        this.numberOfIndications.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                addIndication();
                
            }
            
        });

        this.showForward();
        
    }  
    
    private void showIndication () {
        
        switch (this.getTrackCircuitIndication()) {
            
            case ROUTE:
                
                this.trackCircuitRouteIndications.forEach((value) -> {
        
                    value.setFill(Color.WHITE);
            
                });
                
                this.trackCircuitOccupationIndications.forEach((value) -> {
        
                    value.setFill(Color.SLATEGREY);
            
                });
                
                break;
                
            case OCCUPIED:
                
                this.trackCircuitRouteIndications.forEach((value) -> {
        
                    value.setFill(Color.SLATEGREY);
            
                });
                
                this.trackCircuitOccupationIndications.forEach((value) -> {
        
                    value.setFill(Color.RED);
            
                });
                
                break;
                
            case CLEAR:
                
                this.trackCircuitRouteIndications.forEach((value) -> {
        
                    value.setFill(Color.SLATEGREY);
            
                });
                
                this.trackCircuitOccupationIndications.forEach((value) -> {
        
                    value.setFill(Color.SLATEGREY);
            
                });
                
                break;
        }
        
    }
    
    private void showBackward () {
        
        this.diagTrack.getPoints().clear();

        this.backward[0] = 0.0;
        this.backward[1] = 0.0;
        this.backward[2] = this.WIDTH;
        this.backward[3] = 0.0;
        this.backward[4] = (this.getLength() + WIDTH);
        this.backward[5] = this.getLength();
        this.backward[6] = this.getLength();
        this.backward[7] = this.getLength();
        
        this.diagTrack.getPoints().addAll(backward);
        
        this.addIndication();

    }
    
    private void showForward () {
    
        this.diagTrack.getPoints().clear();

        this.forward[0] = this.FORWARD_START_X;
        this.forward[1] = 0.0;
        this.forward[2] = (FORWARD_START_X + WIDTH);
        this.forward[3] = 0.0;
        this.forward[4] = ((FORWARD_START_X + WIDTH) - this.getLength());
        this.forward[5] = this.getLength();
        this.forward[6] = ((FORWARD_START_X) - this.getLength());
        this.forward[7] = this.getLength();
        
        this.diagTrack.getPoints().addAll(forward);
        
        this.addIndication();
        
    }
    
    private void addIndication () {
        
        if (this.getChildren().contains(this.lightBox)) {
            
            this.getChildren().remove(this.lightBox);
            
        }

        if (!this.lightBox.getChildren().isEmpty()) {
            
            this.lightBox.getChildren().clear();
            
        }

        if (!this.trackCircuitIndicationGroup.isEmpty()) {
            
            this.trackCircuitIndicationGroup.clear();
            
        }
        
        if (!this.trackCircuitOccupationIndications.isEmpty()) {
            
            this.trackCircuitOccupationIndications.clear();
            this.trackCircuitRouteIndications.clear();
            
        }

        for (int i = 0; i < this.getNumberOfIndications(); i++) {

            Rectangle route = new Rectangle(0.0, 0.0, 25.0, 14.0);
            route.setStroke(Color.BLACK);
            route.setStrokeWidth(1.0);
            route.setEffect(this.innerShadow);
            this.trackCircuitRouteIndications.add(route);

            Rectangle light = new Rectangle(25.0, 0.0,25.0, 14.0);
            light.setStroke(Color.BLACK);
            light.setStrokeWidth(1.0);
            light.setEffect(this.innerShadow);
            this.trackCircuitOccupationIndications.add(light);

            Group indication = new Group();
            indication.getChildren().addAll(route, light);
            this.trackCircuitIndicationGroup.add(indication);

        }
        
        if (this.getNumberOfIndications() == 1) {
            
            Region filler1 = new Region();
            this.lightBox.getChildren().add(filler1);
            this.lightBox.setHgrow(filler1, Priority.ALWAYS);
            this.lightBox.getChildren().add(this.trackCircuitIndicationGroup.get(0));
            Region filler2 = new Region();
            this.lightBox.getChildren().add(filler2);
            this.lightBox.setHgrow(filler2, Priority.ALWAYS);
            
        } else if (this.getNumberOfIndications() != 0){
        
            for (int i = 0; i < this.trackCircuitIndicationGroup.size(); i++) {
                    
                    Region filler1 = new Region();
                    this.lightBox.getChildren().add(filler1);
                    this.lightBox.setHgrow(filler1, Priority.ALWAYS);
                    
                    this.lightBox.getChildren().add(this.trackCircuitIndicationGroup.get(i));
            
                if (i < (this.trackCircuitIndicationGroup.size() + 1)) {
                
                    Region filler = new Region();
                    this.lightBox.getChildren().add(filler);
                    this.lightBox.setHgrow(filler, Priority.ALWAYS);
                
                }
            }    
        }

        this.lightBox.setPrefWidth(this.getLength());
        this.lightBox.setPrefHeight(25);
        this.lightBox.setMinHeight(25);
        this.lightBox.setAlignment(Pos.CENTER);
        this.rotateLightBox();
        setAlignment(this.lightBox, Pos.CENTER);
        this.getChildren().add(this.lightBox);
        this.showIndication();

    }
    
    /**
     * This method sets the root object.
     */
    private void setRoot() {this.fxmlLoader.setRoot(this);}
    
    /**
     * This method sets the FXML Controller object.
     */
    private void setController() {this.fxmlLoader.setController(this);}
    
    private void rotateLightBox() {
    
        switch (this.getTrackCircuitOrientation()) {
            
            case FORWARD:
                this.lightBox.setRotate(135.0);
                break;
                
            case BACKWARD:
                this.lightBox.setRotate(45.0);
                break;
        
        }
    }
}
