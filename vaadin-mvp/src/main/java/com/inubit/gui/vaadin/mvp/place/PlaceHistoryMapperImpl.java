package com.inubit.gui.vaadin.mvp.place;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is the hub of the application's navigation system. It links
 * the {@link Place}s a user navigates to with the browser history system &mdash; that is, it makes the browser's back
 * and forth buttons work, and also makes each spot in the app bookmarkable.
 *
 */
public class PlaceHistoryMapperImpl extends AbstractPlaceHistoryMapper {
    private static final Logger log = LoggerFactory.getLogger(PlaceHistoryMapperImpl.class);

    private Map<String, PlaceTokenizer<Place>> tokenizers = new HashMap<String, PlaceTokenizer<Place>>();

    public PlaceHistoryMapperImpl(Class<? extends Place>... places) {
        registerTokenizers(places);
    }

    @SuppressWarnings("unchecked")
    private void registerTokenizers(Class<? extends Place>... places) {
        log.debug("Starting registering tokenizers for places...");
        for(Class<? extends Place> clazz: places){
            Prefix prefix = clazz.getAnnotation(Prefix.class);
            if(prefix == null){
                log.info("No @Prefix annotation found for place {}. Skipping it...", clazz.getName());
                continue;
            }

            boolean foundTokenizer = false;
            final Class<?>[] declaredClasses = clazz.getDeclaredClasses();

            for(Class<?> declaredClass : declaredClasses){
                if(PlaceTokenizer.class.isAssignableFrom(declaredClass)){
                    try {
                        final PlaceTokenizer<Place> tokenizer = (PlaceTokenizer<Place>) declaredClass.newInstance();
                        tokenizers.put(prefix.value(), tokenizer);
                        log.debug("Added tokenizer for place {}", clazz.getName());
                        foundTokenizer = true;
                    } catch (InstantiationException e) {
                        throw new IllegalStateException(e);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    }
                }
            }
            if(!foundTokenizer){
                log.warn("A @Prefix annotation was detected for {} but a PlaceTokenizer implementation was not found.", clazz.getName());
            }
        }
    }

    @Override
    protected PrefixAndToken getPrefixAndToken(Place newPlace) {
        final String prefix = newPlace.getPrefixValue();
        if (prefix == null) {
            return null;
        }
        PlaceTokenizer<Place> tokenizer = tokenizers.get(prefix);
        if (tokenizer == null) {
            return null;
        }
        String token = tokenizer.getToken(newPlace);
        return new PrefixAndToken(prefix, token);
    }

    @Override
    protected PlaceTokenizer<?> getTokenizer(String prefix) {
        return tokenizers.get(prefix);
    }
}
