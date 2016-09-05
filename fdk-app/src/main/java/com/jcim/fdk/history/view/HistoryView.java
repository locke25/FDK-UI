package com.jcim.fdk.history.view;

import com.inubit.gui.vaadin.mvp.view.View;

public interface HistoryView extends View {

	void setPresenter(Presenter presenter);
	
	public static interface Presenter {
		String getDataFromTestDao();   
    }

	void updateData(long itemId);	
	void setData();
}
