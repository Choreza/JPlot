package main.java;

import java.util.List;

import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;

public class Plot {
  @SuppressWarnings("rawtypes")
  protected Axis horAxis;
  @SuppressWarnings("rawtypes")
  protected Axis verAxis;
  @SuppressWarnings("rawtypes")
  protected XYChart plot;
  
  /**
   * Receive two lists of data representing the horizontal and vertical coordinates and 
   * add it to the Plot as an independent series of points.
   * @param horData  Horizontal coordinates of the series of points.
   * @param verData  Vertical coordinates of the series of points. 
   *
   */
  @SuppressWarnings("rawtypes")
  public void addSeries(List horData, List verData) {
    addSeries(horData, verData, "");
  }

  /**
   * Receive two lists of data representing the horizontal and vertical coordinates and add it
   * to the Plot as an independent series of points. Includes the name of the series as 
   * third parameter.
   * @param horData   Horizontal coordinates of the series of points.
   * @param verData   Vertical coordinates of the series of points.
   * @param seriesName  Name of the series of points.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void addSeries(List horData, List verData, String seriesName) {
    if (horData.size() != verData.size()) {
      throw new IllegalArgumentException("X and Y data must have the same size");
    }
    XYChart.Series series = new XYChart.Series<>();
    for (int i = 0; i < horData.size(); i++) {
      series.getData().add(new XYChart.Data<>(horData.get(i), verData.get(i)));
    }
    series.setName(seriesName);
    plot.getData().add(series);
  }
  
  /**
   * Setter of the title of the Plot.
   * @param title String representative of the plot's title.
   */
  public void setTitle(String title) {
    plot.setTitle(title);
  }

  /**
   * Plot's horizontal label setter.
   * @param label   String representative of the plot's horizontal label.
   */
  public void setXLabel(String label) {
    horAxis.setLabel(label);
  }
  
  /**
   * Plot's vertical label setter.
   * @param label   String representative of the plot's vertical label.
   */
  public void setYLabel(String label) {
    verAxis.setLabel(label);
  }

  /**
   * Once the Plot is ready use this method to get the LineChart created using Plot as 
   * adapter.
   * @return    LineChart chart.
   */  
  
  @SuppressWarnings("rawtypes")
  public XYChart getPlot() {
    return plot;
  }
  
  /**
   * Remove the data from previous plots.
   */
  public void clear() {
    plot.getData().clear();
  }
}
