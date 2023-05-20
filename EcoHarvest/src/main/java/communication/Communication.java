/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

/**
 *
 * @author Unknown Account
 */
public class Communication {
    
    // Funções para verificar integridade do banco de dados.
    public boolean checkDatabaseConnection() {
        return SQLite.checkDatabaseConnection();
    }
    
    public boolean checkDatabaseStructure() {
        return SQLite.checkDatabaseStructure();
    }
    
    // Funções para trabalhar com banco de dados.
    public boolean checkAdminLogin(String username, String password) {
        return SQLite.checkAdminLogin(username, password);
    }
    
    public boolean createAdminLogin(String username, String password) {
        return SQLite.createAdmin(username, password);
    }
    
    public boolean updateAdminLogin(String username, String password) {
        return SQLite.updateAdminLogin(username, password);
    }
    
    public boolean checkAdminExist(String username) {
        return SQLite.checkAdminExist(username);
    }
}
