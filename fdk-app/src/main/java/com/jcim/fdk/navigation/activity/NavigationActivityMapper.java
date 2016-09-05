package com.jcim.fdk.navigation.activity;

import com.inubit.gui.vaadin.mvp.activity.Activity;
import com.inubit.gui.vaadin.mvp.activity.ActivityMapper;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.jcim.fdk.navigation.place.NavigationPlace;

/**
 * Returns a {@link NavigationActivity} for a given place.
 *
 */
public class NavigationActivityMapper implements ActivityMapper {

    private NavigationActivity navigationActivity;

    public NavigationActivityMapper(PlaceController placeController, NavigationPlace[] places) {
        this.navigationActivity = new NavigationActivity(placeController, places);
    }

    @Override
    public Activity getActivity(Place place) {
        navigationActivity.update((NavigationPlace)place);
        return navigationActivity;
    }

}
