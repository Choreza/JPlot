package main.java;

import java.io.File;
import java.text.ParseException;

public class DummyClass {
  public static void main(String[] args) throws ParseException {
    File file = new File(System.getProperty("user.dir") + "/src/test/data/xy.tdata");
    ParserFactory parserFactory = new ParserFactory();
    
    FileParser fp = parserFactory.getParser("Line Plot");
    fp.setFile(file);
    fp.parse();
    System.out.println(fp.getHorizontalData().toString());
    System.out.println(fp.getVerticalData().toString());
    System.out.println(fp.getHorizontalStringData().toString());
  }
}