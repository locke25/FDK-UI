package com.jcim.fdk.navigation.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inubit.gui.vaadin.mvp.activity.AbstractActivity;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.jcim.fdk.navigation.place.NavigationPlace;
import com.jcim.fdk.navigation.view.NavigationView;
import com.jcim.fdk.navigation.view.NavigationViewImpl;
import com.vaadin.ui.Notification;

/**
 * Navigation activity.
 *
 */
public class NavigationActivity extends AbstractActivity implements NavigationView.Presenter {

    private static final Logger log = LoggerFactory.getLogger(NavigationActivity.class);
    private NavigationView view;
    private PlaceController placeController;

    public NavigationActivity(PlaceController placeController, NavigationPlace[] places) {
        this.placeController = placeController;
        this.view = new NavigationViewImpl(places);
        view.setPresenter(this);
    }

    @Override
    public void start(ViewPort viewPort, EventBus eventBus) {
        viewPort.setView(view);
    }

    @Override
    public void onMenuSelection(NavigationPlace place) {
        if(place != null){
        	placeController.goTo(place.asPlace());
        }else{
            Notification.show("No action defined");
        }
    }

    public void update(NavigationPlace place) {
        view.select(place);
    }

}