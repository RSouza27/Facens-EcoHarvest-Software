/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Anne
 */
public class SQLite {
    private static final String DB_FILE = "db.sqlite";
    private static final String TABLE_ADMIN = "admin";
    private static final String TABLE_EMPLOYEE = "employee";
    private static final String TABLE_PRODUCTS = "products";

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
        if (!isTableExist(TABLE_EMPLOYEE)) {
            if (!createEmployeeTable()){
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
    
    private static boolean createEmployeeTable() {
        String createTableQuery = "CREATE TABLE " + TABLE_EMPLOYEE + " ("
                + "id INTEGER PRIMARY KEY,"
                + "nome TEXT NOT NULL,"
                + "sobrenome TEXT NOT NULL,"
                + "email TEXT NOT NULL,"
                + "cargo TEXT NOT NULL,"
                + "nascimento TEXT NOT NULL,"
                + "celular TEXT NOT NULL,"
                + "genero TEXT NOT NULL,"
                + "salario REAL NOT NULL"
                + ")";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
            Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
   
    private static boolean createProductsTable() {
        String createTableQuery = "CREATE TABLE " + TABLE_PRODUCTS + " ("
                + "ID INTEGER PRIMARY KEY,"
                + "Nome TEXT NOT NULL,"
                + "Peso TEXT NOT NULL,"
                + "Disponibilidade TEXT NOT NULL,"
                + "QtndEstoque TEXT NOT NULL,"
                + "Compra TEXT NOT NULL,"
                + "Venda TEXT NOT NULL,"
                + ")";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
            Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }    
    
    public static boolean checkAdminExist(String username) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM " + TABLE_ADMIN + " WHERE username = ?")) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {}
        return false;
    }
    
    public static boolean createAdmin(String username, String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + TABLE_ADMIN + " (username, password) VALUES (?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public static boolean createEmployee(String nome, String sobrenome, String email, String cargo, String nascimento, String celular, String genero, double salario) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + TABLE_EMPLOYEE + " (nome, sobrenome, email, cargo, nascimento, celular, genero, salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, nome);
            stmt.setString(2, sobrenome);
            stmt.setString(3, email);
            stmt.setString(4, cargo);
            stmt.setString(5, nascimento);
            stmt.setString(6, celular);
            stmt.setString(7, genero);
            stmt.setDouble(8, salario);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public static boolean checkAdminLogin(String username, String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM " + TABLE_ADMIN + " WHERE username = ? AND password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {}
        return false;
    }
    
    public static boolean updateAdminLogin(String username, String newPassword) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
             PreparedStatement stmt = conn.prepareStatement("UPDATE " + TABLE_ADMIN + " SET password = ? WHERE username = ?")) {

            stmt.setString(1, newPassword);
            stmt.setString(2, username);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {}
        return false;
    }
    
    public static ArrayList getAllEmployees() {
        ArrayList allEmployees = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_EMPLOYEE);
            ResultSet rs = stmt.executeQuery()) {
            
            ArrayList<Integer> ids = new ArrayList<>();
            ArrayList<String> nomes = new ArrayList<>();
            ArrayList<String> sobrenomes = new ArrayList<>();
            ArrayList<String> emails = new ArrayList<>();
            ArrayList<String> cargos = new ArrayList<>();
            ArrayList<String> nascimentos = new ArrayList<>();
            ArrayList<String> celulares = new ArrayList<>();
            ArrayList<String> generos = new ArrayList<>();
            ArrayList<Double> salarios = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getInt("id"));
                nomes.add(rs.getString("nome"));
                sobrenomes.add(rs.getString("sobrenome"));
                emails.add(rs.getString("email"));
                cargos.add(rs.getString("cargo"));
                nascimentos.add(rs.getString("nascimento"));
                celulares.add(rs.getString("celular"));
                generos.add(rs.getString("genero"));
                salarios.add(rs.getDouble("salario"));
            }
            allEmployees.add(ids);
            allEmployees.add(nomes);
            allEmployees.add(sobrenomes);
            allEmployees.add(emails);
            allEmployees.add(cargos);
            allEmployees.add(nascimentos);
            allEmployees.add(celulares);
            allEmployees.add(generos);
            allEmployees.add(salarios);
        } catch (SQLException e) {}
        return allEmployees;
    }
    
    public static ArrayList<Object> getEmployeeData(int id) {
        ArrayList<Object> employeeData = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE)) {
            String sql = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                employeeData.add(rs.getInt("id"));
                employeeData.add(rs.getString("nome"));
                employeeData.add(rs.getString("sobrenome"));
                employeeData.add(rs.getString("email"));
                employeeData.add(rs.getString("cargo"));
                employeeData.add(rs.getString("nascimento"));
                employeeData.add(rs.getString("celular"));
                employeeData.add(rs.getString("genero"));
                employeeData.add(rs.getDouble("salario"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return employeeData;
    }

    public static boolean setEmployeeData(int id, String nome, String sobrenome, String email, String cargo, String nascimento, String celular, String genero, double salario) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE)) {
            String sql = "UPDATE " + TABLE_EMPLOYEE + " SET nome = ?, sobrenome = ?, email = ?, cargo = ?, nascimento = ?, celular = ?, genero = ?, salario = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, sobrenome);
            stmt.setString(3, email);
            stmt.setString(4, cargo);
            stmt.setString(5, nascimento);
            stmt.setString(6, celular);
            stmt.setString(7, genero);
            stmt.setDouble(8, salario);
            stmt.setInt(9, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {}
        return false;
    }
    
    public static boolean delEmployeeData(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE)) {
            String sql = "DELETE FROM " + TABLE_EMPLOYEE + " WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee with ID " + id + " was deleted successfully.");
                return true;
            } else {
                System.out.println("No Employee was found with ID " + id);
                return false;
            }
        } catch (SQLException e) {}
        return false;
    }

    static ArrayList<Object> getProductData(int ID) {
        ArrayList<Object> productsData = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE)) {
            String sql = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                productsData.add(rs.getInt("ID"));
                productsData.add(rs.getString("Nome"));
                productsData.add(rs.getString("Peso"));
                productsData.add(rs.getString("Disponibilidade"));
                productsData.add(rs.getString("QtndEstoque"));
                productsData.add(rs.getString("Compra"));
                productsData.add(rs.getString("Venda"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return productsData;
    }

    static ArrayList getAllProduct() {
        ArrayList allProducts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_PRODUCTS);
            ResultSet rs = stmt.executeQuery()) {
            
            ArrayList<Integer> IDS = new ArrayList<>();
            ArrayList<String> Nomes = new ArrayList<>();
            ArrayList<String> Pesos = new ArrayList<>();
            ArrayList<String> Disponibilidades = new ArrayList<>();
            ArrayList<String> QtndEstoques = new ArrayList<>();
            ArrayList<String> Compras = new ArrayList<>();
            ArrayList<String> Vendas = new ArrayList<>();

            while (rs.next()) {
                IDS.add(rs.getInt("ID"));
                Nomes.add(rs.getString("Nome"));
                Pesos.add(rs.getString("Peso"));
                Disponibilidades.add(rs.getString("Disponibilidade"));
                QtndEstoques.add(rs.getString("QtndEstoque"));
                Compras.add(rs.getString("Compra"));
                Vendas.add(rs.getString("Venda"));

            }
            allProducts.add(IDS);
            allProducts.add(Nomes);
            allProducts.add(Pesos);
            allProducts.add(Disponibilidades);
            allProducts.add(QtndEstoques);
            allProducts.add(Compras);
            allProducts.add(Vendas);
        } catch (SQLException e) {}
        return allProducts;
    }

    static boolean createProduct(String Nome, String Peso, String Disponibilidade, String QtndEstoque, String Compra, String Venda) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + TABLE_PRODUCTS + " (Nome, Peso, Disponibilidade, QtndEstoque, Compra, Venda) VALUES (?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, Nome);
            stmt.setString(2, Peso);
            stmt.setString(3, Disponibilidade);
            stmt.setString(4, QtndEstoque);
            stmt.setString(5, Compra);
            stmt.setString(6, Venda);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    static boolean setProductData(int ID, String Nome, String Peso, String Disponibilidade, String QtndEstoque, String Compra, String Venda) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE)) {
            String sql = "UPDATE " + TABLE_PRODUCTS + " SET Nome = ?, Peso = ?, Disponibilidade = ?, QtndEstoque = ?, Compra = ?, Venda = ?, WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Nome);
            stmt.setString(2, Peso);
            stmt.setString(3, Disponibilidade);
            stmt.setString(4, QtndEstoque);
            stmt.setString(5, Compra);
            stmt.setString(6, Venda);
            stmt.setInt(7, ID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {}
        return false;
    }

    static boolean delProductData(int ID) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE)) {
            String sql = "DELETE FROM " + TABLE_PRODUCTS + " WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product with ID " + ID + " was deleted successfully.");
                return true;
            } else {
                System.out.println("No Product was found with ID " + ID);
                return false;
            }
        } catch (SQLException e) {}
        return false;
    }

}

