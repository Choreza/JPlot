package main.app;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.BarPlot;

public class JPlot extends Application {
  
  @Override public void start(Stage stage) {
    stage.setTitle("JPlot");

    BorderPane pane = new BorderPane();

    HBox hbox = new HBox(10);
    hbox.setAlignment(Pos.CENTER_LEFT);

    FileChooser dataChooser = new FileChooser();
    Button openFileButton = new Button("Choose Data File");

    openFileButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        File file = dataChooser.showOpenDialog(stage);
      }
    });

    Button drawButton = new Button("Draw");

    drawButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        pane.setCenter(buildPlot());
      }
    });

    ObservableList<String> options =
        FXCollections.observableArrayList(
            "Line Plot", "Scatter Plot", "Bar Plot"
            );
    ComboBox<String> comboBox = new ComboBox<>(options);
    comboBox.setValue("Select Plot");

    hbox.getChildren().addAll(new Label("Plot Type:"), comboBox, openFileButton, drawButton);
    pane.setTop(hbox);

    Scene scene  = new Scene(pane,800,600);

    stage.setScene(scene);
    stage.show();
  }

  protected Node buildPlot() {
    BarPlot<String, Number> lineplot = new BarPlot<>(new CategoryAxis(), new NumberAxis());

    List<String> x = Arrays.asList(new String[]{"1", "2", "3", 
        "4", "5", "6", "8", "7", "9", "10", "11", "12"});
    List<Number> y = Arrays.asList(new Number[]{23, 14, 15, 24, 34, 36, 22, 45, 42, 17, 29, 25});

    lineplot.setTitle("Line Plot Sample");
    lineplot.setXLabel("Number of month");
    lineplot.setYLabel("Millions of US$");
    lineplot.addSeries(x, y, "Portfolio #1");
    return lineplot.getPlot();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
