package main.java;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;

/**
 * Adapter of LineChart JavaFX, it contains his own horizontal and vertical axes which 
 * can be set to a value dependent on the user input.
 * 
 * @author alejandro
 *
 * @param <Number>  Type of the parameter of the x axe.
 * @param <Number>  Type of the parameter of the y axe.
 */
public class LinePlot extends Plot {

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
}
