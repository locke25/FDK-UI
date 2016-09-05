package com.jcim.fdk.ui.model.menu.definition;

import com.jcim.fdk.ui.model.action.ActionDefinition;



/**
 * Simple implementation of {@link MenuItemDefinition}.
 */
public class MenuItemDefinitionImpl implements MenuItemDefinition {

    private String i18nBasename;

    private String name;

    private String label;

    private String icon;

    private String description;

    private ActionDefinition actionDefinition;

    public void setLabel(String label) {
        this.label = label;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public ActionDefinition getActionDefinition() {
        return actionDefinition;
    }

    public void setActionDefinition(ActionDefinition actionDefinition) {
        this.actionDefinition = actionDefinition;
    }


    @Override
    public String getI18nBasename() {
        return i18nBasename;
    }

    public void setI18nBasename(String i18nBasename) {
        this.i18nBasename = i18nBasename;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
