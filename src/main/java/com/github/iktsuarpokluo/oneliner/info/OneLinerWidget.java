package com.github.iktsuarpokluo.oneliner.info;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.impl.status.TextPanel;
import com.intellij.ui.ClickListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class OneLinerWidget extends TextPanel implements CustomStatusBarWidget {
    private static final String ID = "OneLiner";
    private static OneLiner oneLiner;
    static {
        PropertiesComponent properies = PropertiesComponent.getInstance();
        String url = properies.getValue("com.github.iktsuarpokluo.oneliner.url", "/Users/bytedance/Documents/oneLiner.txt");
        int lineNumber = properies.getInt("com.github.iktsuarpokluo.oneliner.pageNumber", 0);
        oneLiner = new OneLiner(url, lineNumber);
    }

    public OneLinerWidget(){
        super();
        new ClickListener() {
            @Override
            public boolean onClick(@NotNull MouseEvent event, int clickCount) {
                //TODO: 弹窗更改配置
                return true;
            }
        }.installOn(this, true);
    }

    @Override
    @NotNull
    public String ID(){
        return ID;
    }

    @Override
    public void install(@NotNull StatusBar statusBar){
        updateUI();
    }

    @Override
    public JComponent getComponent(){
        return this;
    }

    @Override
    public void updateUI(){
        setText(oneLiner.getText());
    }

    @Override
    public void dispose(){}

    public void pageUp(){
        oneLiner.pageUp();
        updateUI();
    }

    public void pageDown(){
        oneLiner.pageDown();
        updateUI();
    }
}
