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
    public boolean createEmployee(String nome, String sobrenome, String email, String cargo, String nascimento, String celular, String genero, double salario, String rua, int numero, String bairro, String cidade, String estado) {
        return SQLite.createEmployee(nome, sobrenome, email, cargo, nascimento, celular, genero, salario, rua, numero, bairro, cidade, estado);
    }
    
    public boolean changeEmployee() {
        return true;
    }
    
    public boolean deleteEmployee(int id) {
        return true;
    }
    
    public boolean searchEmployee(int id) {
        return true;
    }
    
    public ArrayList getAllEmployees() {
        return SQLite.getAllEmployees();
    }
}
