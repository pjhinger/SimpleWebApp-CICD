package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class PDFResultPage implements Page {

  private final String query;
  private final String answer;

  public PDFResultPage(String query, String answer) {
    this.query = query;
    this.answer = answer;
  }

  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("application/pdf");
    resp.setHeader("Content-Disposition", "inline;filename=\"" + query + ".pdf\""); // ME: OR JUST REMOVE THIS LINE COMPLETELY? HTMLResult doesn't use this
    // resp.setContentLength((int) outputFile.length());
    PrintWriter writer = resp.getWriter();
    writer.println("#" + query);
    writer.println(answer);
  }
}