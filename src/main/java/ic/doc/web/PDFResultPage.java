package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
      resp.setHeader("Content-Disposition", "attachment;filename=\"" + query + ".pdf\"");

      File md = File.createTempFile(query, ".md");
      md.deleteOnExit();
      FileWriter fw = new FileWriter(md);
      fw.write("#" + query + "\n");
      fw.write(answer);
      fw.close();

      File pdf = File.createTempFile(query, ".pdf");
      // pdf.deleteOnExit();

      // check full addresses of tmp files to see if they can be accessed
      System.out.println(pdf.getAbsolutePath() + md.getAbsolutePath());

      // String[] commands = {"pandoc", "-s", tmp.getName(), "-o", query + ".pdf"};
      List<String> commands = new ArrayList<>();
      commands.add("bash");
      commands.add("pandoc");
      commands.add("-s");
      commands.add(md.getAbsolutePath());
      commands.add("-o");
      commands.add(pdf.getAbsolutePath());

      ProcessBuilder processBuilder = new ProcessBuilder(commands);
      Process process = processBuilder.start();

      try {
        process.waitFor();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      FileInputStream pdfInputStream = new FileInputStream(pdf);
      OutputStream servletOutputStream = resp.getOutputStream();
      servletOutputStream.write(pdfInputStream.readAllBytes());

      /*
      * ideas
      * specify pdf engine (conversion)
      * work out if files are actually being built
      * */
    }
  }
}