package ic.doc;

import ic.doc.web.DownloadPage;
import ic.doc.web.HTMLResultPage;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class WebPagesTest {

  @Mock
  HttpServletRequest req;

  @Mock
  HttpServletResponse resp;

  @Rule
  public MockitoRule context = MockitoJUnit.rule();

  WebServer.Website website = new WebServer.Website();

  @Test
  public void htmlPageGenerated() throws IOException {
    when(req.getParameter("q")).thenReturn("shakespeare");
    when(req.getParameter("type")).thenReturn("html");
    when(resp.getWriter()).thenReturn(new PrintWriter(File.createTempFile("tmp","tmp")));
    when(resp.getContentType()).thenReturn("text/html");

    website.doGet(req, resp);
  }

  @Test
  public void markdownPageGenerated() throws IOException {
    when(req.getParameter("q")).thenReturn("shakespeare");
    when(req.getParameter("type")).thenReturn("md");
    when(resp.getWriter()).thenReturn(new PrintWriter(File.createTempFile("tmp","tmp")));
    when(resp.getContentType()).thenReturn("text/markdown");

    website.doGet(req, resp);
  }

  @Test
  public void pdfPageGenerated() throws IOException {
    when(req.getParameter("q")).thenReturn("shakespeare");
    when(req.getParameter("type")).thenReturn("pdf");
    when(resp.getWriter()).thenReturn(new PrintWriter(File.createTempFile("tmp","tmp")));
    when(resp.getContentType()).thenReturn("application/pdf");

    website.doGet(req, resp);
  }
}