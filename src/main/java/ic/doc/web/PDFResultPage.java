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

      File tmp = File.createTempFile(query, ".md");
      FileWriter fw = new FileWriter(tmp);
      fw.write("#" + query + "\n");
      fw.write(answer);
      fw.close();

      FileInputStream fileInputStream = new FileInputStream(tmp);
      OutputStream servletOutputStream = resp.getOutputStream();
      System.out.println(fileInputStream.available());
      servletOutputStream.write(fileInputStream.readAllBytes());

      // String[] commands = {"pandoc", "-s", tmp.getName(), "-o", query + ".pdf"};
      List<String> commands = new ArrayList<String>();
      commands.add("bash");
      commands.add("pandoc");
      commands.add("-s");
      commands.add("-o");
      commands.add(query + ".pdf");
      commands.add(tmp.getName());
      ProcessBuilder processBuilder = new ProcessBuilder(commands);

//      processBuilder.command(commands); // "pandoc -s " + tmp.getName() + " -o " + query + ".pdf"
      Process process = processBuilder.start();

      /*try {
        process.waitFor();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }*/

      // tmp.delete(); // ME: this is getting ignored
    }
  }
}