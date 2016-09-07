package com.inubit.gui.vaadin.mvp.activity;

import com.inubit.gui.vaadin.mvp.place.Place;

/**
 * Finds the activity to run for a given {@link Place}, used to configure
 * an {@link ActivityManager}.
 */
public interface ActivityMapper {

    /**
     * Returns the activity to run for the given {@link Place}.
     *
     * @param place a Place object
     */
    Activity getActivity(Place place);

}
