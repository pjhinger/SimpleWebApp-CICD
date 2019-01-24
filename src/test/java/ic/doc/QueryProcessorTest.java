package ic.doc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueryProcessorTest {

  QueryProcessor queryProcessor = new QueryProcessor();

  @Test
  public void returnsEmptyStringIfCannotProcessQuery() throws Exception {
    assertThat(queryProcessor.process("test").getAnswer(), is(""));
  }

  @Test
  public void knowsAboutShakespeare() throws Exception {
    assertThat(queryProcessor.process("Shakespeare").getAnswer(), containsString(
            "playwright"));
  }

  @Test
  public void knowsAboutAsimov() throws Exception {
    assertThat(queryProcessor.process("Asimov").getAnswer(), containsString("science " +
            "fiction"));
  }

  @Test
  public void knowsAboutTuring() throws Exception {
    assertThat(queryProcessor.process("Turing").getAnswer(), containsString("logician"));
  }

  @Test
  public void knowsAboutLovelace() throws Exception {
    assertThat(queryProcessor.process("Lovelace").getAnswer(), containsString(
            "Countess of Lovelace"));
  }

  @Test
  public void knowsAboutPascal() throws Exception {
    assertThat(queryProcessor.process("Pascal").getAnswer(), containsString(
            "clarified the concepts " +
        "of pressure and vacuum"));
  }

  @Test
  public void isNotCaseSensitive() throws Exception {
    assertThat(queryProcessor.process("shakespeare").getAnswer(), containsString(
            "playwright"));
  }
}
