package com.inubit.gui.vaadin.mvp.place;

import com.inubit.gui.vaadin.mvp.event.Event;
import com.inubit.gui.vaadin.mvp.event.EventHandler;


/**
 * Fired on UI navigation.
 */
public class PlaceChangeEvent implements Event<PlaceChangeEvent.Handler> {

    /**
     * Listens to {@link PlaceChangeEvent}s.
     */
    public interface Handler extends EventHandler {
        void onPlaceChange(PlaceChangeEvent event);
    }

    private final Place newPlace;

    public PlaceChangeEvent(Place newPlace) {
      this.newPlace = newPlace;
    }

    public Place getNewPlace() {
      return newPlace;
    }

    @Override
    public void dispatch(Handler handler) {
        handler.onPlaceChange(this);
    }

}
