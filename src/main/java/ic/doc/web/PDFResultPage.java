package ic.doc.web;

import ic.doc.Query;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PDFResultPage implements Page {

  private final String query;
  private final Query answer;

  public PDFResultPage(String query, Query answer) {
    this.query = query;
    this.answer = answer;
  }

  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("application/pdf");

    if (answer == null || answer.getAnswer() == null || answer.getAnswer().isEmpty()) {
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
      fw.write(answer.getAnswer());
      fw.write("![alt text]("+answer.getImgURL()+" \"Pic\")");
      fw.write("[Learn More!]("+answer.getWikiURL()+")");
      fw.close();

      File pdf = File.createTempFile(query, ".pdf");
      pdf.deleteOnExit();

      ProcessBuilder processBuilder = new ProcessBuilder("pandoc",
          md.getAbsolutePath(), "-f", "markdown", "-o", pdf.getAbsolutePath());

      try {
        Process process = processBuilder.start();
        process.waitFor();
      } catch (InterruptedException | IOException e) {
        e.printStackTrace();
      }

      FileInputStream pdfInputStream = new FileInputStream(pdf);
      OutputStream servletOutputStream = resp.getOutputStream();
      servletOutputStream.write(pdfInputStream.readAllBytes());
    }
  }
}