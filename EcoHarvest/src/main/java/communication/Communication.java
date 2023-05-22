/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.util.ArrayList;

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
    
    // Funções para administrar funcionários.
    public boolean createEmployee(String nome, String sobrenome, String email, String cargo, String nascimento, String celular, String genero, double salario) {
        return SQLite.createEmployee(nome, sobrenome, email, cargo, nascimento, celular, genero, salario);
    }
    
    public boolean changeEmployee(int id, String nome, String sobrenome, String email, String cargo, String nascimento, String celular, String genero, double salario) {
        return SQLite.setEmployeeData(id, nome, sobrenome, email, cargo, nascimento, celular, genero, salario);
    }
    
    public boolean deleteEmployee(int id) {
        return SQLite.delEmployeeData(id);
    }
    
    public ArrayList<Object> searchEmployee(int id) {
        return SQLite.getEmployeeData(id);
    }
    
    public ArrayList getAllEmployees() {
        return SQLite.getAllEmployees();
    }
}
