package studyplanner;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import studyplanner.Model.Objective;

/**
 *
 * @author Doggo
 */
public abstract class ObjectiveViewController extends ViewController{
    
    @FXML protected Button cancelButton, updateButton;
    @FXML protected TextArea descriptionTextArea;
    @FXML protected TextField startDateTextField;
    @FXML protected DatePicker endDatePicker;
    
    public void initializeViewFromObjective(Objective objective){
        nameTextField.setText(objective.getName());
        descriptionTextArea.setText(objective.getDescription());
        startDateTextField.setText(objective.getStart().toString());
        endDatePicker.setValue(
                new java.sql.Date(objective.getEnd().getTime()).toLocalDate());
        
        final Callback<DatePicker, DateCell> dayCellFactory = 
            (final DatePicker datePicker1) -> new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (item.isBefore(LocalDate.now()) 
                                            || item.isAfter(endDatePicker.getValue())){
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");   
                    }
                }
            };
        endDatePicker.setDayCellFactory(dayCellFactory);
         
    }
    /**
     * Hides this controller's view window if stage is initialized
     */
    @FXML protected void cancelButtonClick(){
        if(stage != null) stage.hide();  
    }
    
    @FXML abstract void updateButtonClick();
    
    abstract void addMandatoryFieldRestrictions();
}
