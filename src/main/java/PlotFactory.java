package main.java;

import java.util.HashMap;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

/**
 * Implements the flyweight design pattern to create a plot factory.
 * @author alejandro
 *
 */
public class PlotFactory {
  HashMap<String, Plot> hashMap;
  
  /**
   * Implements the flyweight design pattern to create a plot factory.
   */
  public PlotFactory() {
    hashMap = new HashMap<String, Plot>();
    hashMap.put("Line Plot", new LinePlot(new NumberAxis(), 
        new NumberAxis()));
    hashMap.put("Scatter Plot", new ScatterPlot(new NumberAxis(),
        new NumberAxis()));
    hashMap.put("Bar Plot", new BarPlot(new CategoryAxis(), 
        new NumberAxis()));
  }
  
  /**
   * Plot getter to get a plot.
   * @param type  String representing the type to plot.
   * @return  Return a Plot corresponding to the type given.
   */
  public Plot getPlot(String type) {
    if (hashMap.containsKey(type)) {
      return hashMap.get(type);
    }
    hashMap.put(type, new ScatterPlot(new NumberAxis(),
        new NumberAxis()));
    return hashMap.get(type);
  }
}
