package com.inubit.gui.vaadin.mvp.editor;

import java.util.List;

/**
 * Editors that wish to be notified about ConstraintViolations in the value
 * being edited should implement this interface.
 *
 * @version $Id$
 */
public interface HasEditorErrors {

    void showErrors(List<EditorError> errors);
}
