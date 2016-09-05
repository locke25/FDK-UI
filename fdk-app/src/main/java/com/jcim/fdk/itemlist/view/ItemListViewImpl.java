package com.jcim.fdk.itemlist.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.inubit.gui.vaadin.mvp.view.impl.IsVaadinComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;


public class ItemListViewImpl extends CssLayout implements ItemListView, IsVaadinComponent {

	private static final Logger log = LoggerFactory.getLogger(ItemListViewImpl.class);	

	private Presenter presenter;

	private Shell shell;	

	public ItemListViewImpl() {
		super();
	}

	@Override
	public Component asVaadinComponent() {
		return this;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}


}
