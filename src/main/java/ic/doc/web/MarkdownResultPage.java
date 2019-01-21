package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class MarkdownResultPage implements Page {

  private final String query;
  private final String answer;

  public MarkdownResultPage(String query, String answer) {
    this.query = query;
    this.answer = answer;
  }

  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain"); // ME : THIS is the MIME Type? before said text/markdown?
    resp.setHeader("Content-Disposition", "attachment;filename=\"" + query + ".md\"");
    PrintWriter writer = resp.getWriter();

    // Content
    if (answer == null || answer.isEmpty()) {
      writer.println("#Sorry");
      writer.println("Sorry, we didn't understand " + query);
    } else {
      writer.println("#" + query);
      writer.println(answer);
    }
  }
}
