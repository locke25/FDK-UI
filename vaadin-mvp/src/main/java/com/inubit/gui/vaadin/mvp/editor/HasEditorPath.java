package com.inubit.gui.vaadin.mvp.editor;

/**
 * Implemented by editors that provide a path for what their editing.
 *
 * @version $Id$
 */
public interface HasEditorPath extends Editor {

    String getPath();
}
