package test.java;

import static org.junit.Assert.assertNotEquals;

import java.io.File;

import main.java.FileParser;
import main.java.ParserFactory;

import org.junit.Before;
import org.junit.Test;



public class ParserFactoryTest {
  private File file;
  private ParserFactory parserFactory = new ParserFactory();
  
  /**
   * Setup test classes.
   * @throws Exception  Some exception.
   */
  @Before
  public void setUp() throws Exception {
    file = new File(System.getProperty("user.dir")
        + "/src/test/data/countries.cdata");
    parserFactory = new ParserFactory();
  }

  @Test
  public void barPlotGetTest() {
    FileParser bp = parserFactory.getParser("Bar Plot");
    bp.setFile(file);
    assertNotEquals(null, bp);
  }
  
  @Test
  public void scatterAndLinePlotGetTest() {
    FileParser bp = parserFactory.getParser("Scatter Plot");
    bp.setFile(file);
    assertNotEquals(null, bp);
    
    bp = parserFactory.getParser("Line Plot");
    bp.setFile(file);
    assertNotEquals(null, bp);
    parserFactory.getParser("Scatter Plot");
  }

}
