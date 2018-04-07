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
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import studyplanner.Model.StudyProfile;
import studyplanner.Model.Task;
import studyplanner.Model.Module;
import studyplanner.Model.Assignment;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import studyplanner.Model.Criterion;
import studyplanner.Model.CriterionType;
import studyplanner.Model.Milestone;

/**
 * FXML controller for a study profile view
 *
 * @author Michail Krugliakov, Kiril Chomaniuk
 */
public class StudyProfileViewController implements Initializable {

    private ContextMenu cmenu;
    @FXML private Label profileNameLabel;

    @FXML private ListView<Task> taskListView;
    @FXML private ListView<Assignment> assignmentListView;
    @FXML private ListView<Milestone> milestoneListView;
    
    
    @FXML private ListView<Module> moduleListView;
            
    @FXML private ListView<Criterion> criteriaListView;
    @FXML private TableView approachingTable;
    @FXML private TableView passedTable;
    @FXML private TableColumn approachingAssignment;
    @FXML private TableColumn approachingDeadline;
    @FXML private TableColumn passedAssignment;
    @FXML private TableColumn passedDeadline;
    @FXML private ProgressBar taskBar;
    @FXML private ProgressBar assignmentBar;
    @FXML private ProgressBar milestoneBar;
    @FXML private Label criterionProgress;
    @FXML private Label selectedTaskName;
    @FXML private Label selectedCriterionName;
    @FXML private Label selectedAssignmentName;
    @FXML private Label selectedMilestoneName;
    @FXML private StudyProfile profile;   
    
    @FXML private Module selectedModule;   
    @FXML private Assignment selectedAssignment;   
    @FXML private Task selectedTask;    
    @FXML private Milestone selectedMilestone;
    @FXML private Criterion selectedCriterion;
    
    private static int profileNumber = 0;
    
    @FXML private void generateGanttChart() throws Exception, Exception{

        Stage stage = new Stage();
        stage.setTitle("Gantt Chart");
        
        GanttChartController gch = new GanttChartController();
        
        gch.firstInit(selectedAssignment, stage);
        gch.setPrevious(selectedAssignment, stage);
        gch.setNext(selectedAssignment, stage);
        Scene scene = gch.getScene();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    @FXML private void addTaskButtonClick() throws IOException, IOException {
        showAddTask();
    }
    @FXML private void addMilestoneButtonClick() throws IOException, IOException {
        showAddMilestone();
    }
    
    @FXML private void addActivityButtonClick() throws IOException, IOException {
        showAddActivity();
    }

    @FXML private void dashboardButtonClick() throws IOException, IOException {
        showDashboard();
    }
    
    @FXML private void saveStudyProfile() throws Exception, Exception {
        File f;
        String filestring;

        while(true){
            
            filestring = "sp" + ++profileNumber + ".ser";
            f = new File(filestring);
            if(!f.exists()) break;
        }
                FileOutputStream fos = new FileOutputStream(filestring);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(profile);
    }
    
    public void taskAdded(Task task) {
        Platform.runLater(() -> {
            this.taskListView.getItems().add(task);
        });
    }
    
    public void milestoneAdded(Milestone milestone){
        Platform.runLater(() -> {
            this.milestoneListView.getItems().add(milestone);
        });
    }

    public void criterionAdded(Criterion criterion) {
        Platform.runLater(() -> {
            this.criteriaListView.getItems().add(criterion);
        });
    }
    
    public void assignmentAdded(Assignment as) {
        Platform.runLater(() -> {
            this.assignmentListView.getItems().add(as);
        });
    }
    
        
        
    private void showCriteriaProgress(Criterion selectedCriterion) {
        String text = "";
        if(selectedCriterion == null){
            selectedCriterionName.setText("Criterion Progress");
            criterionProgress.setText("");
        }
        else{
            if(selectedCriterion.getType().equals(CriterionType.Boolean))
            {
                if(selectedCriterion.isMet()) text = " is done";
                else text = " is not done";
            }
            else{
                double progress = selectedCriterion.getValue();                    
                text = Double.toString(progress) + " left";
            }

                selectedCriterionName.setText(selectedCriterion.getName());
            criterionProgress.setText(text);
        }

    }    
    
    private void contextMenuFunction(ListView<?> lv, Object object){
        cmenu = new ContextMenu();
        MenuItem i1 = new MenuItem("Delete");
        i1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                lv.getItems().remove(object);
                if(object instanceof Task){
                    ArrayList<Task> tasks = selectedAssignment.getTasks();
                    tasks.remove((Task) object);
                }
                if(object instanceof Criterion){
                    ArrayList<Criterion> criteria = selectedTask.getCriteria();
                    criteria.remove((Criterion) object);
                }
            }
        });
        MenuItem i2 = new MenuItem("Show");
        i2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                //lv.getItems().remove(object);
                if(object instanceof Task){
                    try {
                        selectedTask = (Task) object;
                        showTask();
                    } catch (Exception ex) {
                        Logger.getLogger(StudyProfileViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(object instanceof Milestone){
                    try {
                        selectedMilestone = (Milestone) object;
                        showMilestone();
                    } catch (Exception ex) {
                        Logger.getLogger(StudyProfileViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        cmenu.getItems().addAll(i1, i2);
        lv.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
 
            @Override
            public void handle(ContextMenuEvent event) {
 
                cmenu.show(lv, event.getScreenX(), event.getScreenY());
            }
        });
    }
        
    private void showAddTask() throws IOException, IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "CreateTaskView.fxml"
                )
        );

        Stage stage = new Stage();
        stage.setTitle("New Task");
        //stage.setOnHidden(e -> updateTaskListView());

        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        updateStage(stage);

        CreateTaskViewController controller
                = loader.<CreateTaskViewController>getController();
        controller.initData(profile,selectedModule,selectedAssignment, this);
        stage.setResizable(false);
        stage.show();
    }
    
    private void showTask() throws Exception, Exception{
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
                "TaskView.fxml"
                )
        );
    
        Stage stage = new Stage();
        stage.setTitle(selectedTask.getName());
        
        stage.setScene(
            new Scene(
                (Pane) loader.load()
            )
        );
        updateStage(stage);
        
        TaskViewController controller = 
                loader.<TaskViewController>getController();
        controller.initData(profile, selectedTask);
        stage.setResizable(false);
        stage.show();    
    }
    
    private void showAddMilestone() throws IOException, IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "CreateMilestoneView.fxml"
                )
        );

        Stage stage = new Stage();
        stage.setTitle("New Milestone");

        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        updateStage(stage);

        CreateMilestoneViewController controller
                = loader.<CreateMilestoneViewController>getController();
        controller.initData(profile,selectedModule,selectedAssignment, this);
        stage.setResizable(false);
        stage.show();
    }
    
    private void showMilestone() throws Exception, Exception{
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
                "MilestoneView.fxml"
                )
        );
    
        Stage stage = new Stage();
        stage.setTitle(selectedMilestone.getName());
        
        stage.setScene(
            new Scene(
                (Pane) loader.load()
            )
        );
        updateStage(stage);
        
        MilestoneViewController controller = 
                loader.<MilestoneViewController>getController();
        controller.initData(profile, selectedMilestone);
        stage.setResizable(false);
        stage.show();    
    }
    
    private void showAddActivity() throws IOException, IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "CreateActivityView.fxml"
                )
        );

        Stage stage = new Stage();
        stage.setTitle("New Activity");
        //stage.setOnHidden(e -> updateTaskListView());

        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        updateStage(stage);
        
        CreateActivityViewController controller
                = loader.<CreateActivityViewController>getController();
        controller.initData(profile,selectedModule,selectedAssignment,
                            selectedTask, this);
        stage.setResizable(false);
        stage.show();
        
    }
    
    private void showDashboard() throws IOException, IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "DashboardView.fxml"
                )
        );

        Stage stage = new Stage();
        stage.setTitle("Dashboard");
        //stage.setOnHidden(e -> updateTaskListView());

        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );

        DashboardViewController controller
                = loader.<DashboardViewController>getController();
        controller.initData(profile, this);
        stage.setResizable(false);
        stage.show();
    }  
    
    private void updateStage(Stage stage) {
        stage.setOnHidden(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                updateTaskList(selectedAssignment);
                updateCriteriaList(selectedTask);
                updateMilestoneList(profile);
                updateDeadlines(profile);
                showCriteriaProgress(selectedCriterion);                   
            }
        });
    }
    
    private void updateMilestoneList(StudyProfile profile){
        if(milestoneListView != null){
            milestoneListView.getItems().clear();
            profile.getModules().forEach((Module m) -> {
                m.getAssignments().forEach((Assignment a) -> {
                    for(Milestone mileS : a.getMilestones()){
                        mileS.update();
                        this.milestoneAdded(mileS);
                    }
                });
            });
        }
    }
    private void updateDeadlines(StudyProfile profile) {
        boolean allDone = true;
        
        approachingTable.getItems().clear();
        passedTable.getItems().clear();
        for(Module m : profile.getModules()){
            for(Assignment a : m.getAssignments()){
                
                Date current = new Date();
                
                for(Task t : a.getTasks()){
                    if(!t.isDone()) allDone = false;
                }
                
                
                    
                if(!allDone && current.getTime() < a.getEnd().getTime())
                    approachingTable.getItems().add(a);
                else
                   passedTable.getItems().add(a); 
                
            }
        }
    }
    
    private void updateTaskList(Assignment cur){
        if(cur == null){
                    selectedAssignmentName.setText("Assignment Progress");
                    assignmentBar.setProgress(0.0);
        }
        else{
             int required = cur.getTasks().size();
        int finished = 0;
        if ( !taskListView.getItems().isEmpty()) {
                    taskListView.getItems().clear();
                    criteriaListView.getItems().clear();
                    selectedTask=null;
                    selectedCriterion = null;
                }
                for (Task t : cur.getTasks()) {
                    taskAdded(t);
                    if(t.isDone()) finished++;
                }
                
                        double progress = (double)finished/required;
        
                        selectedAssignmentName.setText(cur.getName());
                        assignmentBar.setProgress(progress);
        }
       
     
    }
    private void updateCriteriaList(Task cur){
        if(cur == null){
                    selectedTaskName.setText("Task Progress");
                    taskBar.setProgress(0.0);
        }
        else{
            int required = cur.getCriteria().size();
            int finished = 0;
            if(!criteriaListView.getItems().isEmpty()){
                        criteriaListView.getItems().clear();
                        selectedCriterion=null;
                    }
                    for(Criterion c : cur.getCriteria()){
                        criterionAdded(c);
                        if(c.isMet()) finished++;
                    }

                            double progress = (double)finished/required;
            selectedTaskName.setText(cur.getName());
            taskBar.setProgress(progress);
        }
    }
    
    public void initData(StudyProfile profile) {
        this.profile = profile;

        PropertyValueFactory<Assignment,String> aName = new PropertyValueFactory<>("name");
        PropertyValueFactory<Assignment,Date> aDate = new PropertyValueFactory<>("end");
        
        approachingAssignment.setCellValueFactory(aName);
        approachingDeadline.setCellValueFactory(aDate);
        
        passedAssignment.setCellValueFactory(aName);
        passedDeadline.setCellValueFactory(aDate);
        
        

        updateDeadlines(profile);
        

        profileNameLabel.setText(profile.getName());
        moduleListView.getItems().addAll(profile.getModules());
        for(Module m : profile.getModules()){
            for(Assignment a : m.getAssignments()){
                for(Milestone ms : a.getMilestones()){
                    milestoneListView.getItems().add(ms);
                }
            }
        }
        
        moduleListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Module>() {
            @Override
            public void changed(ObservableValue ov, Module prev, Module cur) {
                //resets value to zero so that user can't create task
                //with incompatible modules and assignments
                if (!assignmentListView.getItems().isEmpty()) {
                    assignmentListView.getItems().clear();
                    criteriaListView.getItems().clear();
                    taskListView.getItems().clear();
                    selectedAssignment=null;
                    selectedCriterion=null;
                }
                for (Assignment assign : cur.getAssignments()) {
                    assignmentAdded(assign);
                }
                //contextMenuDelete(moduleListView, cur);
                selectedModule = cur;
            }
        });
        
        assignmentListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Assignment>() {
            @Override
            public void changed(ObservableValue ov, Assignment prev, Assignment cur) {
                //resets value to zero so that user can't create task
                //with incompatible modules and assignments
                updateTaskList(cur);
                
                //contextMenuDelete(assignmentListView, cur);
                selectedAssignment = cur;
                
            }
        });
        taskListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>(){
            @Override
            public void changed(ObservableValue ov, Task prev, Task cur){
                updateCriteriaList(cur);
                contextMenuFunction(taskListView, cur);
                selectedTask = cur;
            }
        });
        criteriaListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Criterion>(){
            @Override
            public void changed(ObservableValue ov, Criterion prev, Criterion cur){
                showCriteriaProgress(cur);
                contextMenuFunction(criteriaListView, cur);
                selectedCriterion = cur;
            }
        });
        milestoneListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Milestone>(){
            @Override
            public void changed(ObservableValue ov, Milestone prev, Milestone cur){
                selectedMilestone = cur;
                //boolean allDone = true;
                int done = 0;
                for(Task t : cur.getTasks()){
                    if(t.isDone()) done++;
                }
                contextMenuFunction(milestoneListView, cur);
                selectedMilestoneName.setText(selectedMilestone.getName());
                milestoneBar.setProgress((double)done/cur.getTasks().size());
            }
        });

        milestoneListView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                   //Use ListView's getSelected Item
                   selectedMilestone = milestoneListView.getSelectionModel()
                                                            .getSelectedItem();
                    try {
                        showMilestone();
                    } catch (Exception ex) {
                        Logger.getLogger(StudyProfileViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }});
        taskListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click){

                if (click.getClickCount() == 2) {
                   //Use ListView's getSelected Item
                   selectedTask = taskListView.getSelectionModel()
                                                            .getSelectedItem();
                    try {
                        showTask();
                    } catch (Exception ex) {
                        Logger.getLogger(StudyProfileViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }});
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

