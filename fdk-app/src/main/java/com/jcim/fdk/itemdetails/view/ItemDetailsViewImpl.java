package com.jcim.fdk.itemdetails.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.inubit.gui.vaadin.mvp.view.impl.ComponentContainerViewPort;
import com.inubit.gui.vaadin.mvp.view.impl.IsVaadinComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

public class ItemDetailsViewImpl extends CssLayout implements ItemDetailsView, IsVaadinComponent {

	private static final Logger log = LoggerFactory.getLogger(ItemDetailsViewImpl.class);

	private ViewPort menuDisplay;

	private ViewPort contentDisplay;

	private Presenter presenter;

	private CssLayout menuLayout;

	public ItemDetailsViewImpl() {
		
		buildTable();
		buildMenuDisplay();
		buildContentDisplay();		

		setStyleName("itemDetails");

		registerEvents();
	}


	private void registerEvents() {		
	}

	
	private void buildTable() {	
	}
	

	@Override
	public Component asVaadinComponent() {
		return this;
	}

	private void buildMenuDisplay() {
		menuLayout = new CssLayout();
		menuLayout.setImmediate(false);

		menuLayout.setStyleName("itemDetailsMenu");

		this.addComponent(menuLayout);
		menuDisplay = new ComponentContainerViewPort(menuLayout);
	}

	private void buildContentDisplay() {
		// common part: create layout
		CssLayout mainLayout = new CssLayout();
		mainLayout.setImmediate(false);

		mainLayout.setStyleName("itemDetailsContent");

		this.addComponent(mainLayout);
		contentDisplay = new ComponentContainerViewPort(mainLayout);
	}

	@Override
	public ViewPort getMenuDisplay() {
		return menuDisplay;
	}

	@Override
	public ViewPort getContentDisplay() {
		return contentDisplay;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
