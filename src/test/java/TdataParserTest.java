package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import main.java.TdataParser;

import org.junit.Before;
import org.junit.Test;


public class TdataParserTest {
  private File file;
  private File notFoundFile;
  private File wrongFile;
  private File wrongFileExtension;
  private TdataParser tdp;
  
  /**
   * Setup things
   * @throws Exception  Some exception.
   */
  @Before
  public void setUp() throws Exception {
    file = new File(System.getProperty("user.dir") + "/src/test/data/xy.tdata");
    notFoundFile = new File(System.getProperty("user.dir") + "/src/test/data/notfoundxy.tdata");
    wrongFile = new File(System.getProperty("user.dir") + "/src/test/data/wrongxy.tdata");
    wrongFileExtension = new File(System.getProperty("user.dir")
        + "/src/test/data/countries.cdata");
    tdp = new TdataParser(file);
  }

  @Test
  public void successfulParseTest() {
    tdp.parse();
    assertTrue(tdp.getErrorLogs().isEmpty());
  }
  
  @Test
  public void fileNotFoundParseTest() {
    tdp.setFile(notFoundFile);
    tdp.parse();
    assertFalse(tdp.getErrorLogs().isEmpty());
    assertEquals(tdp.getErrorLogs().get(0), "File not found, nothing parsed.");
  }
  
  @Test
  public void fileWrongTupleParseTest() {
    tdp.setFile(wrongFile);
    tdp.parse();
    assertFalse(tdp.getErrorLogs().isEmpty());
    assertEquals(tdp.getErrorLogs().size(), 7);
  }
  
  @Test 
  public void gettersTest() {
    tdp.parse();
    assertTrue(tdp.getErrorLogs().isEmpty());
    assertEquals(tdp.getHorizontalData().size(), tdp.getVerticalData().size());
    
    tdp.setFile(notFoundFile);
    tdp.parse();
    assertFalse(tdp.getErrorLogs().isEmpty());
    assertEquals(tdp.getHorizontalData().size(), tdp.getVerticalData().size());
    
    tdp.setFile(wrongFile);
    tdp.parse();
    assertFalse(tdp.getErrorLogs().isEmpty());
    assertEquals(tdp.getHorizontalData().size(), tdp.getVerticalData().size());
    
    for (int i = 0; i < tdp.getSeries().size(); ++i) {
      assertEquals(tdp.getHorizontalData().get(i).size(), 
          tdp.getVerticalData().get(i).size());
    }
  }
  
  @Test
  public void wrongExtensionTest() {
    tdp.setFile(wrongFileExtension);
    tdp.parse();
    assertEquals(tdp.getErrorLogs().get(0), "Just 'tdata' files supported.");
  }
}
