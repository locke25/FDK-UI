package com.inubit.gui.vaadin.mvp.editor;

/**
 * This is basically the presenter for an Editor.
 *
 * @version $Id$
 */
public interface EditorDelegate {

    /**
     * This method should be called from Editor.getValue() to record that there was an error.
     *
     * @param message a textual description of the editor
     * @param value   the current value that the error relates to
     */
    void recordError(String message, Object value);
}
