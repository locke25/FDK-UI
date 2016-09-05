package com.jcim.fdk.details.view;

import com.inubit.gui.vaadin.mvp.view.View;

public interface DetailsView extends View {

	void setPresenter(Presenter presenter);
		
	public static interface Presenter {    	
		void createEntity(String testString, String testBoolean, String testEnum);
	}
	
}
