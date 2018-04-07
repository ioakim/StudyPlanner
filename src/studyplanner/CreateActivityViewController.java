package studyplanner;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;
import studyplanner.Model.Activity;
import studyplanner.Model.Assignment;
import studyplanner.Model.Criterion;
import studyplanner.Model.CriterionType;
import studyplanner.Model.Module;
import studyplanner.Model.StudyProfile;
import studyplanner.Model.Task;

/**
 * Controller for task creation window
 * @author Michail Krugliakov 100136484, Kiril Chomaniuk
 */
public class CreateActivityViewController 
        extends CreateViewController 
        implements Initializable {
    private final int MAX_NAME_LENGTH = 30;
    private StudyProfile profile;
    private Task selectedTask;

    
    @FXML ComboBox<Task> taskComboBox; //assignment selection box   
    @FXML ComboBox<Criterion> criterionComboBox;
      
    @FXML TextField criterionValue;
    @FXML Label criterionUOM;
    @FXML TextField nameTextField; //task name input field
    
  

    /**
     * checks for correctness of inputs and creates a new task in
     * selected assignment
     */
    @Override
    @FXML void createButtonClick(){
        Activity activity = new Activity();
           
        activity.setDescription(descriptionTextArea.getText());
        activity.setValue(Double.parseDouble(criterionValue.getText()));
        activity.updateCriterion(criterionComboBox.getValue());
        activity.setName(nameTextField.getText()); //
        criterionComboBox.getValue().updateTask(selectedTask);

        stage.hide();
    }
    
    public void initData(StudyProfile profile, Module module,
                Assignment assign, Task task,
                StudyProfileViewController mainController){
        
        if(module!=null) moduleComboBox.setValue(module);
        if(assign!=null) assignmentComboBox.setValue(assign);
        if(task!=null) taskComboBox.setValue(task);

        stage = this.fetchStage();
        
        this.profile = profile;
        
        moduleComboBox.getItems().addAll(profile.getModules());
        
       
    }

    /**
     * Initialises the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
         
        addChangeListeners(moduleComboBox,assignmentComboBox,taskComboBox,
                           criterionComboBox);
        addTextFieldRestrictionChangeListeners(nameTextField, criterionValue);
        Pattern validDoubleText = Pattern.compile("-?((\\d*)|(\\d+\\.\\d*))");
        TextFormatter<Double> textFormatter = new TextFormatter<Double>(new DoubleStringConverter(), 0.0, change
        -> {
            String newText = change.getControlNewText();
            if (validDoubleText.matcher(newText).matches()) return change;
            else return null;
            
        });
        criterionValue.setTextFormatter(textFormatter);
        textFormatter.valueProperty().addListener((obs, oldValue, newValue) ->
        {
            criterionValue.setText(newValue.toString());
        });
    }   
    
    public void addChangeListeners(ComboBox modules, ComboBox assignments,
                                   ComboBox tasks, ComboBox criteria){
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
        assignments.valueProperty().addListener(
            new ChangeListener<Assignment>(){
                @Override
                public void changed(ObservableValue ov, Assignment prev, Assignment cur){
                    if(tasks.getValue() != null){
                        tasks.setValue(null);
                    }
                    for (Task t : cur.getTasks()){
                        if(!t.isDone())
                            tasks.getItems().add(t);
                    }
                }
            });
        tasks.valueProperty().addListener(
                new ChangeListener<Task>(){
                    @Override
                    public void changed(ObservableValue ov, Task prev, Task cur){
                        selectedTask = cur;
                        if(criteria.getValue() != null){
                            criteria.setValue(null);
                        }
                        for (Criterion c : cur.getCriteria()){
                            if(!c.isMet())
                                criteria.getItems().add(c);
                        }
                    }
                });
        criteria.valueProperty().addListener(
                new ChangeListener<Criterion>(){
                    @Override
                    public void changed(ObservableValue ov, Criterion prev, Criterion cur){
                       
                        if(cur.getType().equals(CriterionType.Boolean)){
                            criterionUOM.setText("");
                            criterionValue.setDisable(true);
                        }
                        else{
                            criterionUOM.setText(cur.getUnitOfMeasure());
                            criterionValue.setDisable(false);
                        }
                        //criterionType = cur.getType();
                    }
                });
    }
    /**
     * restricts changes to limit length of name and disallow negative numbers
     * in value
     * @param nameTextField
     * @param criterionValue 
     */
    public void addTextFieldRestrictionChangeListeners(TextField nameTextField,
            TextField criterionValue){
            nameTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue.length()>MAX_NAME_LENGTH)
                    ((StringProperty)observable).setValue(oldValue);
                });
            criterionValue.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(isDouble (newValue)){
                        if(Double.parseDouble(newValue)<0)
                            ((StringProperty)observable).setValue(oldValue);
                    }
                });
    }
    
    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
