package com.inubit.gui.vaadin.mvp.place;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the prefix to use when the token written by
 * {@link PlaceTokenizer#getToken(Place)} is written to
 * {@link UriFragmentUtility#setFragment(String)}.
 * Implementations of {@link AbstractPlaceHistoryMapper} can look
 * for this annotation to retrieve the associated PlaceTokenizer.
 * <p>
 * Copied verbatim from {@link com.google.gwt.place.shared.Prefix}
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Prefix {
  String value();
}