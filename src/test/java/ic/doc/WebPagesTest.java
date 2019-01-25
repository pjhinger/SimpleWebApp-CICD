package ic.doc;

import ic.doc.web.DownloadPage;
import ic.doc.web.HTMLResultPage;
import ic.doc.web.Page;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebPagesTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Before
  public void setupMocks() {
    context.setImposteriser();
  }

  QueryProcessor queryProcessor = new QueryProcessor();
  WebServer.Website website = new WebServer.Website();

  final HTMLResultPage htmlPage = (HTMLResultPage) context.mock(Page.class);
  final DownloadPage pdfFilePage = (DownloadPage) context.mock(Page.class);
  final DownloadPage mdFilePage = (DownloadPage) context.mock(Page.class);
  // ME : NEED TO DIFFERENTIATE BETWEEN THE TWO THOUGH

  // final ChoicePage choicePage = new ChoicePage("william", queryProcessor.process("william"));

  HttpServletRequest req = context.mock(HttpServletRequest.class);
  HttpServletResponse resp = context.mock(HttpServletResponse.class);

  @Test
  public void htmlPageGenerated() throws IOException {
    req.setAttribute("q", "shakespeare");
    req.setAttribute("type", "html");

    context.checking(new Expectations() {{
      exactly(1).of(htmlPage).writeTo(resp);
    }});

    website.doGet(req, resp);
  }

  @Test
  public void markdownPageGenerated() throws IOException {
    req.setAttribute("q", "shakespeare");
    req.setAttribute("type", "md");

    context.checking(new Expectations() {{
      exactly(1).of(mdFilePage).writeTo(resp);
    }});

    website.doGet(req, resp);
  }

  @Test
  public void pdfPageGenerated() throws IOException {
    req.setAttribute("q", "shakespeare");
    req.setAttribute("type", "pdf");

    context.checking(new Expectations() {{
      exactly(1).of(pdfFilePage).writeTo(resp);
    }});

    website.doGet(req, resp);
  }

}