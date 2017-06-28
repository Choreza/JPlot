package main.app;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.FileParser;
import main.java.ParserFactory;
import main.java.Plot;
import main.java.PlotFactory;

public class JPlot extends Application {
  private String action = null;
  private File file = null;
  private FileParser fp = null;
  private Plot plot = null;
  private ParserFactory parserFactory = new ParserFactory();
  private PlotFactory plotFactory = new PlotFactory();
  
  @Override 
  public void start(Stage stage) {
    
    stage.setTitle("JPlot");

    BorderPane pane = new BorderPane();

    HBox hbox = new HBox(10);
    hbox.setAlignment(Pos.CENTER_LEFT);

    FileChooser dataChooser = new FileChooser();
    Button openFileButton = new Button("Choose Data File");
    openFileButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        file = dataChooser.showOpenDialog(stage);
      }
    });

    
    Button drawButton = new Button("Draw");
    drawButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        fp = parserFactory.getParser(action);
        fp.setFile(file);
        fp.parse();
        pane.setCenter(buildPlot(fp));
      }
    });

    ObservableList<String> options =
        FXCollections.observableArrayList(
            "Line Plot", "Scatter Plot", "Bar Plot"
            );
    ComboBox<String> comboBox = new ComboBox<>(options);
    comboBox.setValue("Select Plot");
    comboBox.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        action = comboBox.getValue();
      }
    });
    
    
    hbox.getChildren().addAll(new Label("Plot Type:"), comboBox, openFileButton, drawButton);
    pane.setTop(hbox);
    Scene scene  = new Scene(pane,800,600);
    stage.setScene(scene);
    stage.show();
  }

  protected Node buildPlot(FileParser fp) {
    plot = plotFactory.getPlot(action);
    plot.clear();
    
    ArrayList<String> commands = fp.getCommands();
    
    plot.setTitle(commands.get(0));
    plot.setXLabel(commands.get(1));
    plot.setYLabel(commands.get(2));
    
    setData(plot, fp);
    return plot.getPlot();
  }

  public static void main(String[] args) {
    launch(args);
  }
  
  
  protected void setData(Plot plot, FileParser fp) {  
    for (int i = 0; i < fp.getSeries().size(); ++i) {
      String serie = fp.getSeries().get(i);
      if (action == "Bar Plot") {
        ArrayList<Number> vertical = fp.getVerticalData().get(i);
        plot.addSeries(fp.getHorizontalDataStr(), vertical, serie);
      } else {
        plot.addSeries(fp.getHorizontalData().get(i), 
            fp.getVerticalData().get(i), serie);
      }
    }
    
    
  }
}
