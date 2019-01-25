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
    String name = String.join("+", answer.getQuery());

    // Header
    writer.println("<html>");
    writer.println("<head>"+
            "<style>"+htmlStyle+"</style>"+
            "<title>" + query + "</title></head>");

    writer.println("<body>");

    // Content
    if (answer == null || answer.getAnswer() == null || answer.getAnswer().isEmpty()) {
      writer.println("<h1>Sorry</h1>");
      writer.print("<p>Sorry, we didn't understand <em>" + query + "</em></p>");
    } else {
      writer.println("<header>");
      writer.println("<h1>" + query + "</h1>");
      writer.println("</header>");

      writer.println("<p class=\"information\">" + answer.getAnswer().replace("\n", "<br>") + "</p>");
      writer.println("<img src=\"" + answer.getImgURL() + "\">");

      writer.println("<form>");
      writer.println("<button type=\"submit\" name=\"type\" value=\""+name+",markdown\"> Markdown </button>");
      writer.println("<button type=\"submit\" name=\"type\" value=\""+name+",pdf\"> PDF </button>");
      writer.println("</form>");

      writer.println("<p><a href=\""+answer.getWikiURL()+"\" target=\"_blank" +
              "\">Learn More!</a></p>");

      writer.println("<p><a href=\"/\">Back to Search Page</a></p>");

      writer.println("<div class=\"credits\">");
      writer.println("<a href=\"" + answer.getImgURL() + "\"> Image credits: from wikipedia");
      writer.println("</div>");
    }



    // Footer
    writer.println("</body>");
    writer.println("</html>");
  }
}
