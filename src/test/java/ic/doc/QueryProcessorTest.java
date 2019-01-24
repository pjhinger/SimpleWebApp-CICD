package ic.doc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueryProcessorTest {

  QueryProcessor queryProcessor = new QueryProcessor();

  @Test
  public void returnsEmptyStringIfCannotProcessQuery() throws Exception {
    assertThat(queryProcessor.process("test").get(0).getAnswer(), is(""));
  }

  @Test
  public void knowsAboutShakespeare() throws Exception {
    assertThat(queryProcessor.process("Shakespeare").get(0).getAnswer(), containsString(
            "playwright"));
  }

  @Test
  public void knowsAboutAsimov() throws Exception {
    assertThat(queryProcessor.process("Asimov").get(0).getAnswer(), containsString("science " +
            "fiction"));
  }

  @Test
  public void knowsAboutTuring() throws Exception {
    assertThat(queryProcessor.process("Turing").get(0).getAnswer(), containsString("logician"));
  }

  @Test
  public void knowsAboutLovelace() throws Exception {
    assertThat(queryProcessor.process("Lovelace").get(0).getAnswer(), containsString(
            "Countess of Lovelace"));
  }

  @Test
  public void knowsAboutPascal() throws Exception {
    assertThat(queryProcessor.process("Pascal").get(0).getAnswer(), containsString(
            "clarified the concepts of pressure and vacuum"));
  }

  @Test
  public void isNotCaseSensitive() throws Exception {
    assertThat(queryProcessor.process("shakespeare").get(0).getAnswer(), containsString(
            "playwright"));
  }

  @Test
  public void knowsAboutWilliam() throws Exception {
    assertThat(queryProcessor.process("william").get(0).getAnswer(), containsString(
            "playwright"));
  }

  @Test
  public void knowsAboutAda() throws Exception {
    assertThat(queryProcessor.process("Ada").get(0).getAnswer(), containsString(
            "Countess of Lovelace"));
  }


  @Test
  public void knowsAboutIsaacAsimov() throws Exception {
    assertThat(queryProcessor.process("Isaac Asimov").get(0).getAnswer(),
            containsString(
            "science " +
                    "fiction"));
  }

  @Test
  public void knowsAboutAlanTuring() throws Exception {
    assertThat(queryProcessor.process("Alan Turing").get(0).getAnswer(),
            containsString(
            "logician"));
  }

  @Test
  public void knowsAboutAdaLovelace() throws Exception {
    assertThat(queryProcessor.process("Ada Lovelace").get(0).getAnswer(),
            containsString(
            "Countess of Lovelace"));
  }

  @Test
  public void recognisesDisorderedName() throws Exception {
    assertThat(queryProcessor.process("Lovelace Ada Augusta").get(0).getAnswer(),
            containsString(
                    "Countess of Lovelace"));
  }

  @Test
  public void recognisesFalseName() throws Exception {
    assertThat(queryProcessor.process("William Pascal Lovelace").get(0).getAnswer(),
            is(""));
  }
}
