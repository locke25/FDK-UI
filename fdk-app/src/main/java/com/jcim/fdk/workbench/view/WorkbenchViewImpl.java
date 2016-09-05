package com.jcim.fdk.workbench.view;

import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.inubit.gui.vaadin.mvp.view.impl.ComponentContainerViewPort;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class WorkbenchViewImpl extends CssLayout implements WorkbenchView {


	private ViewPort itemDetailsViewPort;
	private ViewPort itemListViewPort;


	public WorkbenchViewImpl() {
		setWidth("-1px");
		setHeight("-1px");
		
		buildItemListViewPort();
		buildItemDetailsViewPort();	
	}


	private void buildItemListViewPort() {
		// common part: create layout
		CssLayout itemListContainer = new CssLayout();
		itemListContainer.setImmediate(false);
//		itemListContainer.setWidth("100%");
//		itemListContainer.setHeight("100%");
		
		addComponent(itemListContainer);
		
		itemListViewPort = new ComponentContainerViewPort(itemListContainer);
	}

	private void buildItemDetailsViewPort() {
		CssLayout itemDetailsContainer = new CssLayout();
		itemDetailsContainer.setImmediate(false);
//		itemDetailsContainer.setWidth("100%");
//		itemDetailsContainer.setHeight("100%");
		
		addComponent(itemDetailsContainer);
		
		itemDetailsViewPort = new ComponentContainerViewPort(itemDetailsContainer);
	}
	
	
	
	@Override
	public Component asVaadinComponent() {
		return this;
	}
	
	@Override
	public ViewPort getItemDetailsViewPort() {
		return itemDetailsViewPort;
	}
	
	@Override
	public ViewPort getItemListViewPort() {
		return itemListViewPort;
	}
}
