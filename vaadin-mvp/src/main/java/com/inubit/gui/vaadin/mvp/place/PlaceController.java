package com.inubit.gui.vaadin.mvp.place;

import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.shell.ConfirmationHandler;
import com.inubit.gui.vaadin.mvp.shell.Shell;



/**
 * In charge of the user's location in the app.
 */
public class PlaceController {

    private final EventBus eventBus;

    private Shell shell;

    private Place where = Place.NOWHERE;

    public PlaceController(final EventBus eventBus, Shell shell) {
        this.eventBus = eventBus;
        this.shell = shell;
    }

    /**
     * Returns the current place.
     */
    public Place getWhere() {
        return where;
    }

    /**
     * Request a change to a new place.
     */
    public void goTo(final Place newPlace) {
        if (getWhere().equals(newPlace)) {
            return;
        }
        PlaceChangeRequestEvent willChange = new PlaceChangeRequestEvent(newPlace);
        eventBus.fireEvent(willChange);
        if (willChange.getWarning() != null) {
            shell.askForConfirmation(willChange.getWarning(), new ConfirmationHandler() {
                @Override
                public void onConfirm() {
                    goToWithoutChecks(newPlace);
                }

                @Override
                public void onCancel() {
                }
            });
        }
        else {
            goToWithoutChecks(newPlace);
        }

    }

    protected void goToWithoutChecks(final Place newPlace) {
        this.where = newPlace;
        eventBus.fireEvent(new PlaceChangeEvent(newPlace));
    }
}
