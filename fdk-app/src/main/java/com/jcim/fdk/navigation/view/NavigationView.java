package com.jcim.fdk.navigation.view;

import com.inubit.gui.vaadin.mvp.view.View;
import com.jcim.fdk.navigation.place.NavigationPlace;

/**
 * UI component that displays the navigation on the left side in AdminCentral.
 *
 */
public interface NavigationView extends View {

    void setPresenter(Presenter presenter);

    /**
     * Presenter we have to inform about navigation events.
     */
    public static interface Presenter{
        void onMenuSelection(NavigationPlace place);
    }
    /**
     * Selects a menu item in the navigation based on its definition.
     */
    void select(NavigationPlace place);

}
