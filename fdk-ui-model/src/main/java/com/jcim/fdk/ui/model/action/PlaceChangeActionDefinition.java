package com.jcim.fdk.ui.model.action;

import com.inubit.gui.vaadin.mvp.place.Place;

/**
 * Place change action definition.
 *
 */
public interface PlaceChangeActionDefinition extends ActionDefinition {

    Place getPlace();
}
