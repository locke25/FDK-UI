package com.jcim.fdk.mvp;

import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceChangeRequestEvent;

public class StoppablePlaceChangeRequestEvent extends PlaceChangeRequestEvent
{
	private boolean cancel;
	
	public StoppablePlaceChangeRequestEvent(Place newPlace)
	{
		super(newPlace);
		cancel = false;
	}
	
	public boolean isCancel()
	{
		return cancel;
	}
	
	public void setCancel(boolean cancel)
	{
		this.cancel = cancel;
	}
}
