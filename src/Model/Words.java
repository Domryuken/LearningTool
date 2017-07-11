/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Domryuken
 */
public class Words implements Serializable{

    
    private ArrayList<Word> vocab = new ArrayList<>();
    private ArrayList<Word> pool = new ArrayList<>();
//    private ArrayList<Word> current = new ArrayList<>();

    public Words() {
    }

    public boolean newWord() {
        if (vocab.size() == 0) {
            return false;
        }
        Random rn = new Random();
        int i = rn.nextInt(vocab.size());
        pool.add(vocab.get(i));
        vocab.remove(i);
        return true;
    }

    public boolean addWord(String j, String e, String d) {
        return addWord(new Word(j, e, d));
    }

    public boolean addWord(Word word) {
        for (Word w : vocab) {
            if (w.getEnglish().equals(word.getEnglish())) {
                if (w.getJapanese().equals(word.getJapanese())) {
                    return false;
                }
            }
        }
        for (Word w : pool) {
            if (w.getEnglish().equals(word.getEnglish())) {
                if (w.getJapanese().equals(word.getJapanese())) {
                    return false;
                }
            }
        }
        vocab.add(word);
        return true;
    }

    public boolean addPoolWord(String j, String e, String d) {
        return addPoolWord(new Word(j, e, d));
    }

    public boolean addPoolWord(Word word) {
        for (Word w : vocab) {
            if (w.getEnglish().equals(word.getEnglish())) {
                if (w.getJapanese().equals(word.getJapanese())) {
                    return false;
                }
            }
        }
        for (Word w : pool) {
            if (w.getEnglish().equals(word.getEnglish())) {
                if (w.getJapanese().equals(word.getJapanese())) {
                    return false;
                }
            }
        }
        pool.add(word);
        return true;
    }

    public Word getWord(int i) {
        Word word = pool.get(i);
        return word;
    }

    public Word getWordVocab(int i) {
        Word word = vocab.get(i);
        return word;
    }

    public ArrayList<Word> readyCurrentWords() {
        ArrayList<Word> current = new ArrayList<>();
        for (Word w : pool) {
            if (w.timeUp()) {
                current.add(w);
            }
        }
        return current;
    }

//    public void recommitCurrent() {
//        for (Word w : current) {
//            pool.add(w);
//            current.remove(w);
//        }
//    }

    public ArrayList<Word> getPool() {
        return pool;
    }

    public ArrayList<Word> getVocab() {
        return vocab;
    }

    

    public String toString() {
        String string = "";
        for (int i = 0; i < pool.size(); i++) {
            string = string + pool.get(i).fileString() + "\n";
        }
        string = string + "#";
        for (int i = 0; i < vocab.size(); i++) {
            string = string + vocab.get(i).fileString() + "\n";
        }
        return string;
    }

    public int vocabSize() {
        return vocab.size();
    }

    public int poolSize() {
        return pool.size();
    }
    
    public int size() {
        return vocab.size() + pool.size();
    }

    public void readCustomFile(String location) {
        try {
            Scanner scan = new Scanner(new File(location));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String split[] = line.split("\\t");
                String eng = split[split.length - 1];
                String desc = "";
                Matcher m = Pattern.compile("\\((.*?)\\)").matcher(eng);
                if (m.find()) {
                    desc = m.group();
                    eng = eng.replaceAll(desc, "");
                    eng = eng.replaceAll("\\(", "");
                    eng = eng.replaceAll("\\)", "");
                    desc = desc.trim();
                }
                String jap = split[split.length - 2];
                addWord(jap, eng, desc);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void saveToFile(String name) {
        BufferedWriter writer = null;
        try {
            File logFile = new File(name);
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Always attempt to close writer
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    public void readSavedFile(String name) {
        try {
            Scanner scan = new Scanner(new File(name)).useDelimiter("#");
            String words = scan.next();
            String[] aWords = words.split("\n");
            for (String s : aWords) {
                String[] temp = s.split("/");
                addPoolWord(temp[0], temp[1], temp[2]);
            }
            words = scan.next();
            aWords = words.split("\n");
            for (String s : aWords) {
                String[] temp = s.split("/");
                addWord(temp[0], temp[1], temp[2]);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void clear() {
        vocab.removeAll(vocab);
        pool.removeAll(pool);
    }

    public void deleteWords(ArrayList<Word> givenWords) {
        vocab.removeAll(givenWords);
        pool.removeAll(givenWords);
    }

    public void moveToPool(ArrayList<Word> givenWords) {
        pool.addAll(givenWords);
        vocab.removeAll(givenWords);
    }

    public void moveToVocab(ArrayList<Word> givenWords) {
        vocab.addAll(givenWords);
        pool.removeAll(givenWords);
    }
}
