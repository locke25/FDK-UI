package com.jcim.fdk.workbench.place;


import org.apache.commons.lang.builder.ToStringBuilder;

import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceTokenizer;
import com.inubit.gui.vaadin.mvp.place.Prefix;

/**
 * A sub-place of {@link WorkbenchPlace} if an item got selected.
 */
@Prefix("item-selected")
public class ItemSelectedPlace extends Place {

    /**
     * Tokenizer for ItemSelectedPlace.
     *
     */
    public static class Tokenizer implements PlaceTokenizer<ItemSelectedPlace> {

        @Override
        public ItemSelectedPlace getPlace(String token) {
            return new ItemSelectedPlace(Long.parseLong(token));
        }

        @Override
        public String getToken(ItemSelectedPlace place) {
            return String.valueOf(place.getItemId());
        }
    }

    private long itemId;
    
    public ItemSelectedPlace(final long itemId) {
    	this.itemId = itemId;
    }

    public long getItemId() {
        return itemId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new Long(itemId).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ItemSelectedPlace other = (ItemSelectedPlace) obj;
        return other.itemId == this.itemId;
    }

}
