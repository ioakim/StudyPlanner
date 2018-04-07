package studyplanner;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
 * Controller for task creation window
 * @author Michail Krugliakov 100136484
 */
public class CreateTaskViewController 
        extends CreateObjectiveViewController 
        implements Initializable {
    
    @FXML TextField typeTextField; //task type input field
    @FXML TextField criterionNameTextField,
                    criterionValueTextField,
                    criterionUOMTextField;
    
    @FXML ListView dependencyListView; //list of tasks this tasks depends on
    
    @FXML TableView<Criterion> criteriaTableView; //list of criteria to meet this task
        @FXML TableColumn criterionName;
        @FXML TableColumn criterionValue;
        @FXML TableColumn criterionUOM;
    
    @FXML Button   addCriterionButton;              //adds an empty criterion
    @FXML Label createTaskErrorLabel;
    
    /**
     * adds an editable criterion to criteriaTableView
     */
    @FXML private void addCriterionButtonClick(){
        if(!criterionNameTextField.getText().trim().isEmpty()){
            Criterion criterion = new Criterion(criterionNameTextField.getText());
            
            boolean valueTextFieldIsEmpty = criterionValueTextField.getText().trim().isEmpty();
            boolean uomTextFieldIsEmpty = criterionUOMTextField.getText().trim().isEmpty();

            if(!valueTextFieldIsEmpty && !uomTextFieldIsEmpty){
                criterion.setValue(Double.valueOf(criterionValueTextField.getText()));
                criterion.setUnitOfMeasure(criterionUOMTextField.getText());
            }
            criteriaTableView.getItems().add(criterion);

            criterionNameTextField.setText("");
            criterionValueTextField.setText("");
            criterionUOMTextField.setText("");
        }
    }
    /**
	 * checks for correctness of inputs and creates a new task inselected assignment
	 */
    @Override
	@FXML void createButtonClick(){
        //NOTE: OPTIONAL FIELDS ARE: Description, type, dependencies.
        
        //not checking if module combo box = null because it is assumed to be null
        //if assignment is not selected
        if(
            assignmentComboBox.getValue() == null
            ||nameTextField.getText().trim().isEmpty()
            ||criteriaTableView.getItems().isEmpty()
          ){
            createTaskErrorLabel.setText("Mandatory fields are empty");
        }else{
            //selects assignment to add the task to
            Assignment assignment = (Assignment) assignmentComboBox.getValue();
            //>>>>ADD CHECKS AND PROPER READING OF INPUT FIELDS.
            Task task = new Task();
            task.setName(nameTextField.getText());
            task.setType(typeTextField.getText());
            task.setDescription(descriptionTextArea.getText());
            task.setStart(new Date());
            task.setEnd(java.sql.Date.valueOf(dueDatePicker.getValue()));
            task.getCriteria().addAll(criteriaTableView.getItems());

            assignment.addTask(task); 

            stage.hide();
        }
    }
    
    public void initData(StudyProfile profile, Module module, Assignment assign,
                StudyProfileViewController mainController){
        
        if(module != null) moduleComboBox.setValue(module);
        if(assign != null && !assign.getEnd().before(new Date())) assignmentComboBox.setValue(assign);
        stage = this.fetchStage();
        
        moduleComboBox.getItems().addAll(profile.getModules());
        
    }

    /**
     * Initialises the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dueDatePicker.setValue(LocalDate.now());    
        
        criterionName.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        criterionValue.setCellValueFactory(
                new PropertyValueFactory<>("value"));
        criterionUOM.setCellValueFactory(
                new PropertyValueFactory<>("unitOfMeasure"));
        
        this.addChangeListeners(moduleComboBox, assignmentComboBox);
   }
}
