package com.inubit.gui.vaadin.mvp.shell;

import com.inubit.gui.vaadin.mvp.event.Event;



/**
 * Fired if a URI fragment has changed.
 */
public class FragmentChangedEvent implements Event<FragmentChangedHandler> {
    private String fragment;

    public FragmentChangedEvent(String fragment) {
        this.fragment = fragment;
    }

    public String getFragment() {
        return fragment;
    }

    @Override
    public void dispatch(FragmentChangedHandler handler) {
        handler.onFragmentChanged(this);
    }

}
