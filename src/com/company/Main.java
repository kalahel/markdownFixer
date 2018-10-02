package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static final String latexFilePath = "C:\\Users\\Mat\\Desktop\\markdownTest\\Start of the project.tex";
    public static final String captionFilePath = "C:\\Users\\Mat\\Desktop\\markdownTest\\src\\captions.txt";
    public static final String outputFileFolder = "C:\\Users\\Mat\\Desktop\\markdownTest\\src\\";

    public static void main(String[] args) {
        Parser parser = new Parser(latexFilePath, captionFilePath, outputFileFolder);
        try {
            parser.formatText();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void parserTester(Parser parser){
        try {
            ArrayList<String> strings = parser.loadLatexFile();
            for (String string :
                    strings) {
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
