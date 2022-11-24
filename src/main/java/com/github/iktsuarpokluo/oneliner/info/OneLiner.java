package com.github.iktsuarpokluo.oneliner.info;

import com.intellij.ide.util.PropertiesComponent;

import java.io.*;
import java.util.ArrayList;

public class OneLiner {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
        PropertiesComponent properies = PropertiesComponent.getInstance();
        properies.setValue("com.github.iktsuarpokluo.oneliner.pageNumber", currentLine, 0);
    }

    private String url;
    private int currentLine;
    private ArrayList<String> lines;

    public OneLiner(String url, int lineNumber) {
        this.url = url;
        this.currentLine = lineNumber;
        this.lines = readFile(this.url);
    }

    public ArrayList<String> readFile(String url) {
        try{
            String fileName = url;
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            ArrayList<String> newLines = new ArrayList<>();

            String line;
            while((line = br.readLine()) != null){
                int start = 0;
                int oneLineLength = 40;
                while (start < line.length()){
                    int end = start + oneLineLength;
                    String newLine = line.substring(start, end > line.length() ? line.length() : end);
                    start+=oneLineLength;
                    newLines.add(newLine);
                }
            }
            br.close();

            return newLines;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            ArrayList<String> newLines = new ArrayList<>();
            newLines.add("请先选择文件");
            return newLines;
        }catch (IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public String getText(){
        return this.lines.get(this.currentLine);
    }

    public void pageUp(){
        if (this.currentLine > 0){
            setCurrentLine(this.currentLine - 1);
        }
    }

    public void pageDown(){
        if (this.currentLine < this.lines.size() - 1){
            setCurrentLine(this.currentLine + 1);
        }
    }

    @Override
    public String toString(){
        return "toString";
    }
}