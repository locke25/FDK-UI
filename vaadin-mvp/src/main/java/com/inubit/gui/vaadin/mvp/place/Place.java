package com.inubit.gui.vaadin.mvp.place;

/**
 * Represents a bookmarkable location in an app. Implementations are expected to
 * provide correct {@link Object#equals(Object)} and {@link Object#hashCode()}
 * methods.
 */
public abstract class Place {
    /**
     * The null place.
     */
    public static final Place NOWHERE = new Place() {
    };

    /**
     * @return the prefix value for this place if a <code>Prefix</code> annotation exists, else <code>null</code>.
     */
    public final String getPrefixValue() {
        Prefix prefix = getClass().getAnnotation(Prefix.class);
        if(prefix != null){
            return prefix.value();
        }
        return null;
    }
}