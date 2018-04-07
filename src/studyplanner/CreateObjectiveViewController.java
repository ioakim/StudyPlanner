package studyplanner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import studyplanner.Model.Assignment;
import studyplanner.Model.Module;

/**
 *
 * @author Doggo
 */
public abstract class CreateObjectiveViewController extends CreateViewController{
    @FXML protected DatePicker dueDatePicker; //deadline date picker
    @FXML protected TextField nameTextField; //name input field

    public DatePicker getDueDatePicker() {
        return dueDatePicker;
    }

    public void setDueDatePicker(DatePicker dueDatePicker) {
        this.dueDatePicker = dueDatePicker;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }
    
    public void addChangeListeners(ComboBox modules, ComboBox assignments){
        modules.valueProperty().addListener(new ChangeListener<Module>() {
            @Override 
            public void changed(ObservableValue ov, Module prev, Module cur) {
                //resets value to zero so that user can't create task
                //with incompatible modules and assignments
                if(assignments.getValue() != null){
                    assignments.setValue(null);
                }
                ArrayList<Assignment> beforeDeadlineAssign = new ArrayList<>();
                for(Assignment assign : cur.getAssignments()){
                    if(!assign.getEnd().before(new Date())){
                        beforeDeadlineAssign.add(assign);
                    }
                }
                assignments.getItems().setAll(beforeDeadlineAssign);
            }    
        });
        assignments.valueProperty().addListener(new ChangeListener<Assignment>(){
                @Override
                public void changed(ObservableValue ov, Assignment prev, Assignment cur){
                    if(cur!=null){
                        updateDatePicker(dueDatePicker, cur);
                    }
                }
            });
    }
    
    protected void updateDatePicker(DatePicker datePicker, Assignment assign){
        datePicker.setValue(LocalDate.now());
        datePicker.setDisable(false);
        LocalDate assignDeadline = 
                new java.sql.Date(assign.getEnd().getTime()).toLocalDate();
        
        final Callback<DatePicker, DateCell> dayCellFactory = 
            (final DatePicker datePicker1) -> new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (item.isBefore(LocalDate.now()) 
                                            || item.isAfter(assignDeadline)){
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");   
                    }
                }
            };
        datePicker.setDayCellFactory(dayCellFactory);
    }
}
