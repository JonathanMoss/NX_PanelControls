package track_section;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Jonathan Moss
 * @version v1.0 October 2016
 */
public class BlockJoint extends StackPane{

    private FXMLLoader fxmlLoader;
    
    public BlockJoint() {
        
        // Get a reference to the FXML file.
        this.fxmlLoader = new FXMLLoader(getClass().getResource("BlockJoint.fxml"));
        
        // Set the root and Controller Objects
        this.setRoot();
        this.setController();
        
        // Attempt to load the FXML file.
        try {
            
            fxmlLoader.load();
            
        } catch (IOException e) {}

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
