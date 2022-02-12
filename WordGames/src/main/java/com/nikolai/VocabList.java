package com.nikolai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VocabList {
    private List<String> vocab = new ArrayList<>();

    public VocabList () {
        File sourceFile = new File("src/main/resources/java-vocab.txt");
        if (!sourceFile.canRead() || !sourceFile.exists() || !sourceFile.isFile()) {
            System.out.println("File error.");
        }
        try (Scanner fileScanner = new Scanner(sourceFile)) {
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine();
                if (currentLine.length() > 0) {
                    vocab.add(currentLine);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (NoSuchElementException e) {
            System.out.println("No such element: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<String> getVocab () {
        return new ArrayList<>(vocab);
    }

}
