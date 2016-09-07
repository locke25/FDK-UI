package com.inubit.gui.vaadin.mvp.place;

/**
 * Abstract implementation of {@link PlaceHistoryMapper}.
 */
public abstract class AbstractPlaceHistoryMapper implements PlaceHistoryMapper  {

  /**
   * Return value for
   * {@link AbstractPlaceHistoryMapper#getPrefixAndToken(Place)}.
   */
  public static class PrefixAndToken {
    public final String prefix;
    public final String token;

    public PrefixAndToken(String prefix, String token) {
      assert prefix != null && !prefix.contains(":");
      this.prefix = prefix;
      this.token = token;
    }

    @Override
    public String toString() {
      return (prefix.length() == 0) ? token : prefix + ":" + token;
    }
  }

  @Override
public Place getPlace(String token) {
    int colonAt = token.indexOf(':');
    if (colonAt > 0) {
      String initial = token.substring(0, colonAt);
      String rest = token.substring(colonAt + 1);
      PlaceTokenizer<?> tokenizer = getTokenizer(initial);
      if (tokenizer != null) {
        return tokenizer.getPlace(rest);
      }
    }
    return null;
  }

  @Override
public String getToken(Place place) {
    PrefixAndToken token = getPrefixAndToken(place);
    if (token != null) {
      return token.toString();
    }
    return null;
  }

  /**
   * @param newPlace what needs tokenizing
   * @return the token, or null
   */
  protected abstract PrefixAndToken getPrefixAndToken(Place newPlace);

  /**
   * @param prefix the prefix found on the history token
   * @return the PlaceTokenizer registered with that token, or null
   */
  protected abstract PlaceTokenizer<?> getTokenizer(String prefix);
}
