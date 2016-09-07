package com.inubit.gui.vaadin.mvp.event;

import java.util.HashSet;
import java.util.Set;

/**
 * Wraps an EventBus to hold on to any HandlerRegistrations, so that they can
 * easily all be cleared at once.
 * <p/>
 * Inspired by {@link com.google.gwt.event.shared.ResettableEventBus}.
 */
public class ResettableEventBus implements EventBus {

    private final EventBus wrapped;
    private final Set<HandlerRegistration> registrations = new HashSet<HandlerRegistration>();

    public ResettableEventBus(EventBus wrappedBus) {
        this.wrapped = wrappedBus;
    }

    @Override
    public synchronized <H extends EventHandler> HandlerRegistration addHandler(Class<? extends Event<H>> eventClass, H handler) {
        final HandlerRegistration registration = wrapped.addHandler(eventClass, handler);
        registrations.add(registration);
        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                synchronized (ResettableEventBus.this) {
                    registration.removeHandler();
                    registrations.remove(registration);
                }
            }
        };
    }

    @Override
    public <H extends EventHandler> void fireEvent(Event<H> event) {
        wrapped.fireEvent(event);
    }

    /**
     * Remove all handlers that have been added through this wrapper.
     */
    public synchronized void removeHandlers() {
        for (HandlerRegistration registration : registrations) {
            registration.removeHandler();
        }
        registrations.clear();
    }
}
