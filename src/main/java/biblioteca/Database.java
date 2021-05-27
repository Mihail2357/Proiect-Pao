package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Database implements AutoCloseable {
    private static Database database = null;
    private final Connection connection;


    public static Database getDatabaseInstance(){
        if (database == null) {
            try {
                database = new Database();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return database;
    }

    private Database() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true&useSSL=false", "root","password");
        createTables();
    }

    private void createTables() throws SQLException {

        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        List<String> databaseTablesName = new ArrayList<>();
        while(results.next())
            databaseTablesName.add(results.getString("TABLE_NAME").toLowerCase());

        if (databaseTablesName.size() != 4){
            HashMap<String, String> tableStatements = new HashMap<>();
            tableStatements.put("audio_books", "CREATE TABLE audio_books (title varchar(45) primary key, autor varchar(45), section varchar(45), " +
                    "releaseDate varchar(45), numberOfMinutes varchar(45), narator varchar(45))");
            tableStatements.put("paper_books", "CREATE TABLE paper_books (title varchar(45) primary key, autor varchar(45), section varchar(45), " +
                    "releaseDate varchar(45), publisher varchar(45), numberOfPages varchar(45))");
            tableStatements.put("limited_users", "CREATE TABLE limited_users (name varchar(45) , email varchar(45), book varchar(45), " +
                    "numberOfCredits varchar(45))");
            tableStatements.put("premium_users", "CREATE TABLE premium_users (name varchar(45) , email varchar(45), book varchar(45), " +
                    "daysOfSubscription varchar(45))");
            for(Map.Entry<String, String> table : tableStatements.entrySet()) {
                boolean found = databaseTablesName.contains(table.getKey());
                if (!found) {
                    connection.createStatement().execute(table.getValue());
                }
            }
        }
    }

    public void insertBook(String[] data, String table) {
        try {
            PreparedStatement st1 = connection.prepareStatement("SELECT * FROM "+table+" WHERE title = ?");
            st1.setString(1, data[0]);
            ResultSet rs = st1.executeQuery();
            if(!rs.next()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?,?,?,?)");
                for (int parameterIndex = 1; parameterIndex <= 6; parameterIndex++)
                    statement.setString(parameterIndex, data[parameterIndex - 1]);
                int i = statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    @Override
    public void close() throws Exception {
        connection.close();
    }
}