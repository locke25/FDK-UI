package com.inubit.gui.vaadin.mvp.editor;

/**
 * Indicates that an editor requires an EditorDelegate.
 *
 * @version $Id$
 */
public interface HasEditorDelegate extends Editor {

    void setDelegate(EditorDelegate delegate);
}
