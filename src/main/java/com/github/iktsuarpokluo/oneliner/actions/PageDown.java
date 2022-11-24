package com.github.iktsuarpokluo.oneliner.actions;

import com.github.iktsuarpokluo.oneliner.info.OneLinerWidget;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;

public class PageDown extends DumbAwareAction{
    private static final String ID = "OneLiner";
    @Override
    public void update(@NotNull AnActionEvent event) {
        // Using the event, evaluate the context,
        // and enable or disable the action.
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event){
        Project currentProject = event.getProject();
        StatusBar statusBar = WindowManager.getInstance().getStatusBar(currentProject);
        OneLinerWidget oneLiner = (OneLinerWidget) statusBar.getWidget(ID);
        oneLiner.pageDown();
    }

}