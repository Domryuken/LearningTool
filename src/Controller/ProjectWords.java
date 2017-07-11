package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import View.MainUI;

/**
 *
 * @author Domryuken
 */
public class ProjectWords {

    public static String dawords = "C:\\Users\\Domryuken\\Documents\\NetBeansProjects\\Words\\dawords.txt";
    public static String test = "C:\\Users\\Domryuken\\Documents\\NetBeansProjects\\Words\\test.txt";
    public static String saved = "C:\\Users\\Domryuken\\Documents\\NetBeansProjects\\Words\\SavedWords";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainUI ui = new MainUI();
        Controller controller = new Controller(ui);
        
    }
}
