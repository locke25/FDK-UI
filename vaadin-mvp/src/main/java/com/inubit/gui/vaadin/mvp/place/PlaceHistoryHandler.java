package com.inubit.gui.vaadin.mvp.place;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.event.HandlerRegistration;
import com.inubit.gui.vaadin.mvp.shell.FragmentChangedEvent;
import com.inubit.gui.vaadin.mvp.shell.FragmentChangedHandler;
import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;



/* FIXME should not implement a Vaadin specific interface
 An alternative
 * to using UriFragmentUtility would be to implement our own Vaadin widget (server and client sides)
 * to mimic GWT's {@link Historian}.
 * */
/**
 * Monitors {@link PlaceChangeEvent}s and browser's history events and keep them in sync.
 * <p>
 * Inspired by {@link com.google.gwt.place.shared.PlaceHistoryHandler}
 */
public class PlaceHistoryHandler implements FragmentChangedHandler {

    private static final Logger log = LoggerFactory.getLogger(PlaceHistoryHandler.class.getName());

    private final PlaceHistoryMapper mapper;

    private PlaceController placeController;

    private Place defaultPlace = Place.NOWHERE;
    
    private Shell shell;
    
    /**
     * Create a new PlaceHistoryHandler.
     *
     * @param mapper a {@link PlaceHistoryMapper} instance
     */
    public PlaceHistoryHandler(PlaceHistoryMapper mapper, Shell shell) {
        this.mapper = mapper;
        this.shell = shell;
    }

    /**
     * Handle the current history token. Typically called at application start, to ensure bookmark
     * launches work.
     */
    public void handleCurrentHistory() {
        String fragment = shell.getFragment();
        handleHistoryToken(StringUtils.defaultString(fragment));
    }

    @Override
    public void onFragmentChanged(FragmentChangedEvent event) {
        String token = event.getFragment();
        log.debug("fragmentChanged with token {}", token);
        handleHistoryToken(token);
    }

    /**
     * Initialize this place history handler.
     * @return
     *
     */
    public HandlerRegistration register(PlaceController placeController, EventBus eventBus, Place defaultPlace) {
        this.placeController = placeController;
        this.defaultPlace = defaultPlace;
        shell.addFragmentChangedHandler(this);

        return eventBus.addHandler(PlaceChangeEvent.class, new PlaceChangeEvent.Handler() {

            @Override
            public void onPlaceChange(PlaceChangeEvent event) {
                log.debug("onPlaceChange...");
                Place newPlace = event.getNewPlace();
                shell.setFragment(tokenForPlace(newPlace));
            }
        });
    }

    private void handleHistoryToken(String token) {

        Place newPlace = null;

        if ("".equals(token)) {
            newPlace = defaultPlace;
        }

        if (newPlace == null) {
            newPlace = mapper.getPlace(token);
        }

        if (newPlace == null) {
            log.warn("Unrecognized history token: {}, falling back to default place...", token);
            newPlace = defaultPlace;
        }
        log.debug("handleHistoryToken with place {}", newPlace);
        placeController.goTo(newPlace);
    }

    private String tokenForPlace(Place newPlace) {
        // FIXME if uncommented the URI fragment won't be written
        /*if (defaultPlace.equals(newPlace)) {
            return "";
        }*/

        String token = mapper.getToken(newPlace);
        if (token != null) {
            log.debug("tokenForPlace returns token [{}]", token);
            return token;
        }

        log.debug("Place not mapped to a token: {}", newPlace);
        return "";
    }

}
