package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
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
      String pdfname = query + ".pdf";
      resp.setHeader("Content-Disposition", "inline;filename=\"" + pdfname + "\"");

      resp.setHeader("Expires", "0");
      resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
      resp.setHeader("Pragma", "public");

      File md = File.createTempFile(query, ".md");
      md.deleteOnExit();
      FileWriter fw = new FileWriter(md);
      fw.write("#" + query + "\n");
      fw.write(answer);
      fw.close();
      System.out.println("md file size = " + md.length());

      File pdf = File.createTempFile(query, ".pdf");
      pdf.deleteOnExit();
      System.out.println("pdf size BEFORE = " + pdf.length());

      /*new ProcessBuilder("bash", "-c", "pandoc", "-s", md.getAbsolutePath(),
          "--pdf-engine=xelatex", "-o", pdf.getAbsolutePath());*/

      ProcessBuilder processBuilder =
          new ProcessBuilder("/usr/bin/bash", "-c", "/usr/bin/pandoc -s " + md.getAbsolutePath() +
              " --pdf-engine=xelatex -o " + pdf.getAbsolutePath());

      try {
        Process process = processBuilder.start();
        process.waitFor();
        System.out.println("finished the pandoc");
        System.out.println("process exit code: " + process.exitValue());
        System.out.println("" + process.getErrorStream().readAllBytes());
      } catch (InterruptedException | IOException e) {
        e.printStackTrace();
        System.out.println("caught an exception");
      }
      System.out.println("pdf size AFTER = " + pdf.length());

      /*File file = new File("/homes/pj2017/Downloads/eg.pdf");
      FileInputStream fis = new FileInputStream(file);
      byte[] bytes = fis.readAllBytes();*/

      FileInputStream pdfInputStream = new FileInputStream(pdf);
      byte[] bytes = pdfInputStream.readAllBytes();
      resp.setContentLength(bytes.length);
      OutputStream servletOutputStream = resp.getOutputStream();
      servletOutputStream.write(bytes);
      //System.out.println("size = " + Integer.toHexString(bytes.length));
      //System.out.println(bytes);
      // delete file?
      // flush/close?

      /* ideas
       * specify pdf engine (conversion)
       * work out if files are actually being built
       * */
    }
  }
}