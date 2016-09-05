package com.jcim.fdk.mvp;

import com.inubit.gui.vaadin.mvp.activity.Activity;


/**
 * 
 * With this {@link StopPreventableActivity} it is possible to prevent an activity from stopping.
 * 
 * @author fabian.hessel@binaere-bauten.de
 * 
 */

public interface StopPreventableActivity extends Activity
{
	/**
	 * Called from {@link StoppableActivityManager} before stopping
	 * @return true prevent from stopping, false stopped the activity
	 */
	public boolean cancelStop();
}
