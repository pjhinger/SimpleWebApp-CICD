package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class MarkdownResultsPage implements Page {
  private final String query;
  private final String answer;

  public MarkdownResultsPage(String query, String answer) {
    this.query = query;
    this.answer = answer;
  }

  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain"); // ME : THIS is the MIME Type? before said text/markdown?
    resp.setHeader(query, "attachement;filename=" + query + ".md");
    PrintWriter writer = resp.getWriter();

    // Content
    if (answer == null || answer.isEmpty()) {
      writer.println("#Sorry");
      writer.println("Sorry, we didn't understand " + query);
    } else {
      writer.println("#" + query);
      writer.println(answer);
      //writer.println(answer.replace("\n", "<br>"));

      /*File file = new File("C:\\" + query + ".md");
      FileInputStream fileIn = new FileInputStream(file);
      OutputStream out = resp.getOutputStream();

      byte[] outputBytes = new byte[4096];
      while(fileIn.read(outputBytes, 0, 4096) != -1) {
        out.write(outputBytes, 0, 4096);
      }
      fileIn.close();
      out.flush();
      out.close();*/
    }

    // create temporary file
    // write query result to it
    // serve file data to user by turning file into input stream and transfering its
    // bytes to the output stream of the http response

  }
}
