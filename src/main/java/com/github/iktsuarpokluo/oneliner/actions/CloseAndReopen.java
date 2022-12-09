package com.github.iktsuarpokluo.oneliner.actions;

import com.github.iktsuarpokluo.oneliner.OneLinerFactory;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import com.intellij.openapi.wm.impl.status.widget.StatusBarWidgetSettings;
import com.intellij.openapi.wm.impl.status.widget.StatusBarWidgetsManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;

import java.util.Iterator;


public class CloseAndReopen extends DumbAwareAction{
    private static final String ID = "OneLiner";
    @Override
    public void update(@NotNull AnActionEvent event) {
        // Using the event, evaluate the context,
        // and enable or disable the action.
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event){
        Project currentProject = event.getProject();

        StatusBarWidgetSettings settings = ApplicationManager.getApplication().getService(StatusBarWidgetSettings.class);
        StatusBarWidgetsManager manager = currentProject.getService(StatusBarWidgetsManager.class);

        StatusBarWidgetFactory oneLinerFactory = null;
        Iterator<StatusBarWidgetFactory> iter = manager.getWidgetFactories().iterator();
        while (iter.hasNext()){
            StatusBarWidgetFactory factory = iter.next();
            if (factory.getId() == ID){
                oneLinerFactory = factory;
                break;
            }
        }
        settings.setEnabled(oneLinerFactory, !settings.isEnabled(oneLinerFactory));
        manager.updateWidget(oneLinerFactory);
    }

}