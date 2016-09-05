package com.jcim.fdk.itemdetails.view;

import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.inubit.gui.vaadin.mvp.view.View;
import com.inubit.gui.vaadin.mvp.view.ViewPort;

public interface ItemDetailsView extends View {

	public ViewPort getMenuDisplay();
	public ViewPort getContentDisplay();
	
	public static interface Presenter {    	
		
    	public Shell getShell();    	
    	
    }
	
	public void setPresenter(Presenter presenter);
	
	
}
