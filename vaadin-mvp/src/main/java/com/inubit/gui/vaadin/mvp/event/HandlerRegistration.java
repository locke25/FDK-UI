package com.inubit.gui.vaadin.mvp.event;

/**
 * Registration returned from a call to
 * {@link HandlerManager#addHandler(com.google.gwt.event.shared.GwtEvent.Type, EventHandler)} . Use
 * the handler registration to remove handlers when they are no longer needed.
 *
 * Inspired by {@link com.google.gwt.event.shared.HandlerRegistration}.
 */
public interface HandlerRegistration {

    /**
     * Removes the given handler from its manager.
     */
    void removeHandler();
}
