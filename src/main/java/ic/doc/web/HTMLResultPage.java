package ic.doc.web;

import ic.doc.Query;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HTMLResultPage implements Page {

  private final String query;
  private final Query answer;

  public HTMLResultPage(String query, Query answer) {
    this.query = query;
    this.answer = answer;
  }

  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();

    // Header
    writer.println("<html>");
    writer.println("<head><title>" + query + "</title></head>");

    writer.println("<body>");

    // Content
    if (answer == null || answer.getDescription() == null || answer.getDescription().isEmpty()) {
      writer.println("<h1>Sorry</h1>");
      writer.print("<p>Sorry, we didn't understand <em>" + query + "</em></p>");
    } else {
      writer.println("<h1>" + query + "</h1>");
      writer.println("<p>" + answer.getDescription().replace("\n", "<br>") + "</p>");
      writer.println("<p> <img src=\"" + answer.getImgURL() + "\"> </p>");
      writer.println("<p><a href=\""+answer.getWikiURL()+"\" target=\"_blank" +
              "\">Learn More!</a></p>");
      writer.println("<p><a href=\"/\">Back to Search Page</a></p>");
    }



    // Footer
    writer.println("</body>");
    writer.println("</html>");
  }
}
