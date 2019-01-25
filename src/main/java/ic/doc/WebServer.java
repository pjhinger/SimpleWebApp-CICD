package ic.doc;

import ic.doc.web.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      String query = req.getParameter("q");
      String type = req.getParameter("type");
      if (query == null || type == null) {
        new IndexPage().writeTo(resp);
      } else {
        QueryProcessor queryProcessor = new QueryProcessor();
        List<Query> possibilities = queryProcessor.process(query);
        if (possibilities.size() == 1) {
          Query answer = possibilities.get(0);
          if (type.equals("html")) {
            new HTMLResultPage(query, answer).writeTo(resp);
          } else {
            new DownloadPage(query, answer, type).writeTo(resp);
          }
        } else {
          new ChoicePage(query, possibilities).writeTo(resp);
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    new WebServer();
  }
}

