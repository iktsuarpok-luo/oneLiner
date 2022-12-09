package com.github.iktsuarpokluo.oneliner.info;

import com.github.iktsuarpokluo.oneliner.config.OneLinerState;
import com.intellij.ide.util.PropertiesComponent;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class OneLiner {
    private String url;
    private ArrayList<String> lines;

    public OneLiner() {
        OneLinerState settings = OneLinerState.getInstance();
        this.lines = readFile(settings.url);
        this.url = settings.url;
        settings.lineRange = this.lines.size();
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
        OneLinerState settings = OneLinerState.getInstance();
        if (!settings.url.equals(this.url)){
            this.lines = readFile(settings.url);
            this.url = settings.url;
            settings.lineRange = this.lines.size();
        }

        float percent = (settings.currentLine + 1) / this.lines.size() * 100;
        DecimalFormat decimalFormat = new DecimalFormat( "0.00" );
        String p = decimalFormat.format(percent);
        return this.lines.get(Math.min(settings.currentLine, this.lines.size() - 1)) + " " + p + "%";
    }

    public void pageUp(){
        OneLinerState settings = OneLinerState.getInstance();
        if (settings.currentLine > 0){
            settings.currentLine = settings.currentLine - 1;
        }
    }

    public void pageDown(){
        OneLinerState settings = OneLinerState.getInstance();
        if (settings.currentLine < this.lines.size() - 1){
            settings.currentLine = settings.currentLine + 1;
        }
    }

    @Override
    public String toString(){
        return "toString";
    }
}