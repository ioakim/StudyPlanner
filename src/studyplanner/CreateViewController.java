package studyplanner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Controller with basic functionality for creation windows.
 * @author Michail Krugliakov 100136484
 */
abstract class CreateViewController implements Initializable{
    @FXML protected ComboBox moduleComboBox;
    @FXML protected ComboBox assignmentComboBox;
    @FXML protected TextArea descriptionTextArea;
    @FXML protected Button cancelButton, createButton;
    
    protected Stage stage;   
    
    public ComboBox getModuleComboBox() {
        return moduleComboBox;
    }

    public void setModuleComboBox(ComboBox moduleComboBox) {
        this.moduleComboBox = moduleComboBox;
    }

    public ComboBox getAssignmentComboBox() {
        return assignmentComboBox;
    }

    public void setAssignmentComboBox(ComboBox assignmentComboBox) {
        this.assignmentComboBox = assignmentComboBox;
    }

    public TextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public void setDescriptionTextArea(TextArea descriptionTextArea) {
        this.descriptionTextArea = descriptionTextArea;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public void setCreateButton(Button createButton) {
        this.createButton = createButton;
    }
         
    /**
     * Hides this controller's view window if stage is initialized
     */
    @FXML protected void cancelButtonClick(){
        if(stage != null) stage.hide();  
    }
    
    @FXML abstract void createButtonClick();
    /**
     * Gets the stage belonging to this controller's view
     * @return - stage object cancelButton belongs to
     */
    protected Stage fetchStage(){
        return (Stage)cancelButton.getScene().getWindow();
    }

    @Override
    public abstract void initialize(URL location, ResourceBundle resources);
}
