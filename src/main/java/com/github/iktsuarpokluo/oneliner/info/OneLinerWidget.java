package com.github.iktsuarpokluo.oneliner.info;

import com.github.iktsuarpokluo.oneliner.config.OneLinerState;
import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.impl.status.TextPanel;
import com.intellij.ui.ClickListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class OneLinerWidget extends TextPanel implements CustomStatusBarWidget {
    private static final String ID = "OneLiner";
    private static OneLiner oneLiner;
    static {
        oneLiner = new OneLiner();
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
