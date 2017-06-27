package main.java;

import java.util.HashMap;

public class ParserFactory {
  private HashMap<String, FileParser> hashMap = 
      new HashMap<String, FileParser>();
  
  /**
   * Implements the flyweight design pattern to return or create a parser
   * depending of the query. If the plotType is a line plot or a scatter 
   * plot it will return a TdataParser, else if it is a bar plot will
   * return a CdataParser.
   * @param plotType    Type of plot which will use the parser.
   * @return            The parser corresponding to the plot.
   */
  public FileParser getParser(String plotType) {
    if (hashMap.containsKey(plotType)) {
      return hashMap.get(plotType);
    }
    
    if (plotType.equals("Line Plot") || plotType.equals("Scatter Plot")) {
      hashMap.put(plotType, new TdataParser());
      return hashMap.get(plotType);
    }
    
    hashMap.put(plotType, new CdataParser());
    return hashMap.get(plotType);
  }
}
