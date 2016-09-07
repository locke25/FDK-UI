package com.inubit.gui.vaadin.mvp.editor;

/**
 * Describes an error as reported to EditorDelegate.recordError().
 *
 * @version $Id$
 */
public interface EditorError {

    String getPath();

    Editor getEditor();

    String getMessage();

    Object getValue();

    boolean isConsumed();

    void setConsumed(boolean consumed);
}
