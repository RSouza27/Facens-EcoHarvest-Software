/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Anne
 */
public class SQLite {
    private static final String DB_FILE = "db.sqlite";
    private static final String TABLE_ADMIN = "admin";

    private static boolean isDatabaseExist() {
        File file = new File(DB_FILE);
        return file.exists();
    }
    
    private static boolean createDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE)) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    private static boolean isTableExist(String TABLE_NAME) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE)) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, TABLE_NAME, null);
            return tables.next();
        } catch (SQLException e) {
            return false;
        }
    }
    
    public static boolean checkDatabaseConnection() {
        if (!isDatabaseExist()) {
            return createDatabase();
        }
        return true;
    }
    
    public static boolean checkDatabaseStructure() {
        if (!isTableExist(TABLE_ADMIN)) {
            if (!createAdminTable()){
                return false;
            }
        }
        return true;
    }

    private static boolean createAdminTable() {
        String createTableQuery = "CREATE TABLE " + TABLE_ADMIN + " ("
                + "id INTEGER PRIMARY KEY,"
                + "username TEXT NOT NULL,"
                + "password TEXT NOT NULL"
                + ")";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
            Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
