package com.inubit.gui.vaadin.mvp.editor;

/**
 * Editor for editing a value.
 *
 * @param <T> type of the editable value.
 * @version $Id$
 */
public interface ValueEditor<T> extends HasEditorPath {

    /**
     * Sets the value.
     *
     * @param object
     */
    void setValue(T object);

    /**
     * Gets the current value.
     *
     * @return the value currently held by the editor.
     */
    T getValue();

    Class<T> getType();
}
