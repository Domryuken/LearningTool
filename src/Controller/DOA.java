/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Word;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Domryuken
 */
public class DOA {

    private static Connection connection = null;
    private static final String db = "jdbc:derby://localhost:1527/WordsDB";
    private static final String username = "WordsUser";
    private static final String password = "WordsPass";

    public void insert() throws Exception {
        connection = DriverManager.getConnection(db, username, password);
        Statement statement = connection.createStatement();
    }

    public void update() {
    }

    public void delete() {
    }

    public void load() {
    }

}
