package com.inubit.gui.vaadin.mvp.view.impl;

import java.io.Serializable;

import com.vaadin.ui.Component;


/**
 * Implemented if a component is a Vaadin component. Extends Serializable as all Vaadin components will have to.
 */
public interface IsVaadinComponent extends Serializable {
    Component asVaadinComponent();
}
