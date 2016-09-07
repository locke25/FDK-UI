package com.inubit.gui.vaadin.mvp.event;


/**
 * A marker interface for events used in the {@link EventBus}.
 * @param <H> The handler the events will be dispatched to.
 */
public interface Event<H extends EventHandler> {

    /**
     * Invokes the the handler and passes itself.
     */
    void dispatch(H handler);

}
