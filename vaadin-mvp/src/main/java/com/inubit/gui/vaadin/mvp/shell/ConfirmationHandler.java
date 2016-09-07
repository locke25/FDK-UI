package com.inubit.gui.vaadin.mvp.shell;

import com.inubit.gui.vaadin.mvp.event.EventHandler;


/**
 * Called after the user has answered a confirmation dialog issued by
 * {@link Shell#askForConfirmation(String, ConfirmationHandler)}.
 */
public interface ConfirmationHandler extends EventHandler {

    void onCancel();

    void onConfirm();

}
