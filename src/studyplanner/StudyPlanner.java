package studyplanner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import studyplanner.Model.StudyProfile;



public class StudyPlanner extends Application{
    
    
    /**
	 * 
	 * @param stage
	 */
	@Override
    public void start(Stage stage) throws Exception, Exception {
        
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
                "StudyPlannerView.fxml"
                )
        );
        
        stage.setScene(
            new Scene(
                (Pane) loader.load()
            )
        );
        stage.setTitle("Study Planner");
        
        StudyPlannerViewController controller = 
                loader.<StudyPlannerViewController>getController();
        
        stage.show();
    }

    public static void main(String[] args) {
        StudyProfile sp = new StudyProfile();


        //GanttChartView chart = new GanttChartView();
        //try{
            //writeObject(sp);
        //}
        //catch (IOException ex){
                
        //}
          launch(args);
          
    }
    /**
	 * 
	 * @param obj
	 */
	public static void writeObject(Object obj) throws IOException, IOException{
        FileOutputStream fos = new FileOutputStream("deprecated.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
    }
    


}
