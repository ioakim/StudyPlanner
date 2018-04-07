package studyplanner;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import studyplanner.Model.Assignment;
import studyplanner.Model.Milestone;
import studyplanner.Model.Module;
import studyplanner.Model.StudyProfile;
import studyplanner.Model.Task;

/**
 * FXML Controller class for a milestone view
 *
 * @author Michail Krugliakov 100136484
 */
public class CreateMilestoneViewController
        extends CreateObjectiveViewController
        implements Initializable {
    
    @FXML private ListView taskListView;
    @FXML private ListView addedTaskListView;
    
    @FXML private Label createMilestoneErrorLabel;
    
    @Override
    void createButtonClick() {
        //NOTE: OPTIONAL FIELDS ARE: Description
        
        //not checking if module combo box = null because it is assumed to be null
        //if assignment is not selected
        if(
            assignmentComboBox.getValue() == null
            ||nameTextField.getText().trim().isEmpty()
            ||addedTaskListView.getItems().isEmpty()
          ){
            createMilestoneErrorLabel.setText("Mandatory fields are empty");
        }else{
            //selects assignment to add the milestone to
            Assignment assignment = (Assignment) assignmentComboBox.getValue();
            //>>>>ADD CHECKS AND PROPER READING OF INPUT FIELDS.
            
            List<Task> addedTasksList = addedTaskListView.getItems();
            ArrayList<Task> addedTasksArrayList;
            if (addedTasksList instanceof ArrayList<?>) {
                addedTasksArrayList = (ArrayList<Task>) addedTasksList;
            } else {
                addedTasksArrayList = new ArrayList<>(addedTasksList);
            }
            Milestone milestone = new Milestone();
            milestone.setName(nameTextField.getText());
            milestone.setDescription(descriptionTextArea.getText());
            milestone.setStart(new Date());
            milestone.setEnd(java.sql.Date.valueOf(dueDatePicker.getValue()));
            milestone.setTasks(addedTasksArrayList);
            
            assignment.addMilestone(milestone); 

            stage.hide();
        }
    }
    
    @FXML private void addTasksButtonClick(){
        addedTaskListView.getItems().setAll(taskListView.getSelectionModel().getSelectedItems());
    }
    public void initData(StudyProfile profile, Module module, Assignment assign,
                StudyProfileViewController mainController){
        
        if(module != null) moduleComboBox.setValue(module);
        if(assign != null && !assign.getEnd().before(new Date())) assignmentComboBox.setValue(assign);
        stage = this.fetchStage();
        
        moduleComboBox.getItems().addAll(profile.getModules());
        
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.addChangeListeners(moduleComboBox, assignmentComboBox);
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    
    
    @Override
    public void addChangeListeners(ComboBox modules, ComboBox assignments){
        modules.valueProperty().addListener(new ChangeListener<Module>() {
            @Override 
            public void changed(ObservableValue ov, Module prev, Module cur) {
                //resets value to zero so that user can't create milestone
                //with incompatible modules and assignments
                if(assignments.getValue() != null){
                    assignments.setValue(null);
                }
                taskListView.getItems().clear();
                addedTaskListView.getItems().clear();
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
                        taskListView.getItems().setAll(cur.getTasks());
                    }
                    else
                    {
                        taskListView.getItems().clear();
                        addedTaskListView.getItems().clear();
                    }                       
                }
            });
    }

    
    
}
