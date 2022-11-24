package com.github.iktsuarpokluo.oneliner;

import com.github.iktsuarpokluo.oneliner.info.OneLinerWidget;
import com.intellij.openapi.wm.StatusBar;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBarWidget;

public class OneLinerFactory implements StatusBarWidgetFactory{
    private static final String ID = "OneLiner";


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
        return new OneLinerWidget();
    }

    @Override
    public boolean canBeEnabledOn(@NotNull StatusBar statusBar){
        return true;
    }

    @Override
    public void disposeWidget(@NotNull StatusBarWidget widget){

    }
}
