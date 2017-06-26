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
 * @param <T1>  Type of the parameter of the x axe.
 * @param <T2>  Type of the parameter of the y axe.
 */
public class LinePlot<T1, T2> {
  private Axis<T1> horAxis;
  private Axis<T2> verAxis;
  private LineChart<T1, T2> plot;

  /**
   * Builder of a LinePlot, receives two Axis which can be it's subclasses (NumberAxis or 
   * CategorverAxis).
   * @param horAxis   Horizontal axe of the LinePlot.
   * @param verAxis   Vertical axe of the LinePlot.
   */
  public LinePlot(Axis<T1> horAxis, Axis<T2> verAxis) {
    this.horAxis = horAxis;
    this.verAxis = verAxis;
    plot = new LineChart<T1, T2>(horAxis, verAxis);
  }
  
  /**
   * Receive two lists of data representing the horizontal and vertical coordinates and 
   * add it to the LinePlot as an independent series of points.
   * @param horData  Horizontal coordinates of the series of points.
   * @param verData  Vertical coordinates of the series of points. 
   *
   */
  public void addSeries(List<T1> horData, List<T2> verData) {
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
  public void addSeries(List<T1> horData, List<T2> verData, String seriesName) {
    if (horData.size() != verData.size()) {
      throw new IllegalArgumentException("X and Y data must have the same size");
    }
    XYChart.Series<T1, T2> series = new XYChart.Series<>();
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
  public LineChart<T1, T2> getPlot() {
    return plot;
  }
}
