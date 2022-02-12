package com.nikolai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VocabList {
    List<String> vocab = new ArrayList<>();

    public VocabList () {
        File sourceFile = new File("resources/java-vocab.txt");
        try (Scanner fileScanner = new Scanner(sourceFile)) {
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

}
