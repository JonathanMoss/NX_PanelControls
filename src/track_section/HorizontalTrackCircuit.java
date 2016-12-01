/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package track_section;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import static javafx.scene.layout.StackPane.setAlignment;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author joth
 */
public class HorizontalTrackCircuit extends StackPane {

    @FXML private Rectangle trackCircuit;
    private final DoubleProperty trackCircuitLength = new SimpleDoubleProperty(200);
    public double getTrackCircuitLength() {return trackCircuitLength.get();}
    public void setTrackCircuitLength(double value) {trackCircuitLength.set(value);}
    
    private final IntegerProperty numberOfIndications = new SimpleIntegerProperty(0);
    public int getNumberOfIndications() {return numberOfIndications.get();}
    public void setNumberOfIndications(int value) {numberOfIndications.set(value);}
    
    private ArrayList <Group> trackCircuitIndicationGroup = new ArrayList<>();
    private ArrayList <Rectangle> trackCircuitRouteIndications = new ArrayList<>();
    private ArrayList <Rectangle> trackCircuitOccupationIndications = new ArrayList<>();
    
    private final ObjectProperty <TrackCircuitIndications> trackCircuitIndication = new SimpleObjectProperty <> (TrackCircuitIndications.CLEAR);
    public TrackCircuitIndications getTrackCircuitIndication() {return trackCircuitIndication.get();}
    public void setTrackCircuitIndication(TrackCircuitIndications value) {trackCircuitIndication.set(value);}
    
    private final ObjectProperty <TrackCircuitColourScheme> trackCircuitColour = new SimpleObjectProperty <> (TrackCircuitColourScheme.BROWN);
    public TrackCircuitColourScheme getTrackCircuitColour() {return trackCircuitColour.get();}
    public void setTrackCircuitColour(TrackCircuitColourScheme value) {trackCircuitColour.set(value);}
    
    private InnerShadow innerShadow = new InnerShadow();
    private HBox lightBox = new HBox();
    private FXMLLoader fxmlLoader;

    public HorizontalTrackCircuit () {
        
        this.innerShadow.setHeight(10.0);
        this.innerShadow.setRadius(7.0);
        this.innerShadow.setWidth(20.0);
        
        // Get a reference to the FXML file.
        this.fxmlLoader = new FXMLLoader(getClass().getResource("HorizontalTrackCircuit.fxml"));
        
        // Set the root and Controller Objects
        this.setRoot();
        this.setController();
        
        // Attempt to load the FXML file.
        try {
            
            fxmlLoader.load();
            
        } catch (IOException e) {}
        
        this.setAlignment(Pos.CENTER);
        
        this.trackCircuit.widthProperty().bind(trackCircuitLength);
        
        this.numberOfIndications.addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                addIndication();
                
            }
            
        });
        
        this.trackCircuitIndication.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                showIndication();
                
            }
        });
        
        this.trackCircuitColour.addListener(new ChangeListener () {
            
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                trackCircuit.setFill(Color.web(getTrackCircuitColour().getHexValue()));
                
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

        this.lightBox.setPrefWidth(this.getTrackCircuitLength());
        this.lightBox.setPrefHeight(25);
        this.lightBox.setMinHeight(25);
        this.lightBox.setAlignment(Pos.CENTER);
        setAlignment(this.lightBox, Pos.CENTER);
        this.getChildren().add(this.lightBox);
        this.showIndication();

    }
}
