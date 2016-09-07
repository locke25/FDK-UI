package com.inubit.gui.vaadin.mvp.activity;

import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.view.ViewPort;



/**
 * Implemented by objects that control a piece of user interface, with a life cycle managed by an
 * {@link ActivityManager}, in response to
 * {@link com.inubit.gui.vaadin.mvp.place.PlaceChangeEvent} events as the user navigates through
 * the app.
 *
 * Inspired by {@link com.google.gwt.activity.shared.Activity}.
 */
public interface Activity {

    /**
     * Called when the Activity should ready its widget for the user. When the widget is ready it
     * should present it by calling {@link ViewPort#setView(com.vaadin.ui.Component)} on
     * the display.
     * <p>
     * Any eventHandlers attached to the provided event bus will be de-registered when the activity is
     * stopped.
     */
    void start(ViewPort viewPort, EventBus eventBus);

    /**
     * Called when the user is trying to navigate away from this activity.
     *
     * @return A message to display to the user, e.g. to warn of unsaved work, or null to say
     * nothing
     */
    String mayStop();

    /**
     * Called when the Activity's widget has been removed from view. All event eventHandlers it
     * registered will have been removed before this method is called.
     */
    void onStop();
    
    void update(Place place);
}
