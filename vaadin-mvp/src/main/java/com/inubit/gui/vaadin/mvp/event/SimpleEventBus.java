package com.inubit.gui.vaadin.mvp.event;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * A very simplistic event bus.
 * Check event bus project: http://www.eventbus.org/
 */
public class SimpleEventBus implements EventBus {

    private static final Logger log = LoggerFactory.getLogger(SimpleEventBus.class);

    private final Multimap<Class<? extends Event>, EventHandler> eventHandlers = ArrayListMultimap.create();

    @Override
    public <H extends EventHandler> HandlerRegistration addHandler(final Class<? extends Event<H>> eventClass, final H handler) {
        log.debug("Adding handler {} for events of class {}", handler, eventClass);
        internalAddHandler(eventClass, handler);
        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                internalRemoveHandler(eventClass, handler);
            }
        };
    }

    @Override
    public <H extends EventHandler> void fireEvent(Event<H> event) {
        for (H eventHandler : internalGetHandlers(event)) {
            log.debug("Dispatch event {} with handler {}", event, eventHandler);
            event.dispatch(eventHandler);
        }
    }

    // Internal atomic operations

    private synchronized <H extends EventHandler> void internalAddHandler(Class<? extends Event<H>> eventClass, H handler) {
        if (!eventHandlers.containsEntry(eventClass, handler)) {
            eventHandlers.put(eventClass, handler);
        }
    }

    @SuppressWarnings("unchecked")
    protected synchronized <H extends EventHandler> Collection<H> internalGetHandlers(Event<H> event) {
        return new ArrayList<H>((Collection<H>) eventHandlers.get(event.getClass()));
    }

    private synchronized <H extends EventHandler> void internalRemoveHandler(Class<? extends Event<H>> eventClass, H handler) {
        eventHandlers.remove(eventClass, handler);
    }
}
