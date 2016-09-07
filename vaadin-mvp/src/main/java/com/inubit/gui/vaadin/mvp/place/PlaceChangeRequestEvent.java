package com.inubit.gui.vaadin.mvp.place;

import com.inubit.gui.vaadin.mvp.event.Event;
import com.inubit.gui.vaadin.mvp.event.EventHandler;


/**
 * Event thrown when the user may go to a new place in the app, or tries to leave it. Receivers can
 * call {@link #setWarning(String)} request that the user be prompted to confirm the change.
 */
public class PlaceChangeRequestEvent implements Event<PlaceChangeRequestEvent.Handler> {

    /**
     * Listens to {@link PlaceChangeRequestEvent}s.
     */
    public interface Handler extends EventHandler{

        void onPlaceChangeRequest(PlaceChangeRequestEvent event);

    }

    private String warning;

    private final Place newPlace;

    public PlaceChangeRequestEvent(Place newPlace) {
        this.newPlace = newPlace;
    }

    /**
     * Returns the place we may navigate to, or null on window close.
     */
    public Place getNewPlace() {
        return newPlace;
    }

    /**
     * Returns the warning message to show the user before allowing the place change, or null if
     * none has been set.
     */
    public String getWarning() {
        return warning;
    }

    /**
     * Set a message to warn the user that it might be unwise to navigate away from the current
     * place, i.e. due to unsaved changes. If the user clicks okay to that message, navigation will
     * proceed to the requested place.
     * <p>
     * Calling with a null warning is the same as not calling the method at all -- the user will not
     * be prompted.
     * <p>
     * Only the first non-null call to setWarning has any effect. That is, once the warning message
     * has been set it cannot be cleared.
     */
    public void setWarning(String warning) {
        if (this.warning == null) {
            this.warning = warning;
        }
    }

    @Override
    public void dispatch(Handler handler) {
        handler.onPlaceChangeRequest(this);
    }

}
