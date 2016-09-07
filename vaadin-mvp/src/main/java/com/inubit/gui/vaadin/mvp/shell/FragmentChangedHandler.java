package com.inubit.gui.vaadin.mvp.shell;

import com.inubit.gui.vaadin.mvp.event.EventHandler;


/**
 * Called if a URI fragment of {@link Shell} has changed.
 */
public interface FragmentChangedHandler extends EventHandler {

    void onFragmentChanged(FragmentChangedEvent event);

}
