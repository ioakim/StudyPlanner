package studyplanner;
/**
 * Created by aidan on 03/05/2017.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.beans.NamedArg;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GanttChartView<X,Y> extends XYChart<X,Y> {

    public static class ChartRectangle {


        public double rectangleLength;
        public String style;

        /**
         * 
         * @param lengthMs
         * @param styleClass 
         */
        public ChartRectangle(double lengthMs, String styleClass) {

            super();
            this.rectangleLength = lengthMs;
            this.style = styleClass;
        }

        /**
         * 
         * @return 
         */
        public double getLength() {

            return rectangleLength;
        }
        
        /**
         * 
         * @param length 
         */
        public void setLength(long length) {
            this.rectangleLength = length;
        }
        
        /**
         * 
         * @return 
         */
        public String getStyleClass() {
            return style;
        }
        
        /**
         * 
         * @param styleClass 
         */
        public void setStyleClass(String styleClass) {
            this.style = styleClass;
        }


    }

    private double rectangleHeight = 10;

    /**
	 * 
	 * @param xAxis
	 * @param yAxis
	 */
    public GanttChartView(@NamedArg("xAxis") Axis<X> xAxis, @NamedArg("yAxis") Axis<Y> yAxis) {
        this(xAxis, yAxis, FXCollections.<Series<X, Y>>observableArrayList());
    }

    /**
	 * 
	 * @param xAxis
	 * @param yAxis
	 * @param data
	 */
    public GanttChartView(@NamedArg("xAxis") Axis<X> xAxis, @NamedArg("yAxis") Axis<Y> yAxis, @NamedArg("data") ObservableList<Series<X,Y>> data) {
        super(xAxis, yAxis);
        if (!(xAxis instanceof DateAxis && yAxis instanceof CategoryAxis)) {
            throw new IllegalArgumentException("Axis type incorrect, X should be DateAxis, Y should be CategoryAxis");
        }
        setData(data);
    }
    
    /**
     * 
     * @param series
     * @param seriesIndex
     * @param item
     * @param itemIndex
     * @return 
     */
    private Node initNode(Series<X, Y> series, int seriesIndex, final Data<X,Y> item, int itemIndex) {

        Node container = item.getNode();

        if (container == null) {
            container = new StackPane();
            item.setNode(container);
        }

        container.getStyleClass().add(getStyle( item.getExtraValue()));

        return container;
    }

    /**
     * 
     * @param obj
     * @return 
     */
    private static String getStyle( Object obj) {
        return ((ChartRectangle) obj).getStyleClass();
    }

    /**
     * 
     * @param obj
     * @return 
     */
    private static double getRectangleHeight( Object obj) {
        return ((ChartRectangle) obj).getLength();
    }
    
    /**
     * 
     * @return 
     */
    public double getRectangleHeight() {
        return rectangleHeight;
    }

    /**
     * 
     * @param blockHeight 
     */
    public void setRectangleHeight( double blockHeight) {
        this.rectangleHeight = blockHeight;
    }

    @Override protected void layoutPlotChildren() {

      for (int seriesIndex=0; seriesIndex < getData().size(); seriesIndex++) {

            Series<X,Y> series = getData().get(seriesIndex);

            Iterator<Data<X,Y>> iter = getDisplayedDataIterator(series);
            while(iter.hasNext()) {
                Data<X,Y> item = iter.next();
                double x = getXAxis().getDisplayPosition(item.getXValue());
                double y = getYAxis().getDisplayPosition(item.getYValue());
                if (Double.isNaN(x) || Double.isNaN(y)) {
                    continue;
                }
                Node block = item.getNode();
                Rectangle ellipse;
                if (block != null) {
                    if (block instanceof StackPane) {
                        StackPane region = (StackPane)item.getNode();
                        if (region.getShape() == null) {
                            ellipse = new Rectangle( getRectangleHeight( item.getExtraValue()), getRectangleHeight());
                        } else if (region.getShape() instanceof Rectangle) {
                            ellipse = (Rectangle)region.getShape();
                        } else {
                            return;
                        }
                        ellipse.setWidth(getRectangleHeight( item.getExtraValue()) * ((getXAxis() instanceof NumberAxis) ? Math.abs(((NumberAxis)getXAxis()).getScale()) : 1));
                        ellipse.setHeight(getRectangleHeight() * ((getYAxis() instanceof NumberAxis) ? Math.abs(((NumberAxis)getYAxis()).getScale()) : 1));
                        y -= getRectangleHeight() / 2.0;

                        region.setShape(null);
                        region.setShape(ellipse);
                        region.setScaleShape(false);
                        region.setCenterShape(false);
                        region.setCacheShape(false);

                        block.setLayoutX(x);
                        block.setLayoutY(y);
                    }
                }
            }
        }
    }

    @Override protected void dataItemAdded(Series<X,Y> series, int itemIndex, Data<X,Y> item) {
        Node block = initNode(series, getData().indexOf(series), item, itemIndex);
        getPlotChildren().add(block);
    }

    @Override protected  void dataItemRemoved(final Data<X,Y> item, final Series<X,Y> series) {
        final Node block = item.getNode();
            getPlotChildren().remove(block);
            removeDataItemFromDisplay(series, item);
    }

    @Override protected void dataItemChanged(Data<X, Y> item) {
    }

    @Override protected  void seriesAdded(Series<X,Y> series, int seriesIndex) {
        for (int j=0; j<series.getData().size(); j++) {
            Data<X,Y> item = series.getData().get(j);
            Node container = initNode(series, seriesIndex, item, j);
            getPlotChildren().add(container);
        }
    }

    @Override protected  void seriesRemoved(final Series<X,Y> series) {
        for (XYChart.Data<X,Y> d : series.getData()) {
            final Node container = d.getNode();
            getPlotChildren().remove(container);
        }
        removeSeriesFromDisplay(series);

    }

    @Override protected void updateAxisRange() {
        final Axis<X> xa = getXAxis();
        final Axis<Y> ya = getYAxis();
        List<X> xData = null;
        List<Y> yData = null;
        if(xa.isAutoRanging()) xData = new ArrayList<X>();
        if(ya.isAutoRanging()) yData = new ArrayList<Y>();
        if(xData != null || yData != null) {
            for(Series<X,Y> series : getData()) {
                for(Data<X,Y> data: series.getData()) {
                    if(xData != null) {
                        xData.add(data.getXValue());
                        xData.add(xa.toRealValue(xa.toNumericValue(data.getXValue()) + getRectangleHeight(data.getExtraValue())));
                    }
                    if(yData != null){
                        yData.add(data.getYValue());
                    }
                }
            }
            if(xData != null) xa.invalidateRange(xData);
            if(yData != null) ya.invalidateRange(yData);
        }
    }

}