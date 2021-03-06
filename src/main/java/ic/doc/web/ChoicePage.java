package ic.doc.web;

import ic.doc.Query;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class ChoicePage implements Page {

  private final String ambiguousQuery;
  private final List<Query> possibilities;

  public ChoicePage(String ambiguousQuery, List<Query> possibilities) {
    this.ambiguousQuery = ambiguousQuery;
    this.possibilities = possibilities;
  }

  @Override
  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();

    // Header
    writer.println("<html>");
    writer.println("<head>");
    writer.println("<style>");
    writer.println(htmlStyle);
    writer.println("</style>");
    writer.println("<title>" + ambiguousQuery + "</title></head>");
    writer.println("<body>");

    // Content - list of hyperlinked choices
    writer.print("<h1>Disambiguation for \"" + ambiguousQuery + "\"</h1>" +
        "<p>" + ambiguousQuery + " gave many results. Select which result you would like to view " +
        "<br><br>");
    while (!possibilities.isEmpty()) {
      String nameString = possibilities.get(0).getName();
      String name = String.join(" ", nameString);
      String ref = String.join("+", nameString);
      writer.print("<a href=\"/?q=" + ref + "&type=html\">" + name + "</a>" +
          "<br><br>");
      possibilities.remove(0);
    }
    writer.println("</p>");

    // Footer
    writer.println("</body>");
    writer.println("</html>");
  }
}
