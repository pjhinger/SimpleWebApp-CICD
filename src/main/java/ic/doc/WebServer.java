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
      String type = req.getParameter("type");
      if (query == null || type == null) {
        new IndexPage().writeTo(resp);
      } else {
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
          }
          catch (Exception e) {
            System.out.println("Unknown error occurred");
          }
          if (possibilities.size() == 1) {
            Query answer = possibilities.get(0);
            if (type.equals("html")) {
              new HTMLResultPage(query, answer).writeTo(resp);
            } else {
              new DownloadPage(query, answer, type).writeTo(resp);
            }
          } else {
            new ChoicePage(query, possibilities).writeTo(resp);
            // may not need a choice page class, just put code here
          }
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    new WebServer();
  }
}

