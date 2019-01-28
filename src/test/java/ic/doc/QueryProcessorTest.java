package ic.doc;

import ic.doc.web.DBReader;
import org.junit.Test;

import java.sql.ResultSet;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueryProcessorTest {

  DBReader dbReader = new DBReader();
  QueryProcessor queryProcessor = new QueryProcessor(dbReader);

  @Test
  public void returnsEmptyStringIfCannotProcessQuery() throws Exception {
    ResultSet queryRes = queryProcessor.process("test");
    int size = 0;
    while (queryRes.next()) {
      size ++;
    }
    assertThat(size, is(0));
  }

  @Test
  public void knowsAboutShakespeare() throws Exception {
    assertThat(queryProcessor.process("Shakespeare").getString("description"), containsString(
            "playright"));
  }

  @Test
  public void knowsAboutAsimov() throws Exception {
    assertThat(queryProcessor.process("Asimov").getString("description"), containsString("science " +
            "fiction"));
  }

  @Test
  public void knowsAboutTuring() throws Exception {
    assertThat(queryProcessor.process("Turing").getString("description"), containsString("logician"));
  }

  @Test
  public void knowsAboutLovelace() throws Exception {
    assertThat(queryProcessor.process("Lovelace").getString("description"), containsString(
            "Countess of Lovelace"));
  }

  @Test
  public void knowsAboutPascal() throws Exception {
    assertThat(queryProcessor.process("Pascal").getString("description"), containsString(
            "clarified the concepts of pressure and vacuum"));
  }

  @Test
  public void isNotCaseSensitive() throws Exception {
    assertThat(queryProcessor.process("shakespeare").getString("description"), containsString(
            "playwright"));
  }

  @Test
  public void knowsAboutWilliam() throws Exception {
    assertThat(queryProcessor.process("william").getString("description"), containsString(
            "playwright"));
  }

  @Test
  public void knowsAboutAda() throws Exception {
    assertThat(queryProcessor.process("Ada").getString("description"), containsString(
            "Countess of Lovelace"));
  }


  @Test
  public void knowsAboutIsaacAsimov() throws Exception {
    assertThat(queryProcessor.process("Isaac Asimov").getString("description"),
            containsString(
            "science " +
                    "fiction"));
  }

  @Test
  public void knowsAboutAlanTuring() throws Exception {
    assertThat(queryProcessor.process("Alan Turing").getString("description"),
            containsString(
            "logician"));
  }

  @Test
  public void knowsAboutAdaLovelace() throws Exception {
    assertThat(queryProcessor.process("Ada Lovelace").getString("description"),
            containsString(
            "Countess of Lovelace"));
  }

  @Test
  public void recognisesDisorderedName() throws Exception {
    assertThat(queryProcessor.process("Lovelace Ada Augusta").getString("description"),
            containsString(
                    "Countess of Lovelace"));
  }

  @Test
  public void recognisesFalseName() throws Exception {
    ResultSet queryRes = queryProcessor.process("William Pascal Lovelace");
    int size = 0;
    while (queryRes.next()) {
      size ++;
    }
    assertThat(size, is(0));
  }
}
