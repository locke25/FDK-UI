package com.jcim.fdk.mvp;

import com.inubit.gui.vaadin.mvp.activity.ActivityManager;
import com.inubit.gui.vaadin.mvp.activity.ActivityMapper;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.place.PlaceChangeRequestEvent;
import com.inubit.gui.vaadin.mvp.place.PlaceController;

/**
 * 
 * With this {@link StoppableActivityManager} it is possible to prevent an
 * activity from stopping. The corresponding 
 * activity must implements {@link StopPreventableActivity} and the
 * {@link PlaceController} must be {@link StoppablePlaceController}
 * 
 * @author fabian.hessel@binaere-bauten.de
 * 
 */

public class StoppableActivityManager extends ActivityManager
{
	
	public StoppableActivityManager(ActivityMapper mapper, EventBus eventBus)
	{
		super(mapper, eventBus);
		eventBus.addHandler(StoppablePlaceChangeRequestEvent.class, this);
	}
	
	@Override
	public void onPlaceChangeRequest(PlaceChangeRequestEvent event)
	{
		super.onPlaceChangeRequest(event);
		if (getCurrentActivity() instanceof StopPreventableActivity &&
			event instanceof StoppablePlaceChangeRequestEvent)
		{
			((StoppablePlaceChangeRequestEvent)event).setCancel(((StopPreventableActivity) getCurrentActivity()).cancelStop());
		}
	}
}
