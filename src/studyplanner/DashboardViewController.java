package studyplanner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import studyplanner.Model.Assignment;
import studyplanner.Model.Module;
import studyplanner.Model.StudyProfile;
import studyplanner.Model.Task;
import studyplanner.StudyPlanner;

/**
 *
 * @author Doggo
 */
public class DashboardViewController implements Initializable{

    @FXML AnchorPane dashboardWindow; //shortcut fields to ease acess

    private StudyProfile profile;
    @FXML private TableView<Assignment> approachingTable;
    @FXML private TableView<Assignment> passedTable;
    @FXML private TableColumn<Assignment, String> approachingAssignment;
    @FXML private TableColumn<Assignment, Date> approachingDeadline;   
    @FXML private TableColumn<Assignment, String> passedAssignment;        
    @FXML private TableColumn<Assignment, Date> passedDeadline;      
    @FXML private AnchorPane dashboardAnchor;
    
//    public static void writeObject(Object obj) throws IOException{
//        FileOutputStream fos = new FileOutputStream("spfile.ser");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(obj);
//    }
    public void initData(StudyProfile profile, 
                StudyProfileViewController mainController){
        
        //this.profile = profile;


        PropertyValueFactory<Assignment,String> aName = new PropertyValueFactory<>("name");
        PropertyValueFactory<Assignment,Date> aDate = new PropertyValueFactory<>("end");
        
        approachingAssignment.setCellValueFactory(aName);
        approachingDeadline.setCellValueFactory(aDate);
        
        passedAssignment.setCellValueFactory(aName);
        passedDeadline.setCellValueFactory(aDate);
       
        
        
        Date current = new Date();
        for(Module m : profile.getModules()){
            for(Assignment a : m.getAssignments()){
                
                if(current.getTime() < a.getEnd().getTime()) 
                    approachingTable.getItems().add(a);
                    //toAddApproaching.add(a);
                else
                    passedTable.getItems().add(a);

            }
        }
        
        //approachingTable.setItems(toAddApproaching);
        //passedTable.setItems(toAddPassed);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
 
    }

    TableView getApproachingTable() {
        return approachingTable;
    }

    TableView getPassedTable() {
        return passedTable;
    }

    TableColumn getApproachingAssignment() {
        return approachingAssignment;
    }
    
        TableColumn getApproachingDeadline() {
        return approachingDeadline;
    }

    TableColumn getPassedDeadline() {
        return passedDeadline;
    }

    AnchorPane getDashboardAnchor() {
        return dashboardAnchor;
    }

    TableColumn getPassedAssignment() {
        return passedAssignment;
    }
    
}
    
    