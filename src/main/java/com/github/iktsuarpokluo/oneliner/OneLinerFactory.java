package com.github.iktsuarpokluo.oneliner;

import com.github.iktsuarpokluo.oneliner.info.OneLiner;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.ui.ClickListener;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.ui.UIBundle;
import com.intellij.util.concurrency.EdtExecutorService;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.ui.update.Activatable;
import com.intellij.util.ui.update.UiNotifyConnector;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.wm.StatusBarWidgetFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBarWidget;

import static com.intellij.openapi.util.io.FileUtilRt.MEGABYTE;
import com.intellij.openapi.wm.impl.status.TextPanel;

public class OneLinerFactory implements StatusBarWidgetFactory{
    private static final String ID = "OneLiner";
    private static final OneLiner oneLiner = new OneLiner();

    @Override
    @NotNull
    public String getId(){
        return ID;
    }

    @Override
    @NotNull
    public String getDisplayName(){
        return "One Liner";
    }

    @Override
    public boolean isAvailable(@NotNull Project project){
        return true;
    }

    @Override
    @NotNull
    public StatusBarWidget createWidget(@NotNull Project project){
        return new BuildOneLinerWidget();
    }

    @Override
    public boolean canBeEnabledOn(@NotNull StatusBar statusBar){
        return true;
    }

    @Override
    public void disposeWidget(@NotNull StatusBarWidget widget){

    }

    private static class BuildOneLinerWidget extends TextPanel implements CustomStatusBarWidget {

        BuildOneLinerWidget(){
            super();
            new ClickListener() {
                @Override
                public boolean onClick(@NotNull MouseEvent event, int clickCount) {
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
            setText(oneLiner.toString());
        }

        @Override
        public JComponent getComponent(){
            return this;
        }

        @Override
        public void dispose(){}
    }
}
