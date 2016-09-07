package com.inubit.gui.vaadin.mvp.place;

/**
 * Implemented by objects responsible for text serialization and deserialization
 * of Place objects.
 * <p>
 * Copied verbatim from {@link com.google.gwt.place.shared.PlaceTokenizer}
 *
 * @param <P>
 */
public interface PlaceTokenizer<P extends Place> {
  P getPlace(String token);

  String getToken(P place);
}
