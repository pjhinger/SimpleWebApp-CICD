package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

      File md = File.createTempFile(query, ".md");
      md.deleteOnExit();
      FileWriter fw = new FileWriter(md);
      fw.write("#" + query + "\n");
      fw.write(answer);
      fw.close();

      String pdfFileName = query + ".pdf";
      //File pdf = File.createTempFile(query, ".pdf");
      // pdf.deleteOnExit();

      String[] commands = {"bash", "pandoc", "-s", "-o", md.getName(), pdfFileName};

      ProcessBuilder processBuilder = new ProcessBuilder(commands);
      Process process = processBuilder.start();

      try {
        process.waitFor();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      File pdf = Paths.get(pdfFileName).toFile();
      FileInputStream pdfInputStream = new FileInputStream(pdf);
      OutputStream servletOutputStream = resp.getOutputStream();
      servletOutputStream.write(pdfInputStream.readAllBytes());
      // flush/close?

      /*
      * ideas
      * specify pdf engine (conversion)
      * work out if files are actually being built
      * */
    }
  }
}