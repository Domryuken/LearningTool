/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Words;
import Model.Word;
import View.MainUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.UIManager;

/**
 *
 * @author Domryuken
 */
public class Controller {

    private ArrayList<Word> questions;
    private Words words;
    private MainUI ui;
    private int number = 0;
    

    public Controller(MainUI givenUi) {
        words = new Words();
        words.readSavedFile("SavedWords");
        ui = givenUi;
        ui.addStartListener(new StartListener());
//        ui.addQuestionListener(new QuestionsListener());
        ui.addDeleteVocabListener(new DeleteVocabListener());
        ui.addDeletePoolListener(new DeletePoolListener());
        ui.addUnpoolListener(new UnpoolListener());
        ui.addPoolListener(new PoolListener());
        ui.addAddListener(new AddListener());
        ui.addSaveListener(new SaveListener());
        ui.addLoadListener(new LoadListener());
        ui.addLoadCustomListener(new LoadCustomListener());
        ui.setModels(words);
        ui.setVisible(true);
    }

    class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            questions = words.readyCurrentWords();
            if(questions.isEmpty()){
                ui.noWords();
            }else{
                ui.setQuestions(questions);
            }
            
        }

    }

    class QuestionsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
        }

//        private void getAnswer() {
//            if (number < words.poolSize()) {
//                String answer = ui.getAnswerText();
//                String response = "Answer to " + words.getWord(number).getEnglish() + " is " + words.getWord(number).getJapanese();
//                boolean correct = words.getWord(number).answer(answer);
//                words.getWord(number).setTime();
//                if (correct) {
//                    ui.setQuestionText("CORRECT!\n" + response);
//                } else {
//                    ui.setQuestionText("WRONG!\n" + response);
//                }
//                number++;
//            }
//        }
//
//        private boolean setQuestion() {
//            while (true) {
//                if (number < words.poolSize()) {
//                    if (words.getWord(number).timeUp()) {
//                        ui.setQuestionText(words.getWord(number).question());
//                        return true;
//                    } else {
//                        number++;
//                    }
//                } else {
//                    ui.setQuestionText("NO WORDS!");
//                    number = 0;
//                    return false;
//                }
//            }
//        }
    }

    class DeleteVocabListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            words.deleteWords(ui.getVocabWords());
            ui.setModels(words);
        }
    }

    class DeletePoolListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            words.deleteWords(ui.getPoolWords());
            ui.setModels(words);
        }
    }

    class PoolListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            words.moveToPool(ui.getVocabWords());
            ui.setModels(words);
        }
    }

    class UnpoolListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            words.moveToVocab(ui.getPoolWords());
            ui.setModels(words);
        }
    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean added = words.addWord(ui.getAddedWord());
            ui.switchLabel(added);
            ui.setModels(words);
        }
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            words.saveToFile(ui.getSaveLoad());
        }
    }

    class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            words.readSavedFile(ui.getSaveLoad());
            ui.setModels(words);
        }
    }

    class LoadCustomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            words.readCustomFile(ui.getLoadCustom());
            ui.setModels(words);
        }
    }
}
