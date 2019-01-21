package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MarkdownResultsPage implements Page {
  private final String query;
  private final String answer;

  public MarkdownResultsPage(String query, String answer) {
    this.query = query;
    this.answer = answer;
  }

  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("text/markdown");
    PrintWriter writer = resp.getWriter();

    // Content
    if (answer == null || answer.isEmpty()) {
      writer.println("#Sorry");
      writer.print("Sorry, we didn't understand " + query);
    } else {
      writer.println("#" + query);
      writer.println(answer.replace("\n", "<br>"));
    }

  }
}
