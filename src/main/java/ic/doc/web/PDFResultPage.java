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

    if (answer == null || answer.isEmpty()) {
      resp.setHeader("Content-Disposition", "inline;filename=\"sorry.pdf\"");
      PrintWriter writer = resp.getWriter();
      writer.println("#Sorry");
      writer.println("Sorry, we didn't understand " + query + ".");
    } else {
      resp.setHeader("Content-Disposition", "inline;filename=\"" + query + ".pdf\"");

      File tmp = File.createTempFile(query, ".tmp");
      FileWriter fw = new FileWriter(tmp);
      fw.write("#" + query + "\n");
      fw.write(answer);
      fw.close();

      FileInputStream fileInputStream = new FileInputStream(tmp);
      OutputStream servletOutputStream = resp.getOutputStream();
      servletOutputStream.write(fileInputStream.readAllBytes());

      ProcessBuilder processBuilder = new ProcessBuilder();

      String[] commands = {"pandoc", tmp.getName(), "-o", query + ".pdf"};
      processBuilder.command(commands);
      Process process = processBuilder.start();

      try {
        process.waitFor();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // tmp.delete(); // ME: this is getting ignored
    }
  }
}