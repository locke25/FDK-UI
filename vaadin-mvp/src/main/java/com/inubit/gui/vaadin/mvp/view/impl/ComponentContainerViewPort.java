package com.inubit.gui.vaadin.mvp.view.impl;

import java.io.Serializable;

import com.inubit.gui.vaadin.mvp.view.View;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;


/**
 * A {@link ViewPort} using a Vaadin {@link ComponentContainer}. When constructed it will add a
 * placeholder component to the container. If the view is set the current component will be
 * replaced. components could be added.
 */
public class ComponentContainerViewPort implements ViewPort, Serializable {

    // TODO this is a dummy initialization to have a component to add right from the beginning
    private Component component = new Label();

    private ComponentContainer container;

    public ComponentContainerViewPort(ComponentContainer container) {
        this.container = container;
        container.addComponent(component);
    }

    @Override
    public void setView(View view) {
        // TODO view is null if the view port is reset. this happens if the activity manager changes
        // the place
        Component newComponent = view != null ? VaadinComponentUtil.toVaadinComponent(view) : new Label();
        container.replaceComponent(component, newComponent);
        component = newComponent;
    }

    public Component getComponent() {
        return component;
    }
}