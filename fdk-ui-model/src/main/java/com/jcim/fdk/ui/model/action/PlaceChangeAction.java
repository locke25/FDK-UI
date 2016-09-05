package com.jcim.fdk.ui.model.action;

import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceController;

/**
 * Implements a place change action.
 *
 */
public class PlaceChangeAction extends ActionBase<PlaceChangeActionDefinition>  {

    private PlaceController placeController;

    public PlaceChangeAction(final PlaceChangeActionDefinition definition, final PlaceController placeController) {
        super(definition);
        this.placeController = placeController;
    }

    @Override
    public void execute() {
        Place newPlace = getDefinition().getPlace();
        placeController.goTo(newPlace);
    }

    protected PlaceController getPlaceController() {
        return placeController;
    }

}
