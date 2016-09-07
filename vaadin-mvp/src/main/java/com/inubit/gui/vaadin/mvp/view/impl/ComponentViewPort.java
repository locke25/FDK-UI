package com.inubit.gui.vaadin.mvp.view.impl;

import com.inubit.gui.vaadin.mvp.view.View;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.vaadin.ui.CustomComponent;


/**
 * A display which itself is a {@link CustomComponent}. Can be used as a display slot. Attention:
 * this adds an extra element to the DOM tree. It is recommended to use
 * {@link ComponentContainerBasedViewPort}.
 */
@SuppressWarnings("serial")
public class ComponentViewPort extends CustomComponent implements ViewPort {

    public ComponentViewPort() {
        setSizeUndefined();
    }

    @Override
    public void setView(View view) {
        setCompositionRoot(VaadinComponentUtil.toVaadinComponent(view));
    }
}
