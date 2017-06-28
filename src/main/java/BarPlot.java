package main.java;

import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;

/**
 * Adapter of BarChart JavaFX, it contains his own horizontal and vertical axes which can be set to 
 * a value dependent on the user input.
 * 
 * @author alejandro
 *
 * @param <String>  Type of the parameter of the x axe.
 * @param <Number>  Type of the parameter of the y axe.
 */
public class BarPlot extends Plot {

  /**
   * Builder of a BarPlot, receives two Axis which can be it's subclasses (NumberAxis or 
   * CategorverAxis).
   * @param horAxis   Horizontal axe of the BarPlot.
   * @param verAxis   Vertical axe of the BarPlot.
   */
  public BarPlot(Axis<String> horAxis, Axis<Number> verAxis) {
    this.horAxis = horAxis;
    this.verAxis = verAxis;
    plot = new BarChart<String, Number>(horAxis, verAxis);
  }
}
