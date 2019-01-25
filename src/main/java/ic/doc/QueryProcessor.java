package ic.doc;

import ic.doc.web.DBReader;

import java.sql.ResultSet;
import java.util.*;

public class QueryProcessor {

  final DBReader dbReader;

  public QueryProcessor(DBReader dbReader) {
    this.dbReader = dbReader;
  }

//    this.queriesMap.add(new Query(Arrays.asList(""),
//        ",
//        "",
//        ));


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
