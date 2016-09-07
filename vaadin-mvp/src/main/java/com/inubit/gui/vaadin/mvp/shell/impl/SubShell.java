package com.inubit.gui.vaadin.mvp.shell.impl;

import com.inubit.gui.vaadin.mvp.activity.AbstractMVPSubContainer;
import com.inubit.gui.vaadin.mvp.shell.ConfirmationHandler;
import com.inubit.gui.vaadin.mvp.shell.Shell;


/**
 * A shell working only with a sub fragment of the URL fragment. Used to build sub containers by using {@link AbstractMVPSubContainer}.
 *
 */
@SuppressWarnings("serial")
public class SubShell extends AbstractShell {

    private Shell parent;

    public SubShell(String id, Shell parent) {
        super(id);
        this.parent = parent;
    }

    @Override
    public void askForConfirmation(String message, ConfirmationHandler listener) {
        parent.askForConfirmation(message, listener);
    }

    @Override
    public void showNotification(String message) {
        parent.showNotification(message);
    }

    @Override
    public void showError(String message, Exception e) {
        parent.showError(message, e);
    }

   /* @Override
    public void openWindow(String uri, String windowName) {
        parent.openWindow(uri, windowName);
    }*/
}
