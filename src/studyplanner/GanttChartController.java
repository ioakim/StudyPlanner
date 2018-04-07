/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studyplanner;

import java.time.LocalDate;
import studyplanner.Model.*;

import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import studyplanner.GanttChartView.ChartRectangle;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

import java.util.Date;
import java.util.*;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GanttChartController extends Application {

    Button prev = new Button("Previous Week");
    Button next = new Button("Next Week");
    
    ArrayList<String> colours = new ArrayList<>();
    Random rand = new Random();
    Scene scene;
    AnchorPane global;
    //Scene scene = initData(selectedAssignment, , );
    LocalDate today = LocalDate.now().atStartOfDay().toLocalDate();
    LocalDate afterAWeek = LocalDate.now().plusDays(5).atStartOfDay().toLocalDate();  
    
 
    public Scene getScene(){
        return this.scene;
    }
    
    
    private void doPrevious(Assignment selectedAssignment,Stage stage) {
        //temp = LocalDate.now().minusDays(6);
        today = today.minusDays(5).atStartOfDay().toLocalDate();
        afterAWeek = today.plusDays(5).atStartOfDay().toLocalDate();
        initData(selectedAssignment, java.sql.Date.valueOf(today), java.sql.Date.valueOf(afterAWeek));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    private void doNext(Assignment selectedAssignment,Stage stage) {
        //temp = LocalDate.now().minusDays(6);
        today = today.plusDays(5).atStartOfDay().toLocalDate();
        afterAWeek = today.plusDays(5).atStartOfDay().toLocalDate();
        initData(selectedAssignment, java.sql.Date.valueOf(today), java.sql.Date.valueOf(afterAWeek));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void setPrevious(Assignment selectedAssignment, Stage stage){
        prev.setOnAction(e->doPrevious(selectedAssignment,stage));
        prev.setLayoutX(25);
        prev.setLayoutY(575);
    }
    
    public void firstInit(Assignment selectedAssignment,Stage stage)
    {
        colours.add("status-red");
        colours.add("status-green");
        colours.add("status-blue");
        colours.add("status-yellow");
        colours.add("status-orange");
        initData(selectedAssignment, java.sql.Date.valueOf(today), java.sql.Date.valueOf(afterAWeek));
        
    }
    
    public void setNext(Assignment selectedAssignment,Stage stage){
        next.setOnAction(e->doNext(selectedAssignment,stage));
        next.setLayoutX(500);
        next.setLayoutY(575);
    }
    

    public void initData(Assignment a, Date firstDateToShow, Date lastDateToShow)
    {
        global = new AnchorPane();
        
        final DateAxis xAxis = new DateAxis(firstDateToShow,lastDateToShow);
        final CategoryAxis yAxis = new CategoryAxis();
        
        final GanttChartView<Date,String> chart = new GanttChartView<Date,String>(xAxis,yAxis);
        xAxis.setLabel("Date");
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        xAxis.setTickLabelGap(3);

        xAxis.setTickLabelRotation(90);
        xAxis.setTickLabelRotation(90);

        xAxis.setSide(Side.TOP);
        
        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);
        

        chart.setTitle(a.getName());
        chart.setLegendVisible(false);
        chart.setRectangleHeight( 25); 

        double x = 3.0;
        
        XYChart.Series deadline = new XYChart.Series();
        deadline.getData().add(new XYChart.Data(a.getEnd(), "Assignment deadline", new ChartRectangle(5.0,"status-black")));
        
        chart.getData().add(deadline);
        
        XYChart.Series msSeries = new XYChart.Series();
        for(Milestone ms : a.getMilestones()){
             msSeries.getData().add(new XYChart.Data(ms.getEnd(), "Milestones", new ChartRectangle(5.0,"status-black")));
        }
       
        
        chart.getData().add(msSeries);
        
        for (Task t : a.getTasks())
        {
            int num = rand.nextInt(colours.size());
            
            double time = (t.getEnd().getTime() - t.getStart().getTime())/3600000;
            XYChart.Series series = new XYChart.Series();
            series.getData().add(new XYChart.Data(t.getStart(), t.getName(), new ChartRectangle(time*x,colours.get(num))));
            chart.getData().add(series);
        }
        System.out.println(getClass().getResource("").toExternalForm());
        chart.getStylesheets().add(getClass().getResource("GanttChartView.css").toExternalForm());
        chart.setLayoutX(50);
        chart.setLayoutY(50);
        chart.setScaleX(1.2);
        chart.setScaleY(1.2);
        
        global.getChildren().addAll(chart,prev, next);
        scene = new Scene(global, 650, 650);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    
}