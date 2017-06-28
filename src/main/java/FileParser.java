package main.java;

import java.io.File;
import java.util.ArrayList;

/**
 * Base class for parsers of different kind of files.
 * @author alejandro
 *
 */
public class FileParser {
  protected File file;
  protected ArrayList<String> series;
  protected ArrayList<String> commands;
  protected ArrayList<String> errorLogs;
  
  /**
   * Builder of the abstract parser, set the internal file of the parser and the command,
   * error logs and series lists.
  
   */
  public FileParser() {
    this.series = new ArrayList<String>();
    this.commands = new ArrayList<String>();
    this.errorLogs = new ArrayList<String>();
  }
  
  
  /**
   * Builder of the abstract parser, set the internal file of the parser and the command,
   * error logs and series lists.
   * @param file    Contains the data to parse.
   */
  public FileParser(File file) {
    this.file = file;
    this.series = new ArrayList<String>();
    this.commands = new ArrayList<String>();
    this.errorLogs = new ArrayList<String>();
  }
  
  /**
   * Parse data.
   */
  public void parse(){}
  
  
  /**
   * Set the file of the parser to a new file.
   * @param file  Contains the data to parse.
   */
  public void setFile(File file) {
    this.file = file;
  }
  
  /**
   * File contained by the parser.
   * @return  A file contained by the parser.
   */
  public File getFile() {
    return file;
  }
  
  /**
   * Get the list of commands (title, x label and y label).
   * @return  An array list containing the title, horizontal and vertical label to plot.
   */
  public ArrayList<String> getCommands() {
    return commands;
  }
  
  /**
   * Get the list of error logs
   * @return  An array list containing strings of errors produced while parsing.
   */
  public ArrayList<String> getErrorLogs() {
    return errorLogs;
  }
  
  /**
   *  Get the list of series to plot.
   * @return  An array list containing the strings of the series to plot. 
   */
  public ArrayList<String> getSeries() {
    return series;
  }
  
  protected boolean checkFileType(String type) {
    int i = file.getName().lastIndexOf('.');
    String extension = "";
    
    extension = file.getName().substring(i + 1);
    
    return extension.equals(type);
  }
  
  protected void clear() {
    series.clear();
    commands.clear();
    errorLogs.clear();
  }
  
  /**
   * Getter of the vertical data array.
   * @return  An array list containing array lists which contains information of the
   *          vertical coordinates of the data indexed in the same order than the
   *          series array list you can get using the method getSeries.
   */
  public ArrayList<ArrayList<Number>> getVerticalData() {
    return null;
  }
  
  /**
   * Getter of the horizontal data array.
   * @return  An array list containing array lists which contains information of the
   *          horizontal coordinates of the data indexed in the same order than the
   *          series array list you can get using the method getSeries.
   */
  public ArrayList<ArrayList<Number>> getHorizontalData() {
    return null;
  }
  
  public ArrayList<String> getHorizontalDataStr() {
    return null;
  }
 
}
