package main.java;

import java.util.List;

import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * Adapter of BarChart JavaFX, it contains his own horizontal and vertical axes which can be set to a 
 * value dependent on the user input.
 * 
 * @author alejandro
 *
 * @param <T1>	Type of the parameter of the x axe.
 * @param <T2>	Type of the parameter of the y axe.
 */
public class BarPlot<T1, T2> {
	private Axis<T1> xAxis;
	private Axis<T2> yAxis;
	private BarChart<T1, T2> plot;

	/**
	 * Builder of a BarPlot, receives two Axis which can be it's subclasses (NumberAxis or CategoryAxis).
	 * @param xAxis		Horizontal axe of the BarPlot.
	 * @param yAxis		Vertical axe of the BarPlot.
	 */
	public BarPlot(Axis<T1> xAxis, Axis<T2> yAxis){
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		plot = new BarChart<T1, T2>(xAxis, yAxis);
	}
	
	/**
	 * Receive two lists of data representing the horizontal and vertical coordinates and add it to the BarPlot
	 * as an independent series of points.
	 * @param xData		Horizontal coordinates of the series of points.
	 * @param yData		Vertical coordinates of the series of points.
	 */
	public void addSeries(List<T1> xData, List<T2> yData){
		addSeries(xData, yData, "");
	}

	/**
	 Receive two lists of data representing the horizontal and vertical coordinates and add it to the BarPlot
	 * as an independent series of points. Includes the name of the series as third parameter.
	 * @param xData		Horizontal coordinates of the series of points.
	 * @param yData		Vertical coordinates of the series of points.
	 * @param seriesName	Name of the series of points.
	 */
	public void addSeries(List<T1> xData, List<T2> yData, String seriesName){
		if(xData.size() != yData.size())
			throw new IllegalArgumentException("X and Y data must have the same size");
		XYChart.Series<T1, T2> series = new XYChart.Series<>();
		for(int i=0; i<xData.size(); i++){
			series.getData().add(new XYChart.Data<>(xData.get(i), yData.get(i)));
		}
		series.setName(seriesName);
		plot.getData().add(series);
	}
	
	/**
	 * Setter of the title of the BarPlot.
	 * @param title		String representative of the plot's title.
	 */
	public void setTitle(String title){
		plot.setTitle(title);
	}

	/**
	 * BarPlot's horizontal label setter.
	 * @param label		String representative of the plot's horizontal label.
	 */
	public void setXLabel(String label){
		xAxis.setLabel(label);
	}

	/**
	 * BarPlot's vertical label setter.
	 * @param label		String representative of the plot's vertical label.	
	 */
	public void setYLabel(String label){
		yAxis.setLabel(label);
	}

	/**
	 * Once the BarPlot is ready use this method to get the BarChart created using BarPlot as adapter.
	 * @return	BarChart chart.
	 */
	public BarChart<T1, T2> getPlot(){
		return plot;
	}
}
