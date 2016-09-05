package com.jcim.fdk.itemlist.view;

import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.inubit.gui.vaadin.mvp.view.View;

public interface ItemListView extends View {

	void setPresenter(Presenter presenter);

	/**
	 * Presenter we have to inform about navigation events.
	 */
	public static interface Presenter {
		
	}

		
	void setShell(Shell shell);
	
}
