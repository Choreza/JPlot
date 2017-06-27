package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import main.java.CdataParser;

import org.junit.Before;
import org.junit.Test;

public class CdataParserTest {
  private File file;
  private File notFoundFile;
  private File wrongFile;
  private CdataParser cdp;
  private File wrongFileExtension;
  
  /**
   * Prepares the files and the CdataParser to test.
   * @throws Exception    Some exception.
   */
  @Before
  public void setUp() throws Exception {
    file = new File(System.getProperty("user.dir") + "/src/test/data/countries.cdata");
    notFoundFile = new File(System.getProperty("user.dir") + "/src/test/data/nocountries.cdata");
    wrongFile = new File(System.getProperty("user.dir") + "/src/test/data/wrongcountries.cdata");
    wrongFileExtension = new File(System.getProperty("user.dir") + "/src/test/data/xy.tdata");
    cdp = new CdataParser(file);
  }

  @Test
  public void successfulParseTest() {
    cdp.parse();
    assertTrue(cdp.getErrorLogs().isEmpty());
  }
  
  @Test
  public void fileNotFoundParseTest() {
    cdp.setFile(notFoundFile);
    cdp.parse();
    assertFalse(cdp.getErrorLogs().isEmpty());
    assertEquals(cdp.getErrorLogs().get(0), "File not found, nothing parsed.");
  }

  @Test
  public void fileWrongTupleParseTest() {
    cdp.setFile(wrongFile);
    cdp.parse();
    assertFalse(cdp.getErrorLogs().isEmpty());
    assertEquals(cdp.getErrorLogs().size(), 3);
  }
  
  @Test 
  public void gettersTest() {
    cdp.parse();
    assertTrue(cdp.getErrorLogs().isEmpty());
    assertEquals(cdp.getHorizontalData().size(), cdp.getVerticalData().size());
    
    cdp.setFile(notFoundFile);
    cdp.parse();
    assertFalse(cdp.getErrorLogs().isEmpty());
    assertEquals(cdp.getHorizontalData().size(), cdp.getVerticalData().size());
    
    cdp.setFile(wrongFile);
    cdp.parse();
    assertFalse(cdp.getErrorLogs().isEmpty());
    assertEquals(cdp.getHorizontalData().size(), cdp.getVerticalData().size());
  }
  
  @Test
  public void verticalDataVectorsTest() {
    cdp.parse();
    for (ArrayList<Number> arr : cdp.getVerticalData()) {
      assertEquals(cdp.getSeries().size(), arr.size());
    }
  }
  
  @Test
  public void fileTest() {
    assertEquals(file, cdp.getFile());
  }
  
  @Test
  public void commandsTest() {
    cdp.parse();
    assertEquals(cdp.getCommands().size(), 3);
  }
  
  @Test
  public void wrongExtensionTest() {
    cdp.setFile(wrongFileExtension);
    cdp.parse();
    assertEquals(cdp.getErrorLogs().get(0), "Just 'cdata' files supported.");
  }
}