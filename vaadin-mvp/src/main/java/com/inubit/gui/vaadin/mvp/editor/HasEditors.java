package com.inubit.gui.vaadin.mvp.editor;

import java.util.Collection;

/**
 * Exposes editors. Used by the Driver to interact with a view.
 *
 * @version $Id$
 */
public interface HasEditors extends Editor {

    Collection<? extends Editor> getEditors();
}
