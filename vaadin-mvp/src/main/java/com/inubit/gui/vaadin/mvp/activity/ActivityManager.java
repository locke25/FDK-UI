package com.inubit.gui.vaadin.mvp.activity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.event.ResettableEventBus;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceChangeEvent;
import com.inubit.gui.vaadin.mvp.place.PlaceChangeRequestEvent;
import com.inubit.gui.vaadin.mvp.view.ViewPort;

/**
 * Manages {@link Activity} objects that should be kicked off in response to
 * {@link PlaceChangeEvent} events. Each activity provides a widget to be shown when it's ready to run.
 *
 * Inspired by {@link com.google.gwt.activity.shared.ActivityManager}.
 */
public class ActivityManager implements PlaceChangeEvent.Handler, PlaceChangeRequestEvent.Handler {

    private static Logger log = LoggerFactory.getLogger(ActivityManager.class);

    private static final Activity NULL_ACTIVITY = new AbstractActivity() {
        @Override
        public void start(ViewPort viewPort, EventBus eventBus) {
            viewPort.setView(null);
        }
        @Override
        public String toString() {
            return "NULL_ACTIVITY";
        }
    };

    private final ActivityMapper mapper;
    private final ResettableEventBus isolatedEventBus;

    private Activity currentActivity = NULL_ACTIVITY;

    private ViewPort viewPort;

    public ActivityManager(ActivityMapper mapper, EventBus eventBus) {
        log.debug("Create ActivityManager for ActivityMapper {} and EventBus {}", mapper, eventBus);
        this.mapper = mapper;
        this.isolatedEventBus = new ResettableEventBus(eventBus);

        eventBus.addHandler(PlaceChangeEvent.class, this);
        eventBus.addHandler(PlaceChangeRequestEvent.class, this);
    }

    @Override
    public void onPlaceChange(PlaceChangeEvent event) {
        Place newPlace = event.getNewPlace();
        Activity nextActivity = mapper.getActivity(newPlace);


        if (currentActivity.equals(nextActivity)) {
        	currentActivity.update(newPlace);
            return;
        }

        // TODO is it really necessery to set the view to null. I think in GWT this is done to prevent event handling
        // after an activity has been stopped. but this is not going to happen in vaadin.
        viewPort.setView(null);
        isolatedEventBus.removeHandlers();

        currentActivity.onStop();

        if(nextActivity == null){
            nextActivity = NULL_ACTIVITY;
        }

        currentActivity = nextActivity;

        log.debug("starting activity: {}", currentActivity);

        currentActivity.start(viewPort, isolatedEventBus);

   }

    public void setViewPort(ViewPort viewPort){
        this.viewPort = viewPort;
    }
    
    public Activity getCurrentActivity() {
    	return currentActivity;
    }

    @Override
    public void onPlaceChangeRequest(final PlaceChangeRequestEvent event) {
        log.debug("onPlaceChangeRequest for event {}", event);
        if (!currentActivity.equals(NULL_ACTIVITY)) {
            final String message = currentActivity.mayStop();
            if(message != null) {
                event.setWarning(message);
            }
        }
    }
}
