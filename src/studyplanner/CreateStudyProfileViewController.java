package studyplanner;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import studyplanner.Model.StudyProfile;

/**
 * Controller for study profile creation window.
 *
 * @author Michail Krugliakov 100136484
 */
public class CreateStudyProfileViewController implements Initializable {
    //reference to a study planner controller so that a newly created profile
    //may be passed on to it and added to a Study Planner list view
    private StudyPlannerViewController mainController;      
    
    //shortcut fields to make accessing this controller's view's stage easier
    @FXML private AnchorPane createProfileWindow;
    private Stage stage;
    
    @FXML private Button createProfileButton; //button to create a profile
    @FXML private Button cancelProfileButton; //button to close profile creation
    @FXML private Button browseButton;        //button to browse to a data file  
    
    @FXML private TextField profileNameField; //field to input profile's name   
    @FXML private TextField dataFilePathField;//field to input path to hub file
    
    @FXML private Label errNameLabel, errDataLabel; //labels to handle
                                                    //user input errors
    
    
    //TODO IF FILE IS BAD FOR HEALTH, MAKE SURE IT DOESNT GET LOADED
    /**
     * Creates a new Study Profile and closes profile creation window.
     */
    @FXML private void createProfileButtonClick(){
        //INPUT ERROR HANDLING~~~~~~~~~~~
        //reset error labels so that they dynamically change if user
        //fixes or introduces new errors after clicking the creation button
        errNameLabel.setText("");
        errDataLabel.setText("");
        
        //flags to check if input fields are empty
        Boolean nameFieldIsEmpty = profileNameField.getText().trim().equals("");
        Boolean dataFieldIsEmpty = dataFilePathField.getText().trim().equals("");
        
        if(nameFieldIsEmpty){
            errNameLabel.setText("name field is empty");
        }
        if(dataFieldIsEmpty){
            errDataLabel.setText("data field is empty");
        }
        //END OF INPUT ERROR HANDLING~~~~~~~~~~~~TODO ADD MAX CHARACTER LENGTH TO NAME, MAYBE BLOCK SOME SPECIAL CHARACTERS, WRONG FILE etc.
        
        //if no errors are detected, then the input data is used to create a 
        //new profile
        if(!nameFieldIsEmpty && !dataFieldIsEmpty){
            
            StudyProfile profile = new StudyProfile();
            File hubFile = new File(dataFilePathField.getText());
            
            profile.setName(profileNameField.getText());
            //data from hubFile is added into profile
            StudyProfile.InitialiseStudyProfile(profile, hubFile);
            //fully initialised profile is added to a list of profiles
            //in study planner view.
            mainController.addProfileToListView(profile);
            
            stage.hide();
        }    
    }
    /**
     * Closes StudyProfileView's window
     */
    @FXML private void cancelButtonClick(){
        stage.hide();
    }
    /**
     * Opens new window so that user may browse to a proper hub file
     */
    @FXML private void browseButtonClick(){
        errDataLabel.setText("");
        dataFilePathField.setText("");
        
        final FileChooser fileChooser = new FileChooser();
        //configuring FileChooser so that it is more user friendly.
        
        mainController.configureFileChooser(fileChooser);
        
        //opening new fileChooser window on this stage
        File file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            if(StudyProfile.isValid(file)){
                dataFilePathField.setText(file.getPath());
            }else{
                errDataLabel.setText("Invalid file selected");
            }
        }
        
    }
    
    /**
     * Gives this controller a reference to the main StudyPlanner controller
     * and initialises a shortcut variable for CreateStudyProfileView's stage
     * @param controller - main controller
     */
    public void initData(StudyPlannerViewController controller){
        stage = (Stage) createProfileWindow.getScene().getWindow();
        this.mainController = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}   
