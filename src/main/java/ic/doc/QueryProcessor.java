package ic.doc;

import ic.doc.web.DBReader;

import java.sql.ResultSet;
import java.util.*;

public class QueryProcessor {

  final DBReader dbReader;

  public QueryProcessor(DBReader dbReader) {
    this.dbReader = dbReader;
  }

//    this.queriesMap.add(new Query(Arrays.asList("augusta", "ada", "king", "lovelace"),
//        "Augusta Ada King, Countess of Lovelace (10 December 1815 - 27 November\n" +
//            "November 1852) was an English mathematician and writer, chiefly\n" +
//            "known for her work on Charles Babbage's proposed mechanical\n" +
//            "general-purpose computer, the Analytical Engine. She was the first to\n" +
//            "recognise that the machine had applications beyond pure calculation,\n" +
//            "and published the first algorithm intended to be carried out by such\n" +
//            "a machine. As a result, she is sometimes regarded as the first to\n" +
//            "recognise the full potential of a \"computing machine\" and the first\n" +
//            "computer programmer.",
//        "https://upload.wikimedia.org/wikipedia/commons/0/0b/Ada_Byron_daguerreotype_by_Antoine_Claudet_1843_or_1850.jpg",
//        "https://en.wikipedia.org/wiki/Ada_Lovelace"));


  public ResultSet process(String query) {
    ResultSet possibilities = null;
    StringTokenizer queryTokens = new StringTokenizer(query);
    StringBuilder sqlQueryBuilder = new StringBuilder();
    if (queryTokens.hasMoreTokens()) {
      String token = queryTokens.nextToken().toLowerCase();
      sqlQueryBuilder.append("SELECT * FROM PEOPLE WHERE identifier LIKE '%" + token + "%'");
      while (queryTokens.hasMoreTokens()) {
        token = queryTokens.nextToken().toLowerCase();
        sqlQueryBuilder.append(" AND identifier LIKE '%" + token+"%'");
      }
      sqlQueryBuilder.append(";");
      String sqlQuery = sqlQueryBuilder.toString();
      possibilities = dbReader.executeSQLQuery(sqlQuery);
    }
    return possibilities;
  }
}
