package com.inubit.gui.vaadin.mvp.view;


/**
 * Implemented by displays that can be given an {@link View} to show.
 */
public interface ViewPort {

    /**
     * Set the only component of the receiver, replacing the previous component if there was one.
     */
    void setView(View view);

}
