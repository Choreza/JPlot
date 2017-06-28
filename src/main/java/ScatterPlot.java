package main.java;

import javafx.scene.chart.Axis;
import javafx.scene.chart.ScatterChart;

/**
 * Adapter of ScatterChart JavaFX, it contains his own horizontal and vertical axes which 
 * can be set to a value dependent on the user input.
 * 
 * @author alejandro
 *
 * @param <Number>  Type of the parameter of the x axe.
 * @param <Number>  Type of the parameter of the y axe.
 */
public class ScatterPlot extends Plot {

  /**
   * Builder of a ScatterPlot, receives two Axis which can be it's subclasses (NumberAxis or 
   * CategorverAxis).
   * @param horAxis   Horizontal axe of the ScatterPlot.
   * @param verAxis   Vertical axe of the ScatterPlot.
   */
  public ScatterPlot(Axis<Number> horAxis, Axis<Number> verAxis) {
    this.horAxis = horAxis;
    this.verAxis = verAxis;
    plot = new ScatterChart<Number, Number>(horAxis, verAxis);
  }
}
