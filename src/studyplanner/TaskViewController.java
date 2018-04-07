package studyplanner;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import studyplanner.Model.Assignment;
import studyplanner.Model.Criterion;
import studyplanner.Model.Module;
import studyplanner.Model.StudyProfile;
import studyplanner.Model.Task;

/**
 * Controller for an editable view of a task
 * @author Michail Krugliakov 100136484
 */
public class TaskViewController 
        extends ObjectiveViewController
        implements Initializable {
    
    private Task task;
    
    @FXML TextField moduleTextField, assignmentTextField;
    @FXML TextField typeTextField; //task type input field
    
    @FXML ListView dependencyListView; //list of tasks this tasks depends on
    
    @FXML TableView<Criterion> criteriaTableView; //list of criteria to meet this task
        @FXML TableColumn criterionName;
        @FXML TableColumn criterionValue;
        @FXML TableColumn criterionUOM;            
    
    /**
     * checks for correctness of inputs and creates a new task in
     * selected assignment
     */
    @Override
    @FXML void updateButtonClick(){
        //NOTE: OPTIONAL FIELDS ARE: Description, dependencies.
        //>>>>ADD CHECKS AND PROPER READING OF INPUT FIELDS.
        
        List<Criterion> addedCriteriaList = criteriaTableView.getItems();
        ArrayList<Criterion> addedCriteriaArrayList;
        if (addedCriteriaList instanceof ArrayList<?>) {
            addedCriteriaArrayList = (ArrayList<Criterion>) addedCriteriaList;
        } else {
            addedCriteriaArrayList = new ArrayList<>(addedCriteriaList);
        }

        task.setName(nameTextField.getText());
        task.setType(typeTextField.getText());
        task.setDescription(descriptionTextArea.getText());
        task.setCriteria(addedCriteriaArrayList);
        task.setEnd(java.sql.Date.valueOf(endDatePicker.getValue()));
        
        stage.hide();
    }
    public void initData(StudyProfile profile, Task task){
        stage = this.fetchStage();
        
        this.task = task;
        profile.getModules().forEach((Module module) -> {
            module.getAssignments().forEach((Assignment assign) -> {
                for(Task t : assign.getTasks()){
                    if(t.equals(task)){
                        moduleTextField.setText(module.getName());
                        assignmentTextField.setText(assign.getName());
                    }
                }
            });
        });
        
        this.initializeViewFromObjective(task);
        typeTextField.setText(task.getType());
        criteriaTableView.getItems().setAll(task.getCriteria());
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criterionName.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        criterionValue.setCellValueFactory(
                new PropertyValueFactory<>("value"));
        criterionUOM.setCellValueFactory(
                new PropertyValueFactory<>("unitOfMeasure"));    
        addMandatoryFieldRestrictions();
    }    

    @Override
    void addMandatoryFieldRestrictions() {
        nameTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue.isEmpty())
                    ((StringProperty)observable).setValue("taskName");
                });
    }
}
