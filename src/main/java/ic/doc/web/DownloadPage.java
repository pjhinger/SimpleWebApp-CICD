package ic.doc.web;

import ic.doc.Query;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadPage implements Page{

    private final String query;
    private final Query answer;
    private final String format;

    public DownloadPage(String query, Query answer, String format) {
        this.query = query;
        this.answer = answer;
        this.format = format;
    }

    public void writeTo(HttpServletResponse resp) throws IOException{
        if (format.equals("pdf")) {
            resp.setContentType("application/pdf");

        }
        if (format.equals("md")) {
            resp.setContentType("text/markdown");
        }

        if (answer == null || answer.getDescription() == null || answer.getDescription().isEmpty()) {
            resp.setHeader("Content-Disposition", "attachment;filename=\"sorry." + format + "\"");
            PrintWriter writer = resp.getWriter();
            writer.println("#Sorry");
            writer.println("Sorry, we didn't understand " + query + ".");
        } else {
            resp.setHeader("Content-Disposition", "attachment;filename=\"" + query + "."+format+"\"");

            try {
                FileInputStream pdfInputStream = new FileInputStream(createFile());
                OutputStream servletOutputStream = resp.getOutputStream();
                servletOutputStream.write(pdfInputStream.readAllBytes());
            } catch (IOException e) {
                System.out.println("Unable to create file");
            }

        }
    }

    private File createFile() throws IOException{
        File tmp = File.createTempFile(query, ".md");
        tmp.deleteOnExit();
        FileWriter fw = new FileWriter(tmp);
        fw.write("#" + query + "\n");
        fw.write(answer.getDescription());
        fw.write("![alt text]("+answer.getImgURL()+" \"Pic\")");
        fw.write("[Learn More!]("+answer.getWikiURL()+")");
        fw.close();
        if (format.equals("pdf")) {
            File pdf = File.createTempFile(query, ".pdf");
            pdf.deleteOnExit();

            ProcessBuilder processBuilder = new ProcessBuilder("pandoc",
                tmp.getAbsolutePath(), "-f", "markdown", "-o", pdf.getAbsolutePath());

            try {
                Process process = processBuilder.start();
                process.waitFor();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            return pdf;
        }
        return tmp;
    }
}
