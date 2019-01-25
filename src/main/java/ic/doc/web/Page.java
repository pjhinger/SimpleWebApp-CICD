package ic.doc.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Page {
  String htmlStyle = "body {\n" +
          "  text-align: center;\n" +
          "  background-color: rgb(255, 255, 153);\n" +
          "  /* background-image: url(\"https://ae01.alicdn.com/kf/HTB1nAkRi9YH8KJjSspdq6ARgVXa4/white-damask-gray-grey-Floral-pattern-Background-Vinyl-cloth-High-quality-Computer-print-wall-photo-backdrop.jpg\"); */\n" +
          "}\n" +
          "\n" +
          "header {\n" +
          "  position: sticky;\n" +
          "  top: 0;\n" +
          "  left: 0;\n" +
          "  right: 0;\n" +
          "  background-color: #ff9966;\n" +
          "}\n" +
          "\n" +
          "h1 {\n" +
          "  color: teal;\n" +
          "  font-family: \"Old English Text MT\", cursive;\n" +
          "  /* background-color: maroon; */\n" +
          "  font-size: 100px;\n" +
          "  width: 100%;\n" +
          "\n" +
          "}\n" +
          "\n" +
          ".information {\n" +
          "  text-align: left;\n" +
          "  width: 50%;\n" +
          "  left: 0;\n" +
          "  display: inline-block;\n" +
          "  font-size: 150%;\n" +
          "}\n" +
          "\n" +
          "p {\n" +
          "  font-size: 200%;\n" +
          "  /* display: inline-block; */\n" +
          "\n" +
          "  font-family: cursive;\n" +
          "  /* background-color: rebeccapurple; */\n" +
          "}\n" +
          "\n" +
          ".forms {\n" +
          "  /* background-color: rgb(204, 255, 204); */\n" +
          "}\n" +
          "\n" +
          ".forms input[type=\"text\"] {\n" +
          "  width: 250px;\n" +
          "  padding: 10px;\n" +
          "  font-weight: bold;\n" +
          "  background-color: yellow;\n" +
          "  font-family: \"Old English Text MT\", fantasy;\n" +
          "  font-size: 120%;\n" +
          "  /* color: blue; */\n" +
          "}\n" +
          "\n" +
          ".forms input[type=\"radio\"] {\n" +
          "  cursor: pointer;\n" +
          "  position: relative;\n" +
          "}\n" +
          "\n" +
          ".forms input[type=\"submit\"] {\n" +
          "  cursor: pointer;\n" +
          "  height: 50px;\n" +
          "  width: 100px;\n" +
          "  font-weight: bold;\n" +
          "  font-size: 120%;\n" +
          "  font-family: \"Old English Text MT\", cursive;\n" +
          "  background-color: maroon;\n" +
          "  color: yellow;\n" +
          "}\n" +
          "\n" +
          ".forms input[type=\"submit\"]:hover {\n" +
          "  background-color: blue;\n" +
          "}\n" +
          "\n" +
          ".radio-value {\n" +
          "  font-family: Stencil, cursive;\n" +
          "  font-size: 150%\n" +
          "}\n" +
          "\n" +
          "img {\n" +
          "  display: inline-block;\n" +
          "  border-radius: 50%;\n" +
          "  /* align: right; */\n" +
          "  width: 250px;\n" +
          "  margin-right: 100px;\n" +
          "  /* padding-right: 40px; */\n" +
          "}\n" +
          "\n" +
          "a:visited {\n" +
          "  color: red;\n" +
          "}\n" +
          "\n" +
          "a:hover {\n" +
          "  color: rgb(51, 204, 51);\n" +
          "}\n" +
          "\n" +
          "a:active {\n" +
          "  color: blue;\n" +
          "}\n" +
          "\n" +
          ".credits {\n" +
          "  position: static;\n" +
          "  text-align: left;\n" +
          "  bottom: 0;\n" +
          "  left: 0;\n" +
          "  font-size: 20px;\n" +
          "}\n";

  void writeTo(HttpServletResponse resp) throws IOException;
}
