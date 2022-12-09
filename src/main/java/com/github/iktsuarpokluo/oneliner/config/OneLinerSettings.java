package com.github.iktsuarpokluo.oneliner.config;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.*;

public class OneLinerSettings {
    private final JPanel myMainPanel;
    private final JBTextField fileUrl = new JBTextField();
    private final JBTextField currentLine = new JBTextField();
    private final JBTextField numberRange = new JBTextField();

    public OneLinerSettings() {
        JPanel fileChoosePanel = new JPanel(new GridBagLayout());
        JButton chooseButton = new JButton("...");
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(fileUrl.getText());
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.showOpenDialog(myMainPanel);
                File file = fileChooser.getSelectedFile();
                fileUrl.setText( file.getPath());
            }
        });

        fileUrl.setEditable(false);
        fileChoosePanel.add(fileUrl, new GridBagConstraints(0, 0, 1, 0, 1, 0, WEST, BOTH, JBUI.emptyInsets(), 0, 0));
        fileChoosePanel.add(chooseButton, new GridBagConstraints(1, 0, 0, 0, 0, 0, WEST, BOTH, JBUI.emptyInsets(), 0, 0));


        JPanel linePanel = new JPanel(new GridBagLayout());
        numberRange.setEditable(false);
        JPanel rangeTips = new JPanel();
        rangeTips.add(new JLabel("共"));
        rangeTips.add(numberRange);
        rangeTips.add(new JLabel("行"));
        currentLine.setDocument(new NumberDocument());

        linePanel.add(currentLine, new GridBagConstraints(0, 0, 1, 0, 0.2, 0, WEST, BOTH, JBUI.emptyInsets(), 0, 0));
        linePanel.add(rangeTips, new GridBagConstraints(1, 0, 0, 0, 0, 0, WEST, BOTH, JBUI.emptyInsets(), 0, 0));

        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent("选择文件: ", fileChoosePanel)
                .addLabeledComponent("跳转行数: ", linePanel)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return fileUrl;
    }

    @NotNull
    public String getFileUrl() {
        return fileUrl.getText();
    }

    public void setFileUrl(@NotNull String newUrl) {
        fileUrl.setText(newUrl);
    }

    public int getCurrentLine() {
        return Integer.parseInt(currentLine.getText().trim()) - 1;
    }

    public void setCurrentLine(int newLine) {
        currentLine.setText(String.valueOf(newLine));
    }

    public int getNumberRange() {
        return Integer.parseInt(numberRange.getText().trim());
    }

    public void setNumberRange(int number) {
        numberRange.setText(String.valueOf(number));
    }

}
