package com.github.iktsuarpokluo.oneliner.config;

import com.github.iktsuarpokluo.oneliner.info.OneLinerWidget;
import com.intellij.ide.actions.SettingsEntryPointAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.impl.status.widget.StatusBarWidgetSettings;
import com.intellij.openapi.wm.impl.status.widget.StatusBarWidgetsManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Iterator;

public class OneLinerSettingsConfigurable implements Configurable {

    private OneLinerSettings mySettingsComponent;
    private static final String ID = "OneLiner";


    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "OneLiner Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return mySettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mySettingsComponent = new OneLinerSettings();
        return mySettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        OneLinerState settings = OneLinerState.getInstance();
        boolean modified = !mySettingsComponent.getFileUrl().equals(settings.url);
        modified |= (mySettingsComponent.getCurrentLine() != settings.currentLine);
        return modified;
    }

    @Override
    public void apply() {
        OneLinerState settings = OneLinerState.getInstance();
        settings.url = mySettingsComponent.getFileUrl();
        settings.currentLine = mySettingsComponent.getCurrentLine();

        Project[] projects = ProjectManager.getInstance().getOpenProjects();
        for (Project project : projects) {
            StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
            OneLinerWidget oneLiner = (OneLinerWidget) statusBar.getWidget(ID);
            oneLiner.updateUI();
        }
    }

    @Override
    public void reset() {
        OneLinerState settings = OneLinerState.getInstance();
        mySettingsComponent.setFileUrl(settings.url);
        mySettingsComponent.setCurrentLine(settings.currentLine + 1);
        mySettingsComponent.setNumberRange(settings.lineRange);
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }

}