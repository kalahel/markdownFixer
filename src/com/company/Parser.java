package com.company;

import java.io.*;
import java.util.ArrayList;

public class Parser {
    private FileReader latexFileReader;
    private BufferedReader latexBufferedReader;
    private FileReader captionFileReader;
    private BufferedReader captionBufferedReader;
    private String latexFilePath;
    private String outputFolderPath;


    public Parser(String latexFilePath, String captionFileName, String outputFolderPath) {
        this.latexFilePath = latexFilePath;
        this.outputFolderPath = outputFolderPath;
        try {
            this.latexFileReader = new FileReader(latexFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.latexBufferedReader = new BufferedReader(this.latexFileReader);

        try {
            this.captionFileReader = new FileReader(captionFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.captionBufferedReader = new BufferedReader(this.captionFileReader);

    }

    public void formatText() throws IOException {
        // TODO RESTRUCTURE TO MAKE THIS SELF CLEANING, MUST ADD THE OPENING OF THE STREAM HERE
        String currentCaptionLine = null;
        ArrayList<String> loadedLatexFile = this.loadLatexFile();
        int index = 0;

        while ((currentCaptionLine = this.captionBufferedReader.readLine()) != null) {
            while (!loadedLatexFile.get(index).contains("\\caption")) {
                index++;
            }
            // "\caption" has been found
            loadedLatexFile.set(index, "\\caption{" + currentCaptionLine + "}");
        }
        captionBufferedReader.close();
        captionFileReader.close();
        this.createLatexText(loadedLatexFile);
    }

    public ArrayList<String> loadLatexFile() throws IOException {
        ArrayList<String> loadedLatexString = new ArrayList<>();
        String currentLatexLine = null;

        while ((currentLatexLine = this.latexBufferedReader.readLine()) != null) {
            loadedLatexString.add(currentLatexLine);
        }
        this.latexBufferedReader.close();
        this.latexFileReader.close();
        return loadedLatexString;
    }

    public void createLatexText(ArrayList<String> modifiedLatexFile) throws IOException {
        File outputFile = new File(this.outputFolderPath + "outputFile.tex");
        FileWriter fileWriter = new FileWriter(outputFile);
        outputFile.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (String string :
                modifiedLatexFile) {
         bufferedWriter.write(string);
         bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
    }


}
