package com.inubit.gui.vaadin.mvp.event;

/**
 * Will dispatch fired {@link Event}s to the {@link EventHandler}s.
 */
public interface EventBus  {

    <H extends EventHandler> HandlerRegistration addHandler(Class<? extends Event<H>> eventClass, H handler);

    <H extends EventHandler> void fireEvent(Event<H> event);

}
