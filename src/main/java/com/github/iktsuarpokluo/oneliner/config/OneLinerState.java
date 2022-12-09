package com.github.iktsuarpokluo.oneliner.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "com.github.iktsuarpokluo.oneliner.config.OneLinerState",
        storages = @Storage("oneLinerSettingsPlugin.xml")
)
public class OneLinerState implements PersistentStateComponent<OneLinerState> {

    public String url = "/Users/bytedance/Documents/oneLiner.txt";
    public int currentLine = 0;
    public int lineRange = 0;

    public static OneLinerState getInstance() {
        return ApplicationManager.getApplication().getService(OneLinerState.class);
    }

    @Nullable
    @Override
    public OneLinerState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull OneLinerState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

}