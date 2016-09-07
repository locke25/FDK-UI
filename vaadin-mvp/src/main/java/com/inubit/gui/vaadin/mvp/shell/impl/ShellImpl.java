package com.inubit.gui.vaadin.mvp.shell.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;

import com.inubit.gui.vaadin.mvp.shell.ConfirmationHandler;
import com.inubit.gui.vaadin.mvp.view.impl.MainWindow;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Notification;


/**
 * Wraps the Vaadin application.
 *
 */
public class ShellImpl extends AbstractShell {

    private static Logger log = LoggerFactory.getLogger(ShellImpl.class);

    private static final long serialVersionUID = 5700621522722171068L;

    private static final String APPLICATION_FRAGMENT_ID = "app";

    private MainWindow mainWindow;

    public ShellImpl(MainWindow mainWindow) {
        super(APPLICATION_FRAGMENT_ID);
        this.mainWindow = mainWindow;
    }

    @Override
    public void askForConfirmation(String message, final ConfirmationHandler listener) {
         
    	/*
    	 * FIXME
    	 */
    	listener.onConfirm();
    	
    	/*ConfirmDialog.show(mainWindow.getMainWindow(), message, new ConfirmDialog.Listener() {

            @Override
            public void onClose(ConfirmDialog dialog) {
                if (dialog.isConfirmed()) {
                    listener.onConfirm();
                } else {
                    listener.onCancel();
                }
            }
        });*/
    }

    @Override
    public void showNotification(String message) {
    	Notification.show(message);
    }

    @Override
    public void showError(String message, Exception e) {
        log.error(message, e);
        Notification.show(message, e.getMessage(), Notification.Type.ERROR_MESSAGE);
    }

    /*@Override
    public void openWindow(String uri, String windowName) {
        mainWindow.getMainWindow().open(new ExternalResource(uri), windowName);
    }*/
}
