package com.nikolai;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        VocabList vocabList = new VocabList();
        List<String> vocab = vocabList.getVocab();
        for (String word : vocab) {
            System.out.println(word);
        }
    }
}
