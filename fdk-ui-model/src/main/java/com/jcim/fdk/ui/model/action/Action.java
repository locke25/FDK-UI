package com.jcim.fdk.ui.model.action;


/**
 * An action encapsulates the logic for some UI behavior, e.g. clicking on an app menu item would trigger an action which goes to a place associated with it.
 * An action is bound to an {@link ActionDefinition}.
 *
 */
public interface Action {

    void execute() throws ActionExecutionException;
}
