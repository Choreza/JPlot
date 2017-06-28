package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class TdataParser extends FileParser {
  private ArrayList<ArrayList<Number>> horData;
  private ArrayList<ArrayList<Number>> verData;
  
  
  /**
   * Builder of TdataParser, initializes the arrays of data. Set the internal file of the
   * parser and the command and error logs lists.
   */
  public TdataParser() {
    super();
    this.horData = new ArrayList<ArrayList<Number>>();
    this.verData = new ArrayList<ArrayList<Number>>();
  }
  
  /**
   * Builder of TdataParser, initializes the arrays of data. Set the internal file of the
   * parser and the command and error logs lists.
   * @param file    Contains the data to be parsed.
   */
  public TdataParser(File file) {
    super(file);
    this.horData = new ArrayList<ArrayList<Number>>();
    this.verData = new ArrayList<ArrayList<Number>>();
  }
  
  @Override
  public ArrayList<ArrayList<Number>> getHorizontalData() {
    return horData;
  }
  
  @Override
  public ArrayList<ArrayList<Number>> getVerticalData() {
    return verData;
  }
  
  
  /**
   * Parse the selected file, creating commands and horizontal/vertical data ArrayLists.
   * 
   */
  public void parse() {
    super.parse();
    clear();
    FileReader in = null;
    try {
      in = new FileReader(file);
    } catch (FileNotFoundException e) {
      errorLogs.add("File not found, nothing parsed.");
      return;
    }
    if (!checkFileType("tdata")) {
      errorLogs.add("Just 'tdata' files supported.");
      try {
        in.close();
      } catch (IOException e) {
        return;
      }
      return;
    }
    BufferedReader bf = null;
    try {
      bf = new BufferedReader(in);
      String line = bf.readLine().trim();
      String[] toParseInfo = line.split(";\\s*");
      
      for (int i = 0; i < 3; ++i) {
        commands.add(toParseInfo[i].trim());
      }
      
      for (int i = 3; i < toParseInfo.length; ++i) {
        series.add(toParseInfo[i].trim());
        horData.add(new ArrayList<Number>());
        verData.add(new ArrayList<Number>());
      }
      
      line = bf.readLine().trim();
      while (line != null) {
        line.trim();
        String[] tuple = line.split(",\\s*");
        for (String word : tuple) {
          word = word.trim();
        }
        
        if (tuple.length != 3) {
          line = bf.readLine();
          continue;
        }
        
        try {
          Number serie = NumberFormat.getInstance().parse(tuple[2]);
          Number horValue = NumberFormat.getInstance().parse(tuple[0]);
          Number verValue = NumberFormat.getInstance().parse(tuple[1]);
          
          serie.hashCode();
          Integer realSerie = Integer.parseInt(tuple[2]);
          if (realSerie >= 0 && realSerie < series.size()) {
            horData.get(realSerie).add(horValue);
            verData.get(realSerie).add(verValue);
          }
          
        } catch (ParseException e) {
          errorLogs.add("Error while parsing " + tuple[2]
              + " the values " + tuple[0] + " and " + tuple[1]);
        } catch (NumberFormatException e) {
          errorLogs.add("Can't parse " + line);
        }
        line = bf.readLine();
      }
      
    } catch (IOException e) {
      errorLogs.add("Error while reading: " + file.getName());
    } finally {
      try {
        if (bf != null) {
          bf.close();
        }
      } catch (IOException e) {
        errorLogs.add("Error while closing BufferedReader.");
      }
    }
  }
  
  @Override
  protected void clear() {
    super.clear();
    horData.clear();
    verData.clear();
  }
}
