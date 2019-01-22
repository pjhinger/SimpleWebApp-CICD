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
      resp.setHeader("Content-Disposition", "inline;filename=\"" + query + ".pdf\"");

      File md = File.createTempFile(query, ".md");
      FileWriter fw = new FileWriter(md);
      fw.write("#" + query + "\n");
      fw.write(answer);
      fw.close();


      // FileInputStream mdInputStream = new FileInputStream(md);
      // OutputStream servletOutputStream = resp.getOutputStream();
      // servletOutputStream.write(mdInputStream.readAllBytes());

      File pdf = File.createTempFile(query, ".pdf");

      // String[] commands = {"pandoc", "-s", tmp.getName(), "-o", query + ".pdf"};
      List<String> commands = new ArrayList<>();
      commands.add("bash");
      commands.add("pandoc");
      commands.add("-s");
      commands.add(md.getName());
      commands.add("-o");
      commands.add(pdf.getName());

      ProcessBuilder processBuilder = new ProcessBuilder(commands);

      Process process = processBuilder.start();

      FileInputStream pdfInputStream = new FileInputStream(pdf);
      OutputStream servletOutputStream = resp.getOutputStream();
      servletOutputStream.write(pdfInputStream.readAllBytes());

      /*try {
        process.waitFor();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }*/

      tmp.deleteOnExit(); // ME: this is getting ignored
    }
  }
}