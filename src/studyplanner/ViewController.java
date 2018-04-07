package studyplanner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Michail Krugliakov 100136484
 */
public abstract class ViewController implements Initializable {

    @FXML protected TextField nameTextField;
    
   
    protected Stage stage;   
    
    protected Stage fetchStage(){
        return (Stage)nameTextField.getScene().getWindow();
    }
}
