package ic.doc;

import ic.doc.web.DBReader;

import java.sql.ResultSet;
import java.util.*;

class QueryProcessor {

  private final DBReader dbReader;

  QueryProcessor(DBReader dbReader) {
    this.dbReader = dbReader;
  }

  ResultSet process(String query) {
    ResultSet possibilities = null;
    StringTokenizer queryTokens = new StringTokenizer(query);
    StringBuilder sqlQueryBuilder = new StringBuilder();
    if (queryTokens.hasMoreTokens()) {
      String token = queryTokens.nextToken().toLowerCase();
      sqlQueryBuilder.append("SELECT * FROM PEOPLE WHERE identifier LIKE '%").append(token).append("%'");
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
