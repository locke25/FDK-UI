package com.inubit.gui.vaadin.mvp.activity;

import com.inubit.gui.vaadin.mvp.place.Place;

/**
 * Simple Activity implementation that is always willing to stop.
 *
 */
public abstract class AbstractActivity implements Activity {

    @Override
    public String mayStop() {
        return null;
    }

    @Override
    public void onStop() {
    }

    @Override
    public void update(Place place) {
    }

}
