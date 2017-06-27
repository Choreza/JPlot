package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class CdataParser extends FileParser {
  private ArrayList<String> horData;
  private ArrayList<ArrayList<Number>> verData;
  
  /**
   * Builder of CdataParser, initializes the arrays of data. Set the internal file of the
   * parser and the command and error logs lists.
   */
  public CdataParser() {
    super();
    this.horData = new ArrayList<String>();
    this.verData = new ArrayList<ArrayList<Number>>();
  }
  
  
  /**
   * Builder of CdataParser, initializes the arrays of data. Set the internal file of the
   * parser and the command and error logs lists.
   * @param file    Contains the data to be parsed.
   */
  public CdataParser(File file) {
    super(file);
    this.horData = new ArrayList<String>();
    this.verData = new ArrayList<ArrayList<Number>>();
  }
  
  @Override
  public ArrayList<String> getHorizontalStringData() {
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
    FileReader in = null;
    try {
      in = new FileReader(file);
    } catch (FileNotFoundException e) {
      errorLogs.add("File not found, nothing parsed.");
      return;
    }
 
    if (!checkFileType("cdata")) {
      errorLogs.add("Just 'cdata' files supported.");
      try {
        in.close();
      } catch (IOException e) {
        return;
      }
      return;
    }
    clear();
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
      }
      
      line = bf.readLine().trim();
      while (line != null) {
        String[] tuple = line.split(",\\s*");
        
        ArrayList<Number> myNumbers = new ArrayList<Number>();
        for (int i = 1; i < tuple.length; ++i) {
          try {
            Number num = NumberFormat.getInstance().parse(tuple[i].trim());
            myNumbers.add(num);
          } catch (ParseException e) {
            errorLogs.add("Error while parsing " + tuple[0].trim()
                + " the value " + tuple[i].trim());
            break;
          }
        }
        
        if (myNumbers.size() == series.size()) {
          horData.add(tuple[0].trim());
          verData.add(myNumbers);
        }
        
        line = bf.readLine();
        if (line != null) {
          line.trim();
        }
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
