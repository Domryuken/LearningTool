 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Domryuken
 */
public class Word {

    public static int interval = 5000;
    private String description;
    private String english;
    private String japanese;
    private int score;
    private long timer;

    public Word() {
        score = 1;
        timer = 0;
    }
    public Word(String j, String e, String d) {
        english = e;
        japanese = j;
        description = d;
        score = 1;
        timer = 0;
    }
    public Word(String j, String e, String d, int s, int t) {
        english = e;
        japanese = j;
        description = d;
        score = s;
        timer = t;
    }
    
    public int getScore() {return score;}
    public long getTime(){return timer;}
    public String getEnglish() {return english;}
    public String getJapanese() {return japanese;}
    public String getDescription() {return description;}
    
    public void setScore(int score) {this.score = score;}
    public void setTime(long time) {this.timer = time;}
    public void setEnglish(String english) {this.english = english;}
    public void setJapanese(String japanese) {this.japanese = japanese;}
    public void setDescription(String description) {this.description = description;}
    

    public String fileString() {
        String string = japanese + "/" + english + "/" + description + "/" + score + "/" + timer;
        return string;
    }

    public String question() {
        String string = english + " " + description;
        return string;
    }

    public boolean answer(String a) {
        setTime();
        if (a.equals(japanese)) {
            if (score < 5) {
                score++;
            }
            return true;
        } else {
            if (score > 0) {
                score--;
            }
            return false;
        }
    }
    
    public void setTime()
    {
        timer = System.currentTimeMillis();
    }
    
    public boolean timeUp()
    {
        return (System.currentTimeMillis() - timer) > (interval* (score * score));
    }
    
    @Override
    public String toString()
    {
        String string = "Japanese: " + japanese + "\tEnglish: " + english + " " + description;
        return string;
    }
}
