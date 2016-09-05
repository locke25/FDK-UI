package com.jcim.fdk.workbench.view;

import com.inubit.gui.vaadin.mvp.view.View;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.inubit.gui.vaadin.mvp.view.impl.IsVaadinComponent;

public interface WorkbenchView extends IsVaadinComponent, View {

    ViewPort getItemListViewPort();

    ViewPort getItemDetailsViewPort();

}
