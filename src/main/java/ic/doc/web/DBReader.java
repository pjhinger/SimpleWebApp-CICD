package ic.doc.web;


import java.sql.*;

public class DBReader {

    Connection wiki;
    Statement state;

    public DBReader(){
        try {
            Class.forName("org.sqlite.JDBC");
            wiki = DriverManager.getConnection("jdbc:sqlite:wiki.db");
            state = wiki.createStatement();
        } catch (Exception e) {
            System.out.println("Failed to open database/statement");
            System.exit(0);
        }
    }

    public ResultSet executeSQLQuery(String query) {
        ResultSet resultSet = null;
        try {
            resultSet = state.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Failed to execute query");
            System.exit(0);
        }
        return resultSet;
    }

}
