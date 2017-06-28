package test.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.BarPlot;
import main.java.Plot;

import org.junit.BeforeClass;
import org.junit.Test;

public class PlotTest {
  private static Plot plot;
  /**
   * Prepare threads to launch application.
   */
  
  @BeforeClass
  public static void initJavaFx() {
    Thread t = new Thread("JavaFX Init Thread") {
      public void run() {
        Application.launch(MyApp.class);
      }
    };
    t.setDaemon(true);
    t.start();
  }

  public static class MyApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
      BorderPane pane = new BorderPane();
      plot = new BarPlot(new CategoryAxis(), 
          new NumberAxis());
      pane.setCenter(plot.getPlot());
      Scene scene = new Scene(pane, 800, 600);
      primaryStage.setTitle("Calculator");
      primaryStage.setScene(scene);
      primaryStage.show();
    }
  }
  
  @Test
  public void testPlot() {
    plot.setTitle("hola");
  }
}
