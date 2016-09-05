package com.jcim.fdk.navigation.place;

import com.inubit.gui.vaadin.mvp.place.Place;

public interface NavigationPlace {

	public String getLabel();
	
	public Place asPlace();
}
