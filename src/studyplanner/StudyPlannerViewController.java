package studyplanner;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import studyplanner.Model.StudyProfile;

/**
 *
 * @author Doggo
 */
public class StudyPlannerViewController implements Initializable {

    private StudyProfile profile; //profile data to be used by the controller

    @FXML
    private ListView<StudyProfile> profileListView;
    @FXML
    private AnchorPane content;
    @FXML
    private ContextMenu cmenu;

    private static int profileNumber = 0;

    @FXML
    private void loadProfileButtonAction() throws Exception {
        if (profile != null) {
            content.getScene().getWindow().hide();
            showStudyProfile();
        }
    }

    @FXML
    private void newProfileButtonAction() throws Exception {
        showCreateStudyProfile();
    }

    @FXML
    private void updateStudyProfile() throws Exception {
        final FileChooser fileChooser = new FileChooser();
        //configuring FileChoser so that it is more user friendly.
        configureFileChooser(fileChooser);

        //opening new fileChooser window on this stage
        File file = fileChooser.showOpenDialog((Stage) content.getScene().getWindow());
        if (file != null) {
            StudyProfile.updateStudyProfile(profile, file);
        }
    }

    @FXML
    private void saveStudyProfile() throws Exception {
        //File f;
        String filestring = "sp" + profile.getName() + ".ser";

        FileOutputStream fos = new FileOutputStream(filestring);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(profile);
    }

    public void addProfileToListView(final StudyProfile sp) {
        Platform.runLater(() -> {
            profileListView.getItems().add(sp);
        });
    }

    public void showCreateStudyProfile() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "CreateStudyProfileView.fxml"
                )
        );

        Stage stage = new Stage();
        //hides stage if main window is closed
        content.getScene().getWindow().setOnHidden(e -> stage.hide());
        stage.setTitle("Create New Profile");

        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );

        CreateStudyProfileViewController controller
                = loader.<CreateStudyProfileViewController>getController();
        controller.initData(this);
        stage.setResizable(false);
        stage.show();
        
    }

    public void showStudyProfile() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "StudyProfileView.fxml"
                )
        );

        Stage stage = new Stage();
        stage.setTitle(profile.getName());

        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );

        StudyProfileViewController controller
                = loader.<StudyProfileViewController>getController();
        controller.initData(profile);
        stage.setResizable(false);
        stage.show();
        
    }

    public void openDashboard() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardView.fxml"));

        try {
            Stage stage = new Stage();
            stage.setScene(new Scene((HBox) loader.load()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudyPlannerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setProfileListViewContextMenu() {

        cmenu = new ContextMenu();
        MenuItem i1 = new MenuItem("Delete");
        i1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StudyProfile temp;
                temp = profile;
                profileListView.getItems().remove(profile);

                File f = new File("sp" + temp.getName() + ".ser");
                f.delete();
                //try{

                //}
                //catch(Exception e){
                //    System.out.println(e);
                //}
            }
        });
        MenuItem i2 = new MenuItem("Save");
        i2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    saveStudyProfile();
                } catch (Exception ex) {
                    Logger.getLogger(StudyPlannerViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        MenuItem i3 = new MenuItem("Update Profile");
        i3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateStudyProfile();
                } catch (Exception ex) {
                    Logger.getLogger(StudyPlannerViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        cmenu.getItems().addAll(i1, i2, i3);
        profileListView.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {

                cmenu.show(profileListView, event.getScreenX(), event.getScreenY());
            }
        });
    }

    /**
     * Defines name and allowed extensions for fileChooser
     *
     * @param fileChooser - fileChooser to be configured
     */
    public void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Select Hub File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML", "*.xml"),
                new FileChooser.ExtensionFilter("HUB", "*.hub")
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ChangeListener listener = (ChangeListener<StudyProfile>) (ObservableValue<? extends StudyProfile> observable,
                StudyProfile oldValue, StudyProfile newValue) -> {
            profile = newValue;
        };

        FileInputStream fin = null;
        ObjectInputStream ois = null;
        //StudyProfile test = new StudyProfile();

        int num = 0;

        try {
            File dir = new File(System.getProperty("user.dir"));
            File[] files = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".ser");
                }
            });
            if (files.length != 0) {
                for (File f : files) {
                    fin = new FileInputStream(f.getName());
                    ois = new ObjectInputStream(fin);

                    addProfileToListView((StudyProfile) ois.readObject());
                }
            }

        } catch (Exception e) {
            System.out.println("file does not exist");
        }

        profileListView.getSelectionModel().selectedItemProperty().addListener(listener);
        setProfileListViewContextMenu();
    }
}
