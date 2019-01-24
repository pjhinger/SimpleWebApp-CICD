package ic.doc.web;

import ic.doc.Query;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class MarkdownResultPage implements Page {

  private final String query;
  private final Query answer;

  public MarkdownResultPage(String query, Query answer) {
    this.query = query;
    this.answer = answer;
  }

  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("text/markdown"); // ME : THIS is the MIME Type? before said text/markdown?

    // Content
    if (answer == null || answer.getAnswer() == null || answer.getAnswer().isEmpty()) {
      resp.setHeader("Content-Disposition", "attachment;filename=\"sorry.md\"");
      PrintWriter writer = resp.getWriter();
      writer.println("#Sorry");
      writer.println("Sorry, we didn't understand " + query + ".");
    } else {
      resp.setHeader("Content-Disposition", "attachment;filename=\"" + query + ".md\"");

      File tmp = File.createTempFile(query, ".tmp");
      FileWriter fw = new FileWriter(tmp);
      fw.write("#" + query + "\n");
      fw.write(answer.getAnswer());
      fw.close();

      FileInputStream fileInputStream = new FileInputStream(tmp);
      OutputStream servletOutputStream = resp.getOutputStream();
      servletOutputStream.write(fileInputStream.readAllBytes());
      // flush/close?

      tmp.deleteOnExit();
    }
  }
}
