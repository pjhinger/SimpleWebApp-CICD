package ic.doc;

import ic.doc.web.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WebServer {

  public WebServer() throws Exception {
    Server server = new Server(Integer.valueOf(System.getenv("PORT")));

    ServletHandler handler = new ServletHandler();
    handler.addServletWithMapping(new ServletHolder(new Website()), "/*");
    server.setHandler(handler);

    server.start();
  }

  static class Website extends HttpServlet {

    final DBReader dbReader;
    final QueryProcessor queryProcessor;

    public Website(){
      dbReader = new DBReader();
      queryProcessor = new QueryProcessor(dbReader);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      String query = req.getParameter("q");
      String type = req.getParameter("type"); // || type == null
      if (query == null) {
        if(type != null) {
          String[] queryAndType = type.split(",");
          String[] q = queryAndType[0].split("\\+");
          renderResultsPage(String.join(" ", q), queryAndType[1], resp);
        } else {
          new IndexPage().writeTo(resp);
        }

      } else {
        renderResultsPage(query, "html", resp);
      }
    }

    private void renderResultsPage(String query, String type, HttpServletResponse resp) throws IOException {
      ResultSet queryRes = queryProcessor.process(query);
      List<Query> possibilities = new ArrayList<>();
      if (queryRes != null) {
        try {
          while (queryRes.next()) {
            String name = queryRes.getString("name");
            String description = queryRes.getString("description");
            String imgUrl = queryRes.getString("imgUrl");
            String wikiUrl = queryRes.getString("wikiUrl");
            possibilities.add(new Query(name, description, imgUrl, wikiUrl));
          }
        } catch (Exception e) {
          System.out.println("Unknown error occurred");
        }
      }
      if (possibilities.size() == 1) {
        Query answer = possibilities.get(0);
        if(type != null) {
          if (type.equals("html")) {
            new HTMLResultPage(query, answer).writeTo(resp);
          } else {
            new DownloadPage(query, answer, type).writeTo(resp);
          }
        }
      } else if (possibilities.size() > 1 ){
        new ChoicePage(query, possibilities).writeTo(resp);
      }
      else {
        new HTMLResultPage("", null).writeTo(resp);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    new WebServer();
  }
}

