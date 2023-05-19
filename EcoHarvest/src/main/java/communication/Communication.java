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
        return true;
    }
    
    public boolean checkDatabaseStructure() {
        return true;
    }
    
    // Funções para ajustar banco de dados
    public boolean createDatabaseStructure() {
        return true;
    }
    
    public boolean checkAdminLogin(String username, String password) {
        return "admin".equals(username) && "admin".equals(password);
    }
    
    public boolean createAdminLogin(String username, String password) {
        return true;
    }
    
    public boolean updateAdminLogin(String username, String password) {
        return "admin".equals(username);
    }
    
    public boolean checkAdminUsername(String username) {
        return !"admin".equals(username);
    }
}
