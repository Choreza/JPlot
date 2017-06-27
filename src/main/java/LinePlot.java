package main.java;

import java.util.List;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * Adapter of LineChart JavaFX, it contains his own horizontal and vertical axes which 
 * can be set to a value dependent on the user input.
 * 
 * @author alejandro
 *
 * @param <Number>  Type of the parameter of the x axe.
 * @param <Number>  Type of the parameter of the y axe.
 */
public class LinePlot {
  private Axis<Number> horAxis;
  private Axis<Number> verAxis;
  private LineChart<Number, Number> plot;

  /**
   * Builder of a LinePlot, receives two Axis which can be it's subclasses (NumberAxis or 
   * CategorverAxis).
   * @param horAxis   Horizontal axe of the LinePlot.
   * @param verAxis   Vertical axe of the LinePlot.
   */
  public LinePlot(Axis<Number> horAxis, Axis<Number> verAxis) {
    this.horAxis = horAxis;
    this.verAxis = verAxis;
    plot = new LineChart<Number, Number>(horAxis, verAxis);
  }
  
  /**
   * Receive two lists of data representing the horizontal and vertical coordinates and 
   * add it to the LinePlot as an independent series of points.
   * @param horData  Horizontal coordinates of the series of points.
   * @param verData  Vertical coordinates of the series of points. 
   *
   */
  public void addSeries(List<Number> horData, List<Number> verData) {
    addSeries(horData, verData, "");
  }

  /**
   * Receive two lists of data representing the horizontal and vertical coordinates and add it
   * to the LinePlot as an independent series of points. Includes the name of the series as 
   * third parameter.
   * @param horData   Horizontal coordinates of the series of points.
   * @param verData   Vertical coordinates of the series of points.
   * @param seriesName  Name of the series of points.
   */
  public void addSeries(List<Number> horData, List<Number> verData, String seriesName) {
    if (horData.size() != verData.size()) {
      throw new IllegalArgumentException("X and Y data must have the same size");
    }
    XYChart.Series<Number, Number> series = new XYChart.Series<>();
    for (int i = 0; i < horData.size(); i++) {
      series.getData().add(new XYChart.Data<>(horData.get(i), verData.get(i)));
    }
    series.setName(seriesName);
    plot.getData().add(series);
  }
  
  /**
   * Setter of the title of the LinePlot.
   * @param title String representative of the plot's title.
   */
  public void setTitle(String title) {
    plot.setTitle(title);
  }

  /**
   * LinePlot's horizontal label setter.
   * @param label   String representative of the plot's horizontal label.
   */
  public void setXLabel(String label) {
    horAxis.setLabel(label);
  }
  
  /**
   * LinePlot's vertical label setter.
   * @param label   String representative of the plot's vertical label.
   */
  public void setYLabel(String label) {
    verAxis.setLabel(label);
  }

  /**
   * Once the LinePlot is ready use this method to get the LineChart created using LinePlot as 
   * adapter.
   * @return    LineChart chart.
   */  
  public LineChart<Number, Number> getPlot() {
    return plot;
  }
}
