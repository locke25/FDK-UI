package com.jcim.fdk.mvp;

import com.inubit.gui.vaadin.mvp.activity.ActivityManager;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.vaadin.ui.Notification;

/**
 * 
 * With this {@link StoppablePlaceController} it is possible to prevent an
 * activity from stopping. The corresponding 
 * activity must implements {@link StopPreventableActivity} and the
 * {@link ActivityManager} must be {@link StoppableActivityManager}
 * 
 * @author fabian.hessel@binaere-bauten.de
 * 
 */
public class StoppablePlaceController extends PlaceController
{
	private EventBus eventBus;
	
	public StoppablePlaceController(EventBus eventBus, Shell shell)
	{
		super(eventBus, shell);
		this.eventBus = eventBus;
	}
	
	@Override
	public void goTo(Place newPlace)
	{
		if (getWhere().equals(newPlace))
		{
			return;
		}
		StoppablePlaceChangeRequestEvent willChange = new StoppablePlaceChangeRequestEvent(newPlace);
		eventBus.fireEvent(willChange);
		if (willChange.isCancel())
		{
			if (willChange.getWarning() != null)
				Notification.show(willChange.getWarning());
		}
		else
		{
			if (willChange.getWarning() != null)
				Notification.show(willChange.getWarning());
			goToWithoutChecks(newPlace);
		}
	}
}
