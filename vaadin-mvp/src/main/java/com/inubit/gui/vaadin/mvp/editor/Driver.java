package com.inubit.gui.vaadin.mvp.editor;

import java.util.List;

/**
 * Automates editing of entities defined by the content model.
 *
 * @param <T> the type of entity to edit
 * @version $Id$
 */
public interface Driver<T> {

    void initialize(HasEditors editors);

    /**
     * Push the data in an object into the dialog prepared by the initialize() method.
     *
     * @param object
     */
    void edit(T object);

    /**
     * Update the object with values from the dialog.
     *
     * @param object
     */
    void flush(T object);

    /**
     * Indicates if the last call to flush() resulted in any errors.
     *
     * @return
     */
    boolean hasErrors();

    /**
     * Returns any unconsumed(?) errors from the last call to flush().
     *
     * @return
     */
    List<EditorError> getErrors();
}
