package com.inubit.gui.vaadin.mvp.place;

/**
 * Maps {@link Place}s to/from tokens, used to configure a
 * {@link PlaceHistoryHandler}.
 * <p>
 * Copied verbatim from {@link com.google.gwt.place.shared.PlaceHistoryMapper}
 */
public interface PlaceHistoryMapper {

  Place getPlace(String token);

  String getToken(Place place);
}
