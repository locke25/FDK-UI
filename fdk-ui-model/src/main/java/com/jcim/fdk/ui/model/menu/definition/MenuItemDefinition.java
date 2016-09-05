package com.jcim.fdk.ui.model.menu.definition;

import com.jcim.fdk.ui.model.action.ActionDefinition;


/**
 * Menu item definition providing configuration for the label and icon. Also provides the
 * {@link ActionDefinition} executed when the item is clicked.
 */
public interface MenuItemDefinition {

    String getIcon();

    String getName();

    String getLabel();

    String getI18nBasename();

    String getDescription();

    public ActionDefinition getActionDefinition();

}
