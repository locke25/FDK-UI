package com.jcim.fdk.workbench.activity;

import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.activity.AbstractActivity;
import com.inubit.gui.vaadin.mvp.activity.ActivityManager;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.jcim.fdk.itemdetails.activity.ItemDetailsActivityMapper;
import com.jcim.fdk.itemdetails.place.ItemDetailsPlace;
import com.jcim.fdk.itemdetails.view.ItemDetailsView;
import com.jcim.fdk.itemdetails.view.ItemDetailsViewImpl;
import com.jcim.fdk.navigation.activity.NavigationActivityMapper;
import com.jcim.fdk.workbench.place.WorkbenchPlace;

public class WorkbenchActivity extends AbstractActivity {

	private WorkbenchPlace place;
	private ItemDetailsView view;
	
	private final NavigationActivityMapper navigationActivityMapper;
	private final ItemDetailsActivityMapper itemDetailsActionActivityMapper;
	
    public WorkbenchActivity(WorkbenchPlace place, PlaceController placeController, ApplicationContext ctx) {
    	
    	this.place = place;    	
    	
    	navigationActivityMapper = new NavigationActivityMapper(placeController, ItemDetailsPlace.getActionPlaces());
		itemDetailsActionActivityMapper = new ItemDetailsActivityMapper(ctx, placeController.getWhere());
    	
    	view = ctx.getBean(ItemDetailsView.class);    	
    }

	@Override
	public void start(ViewPort display, EventBus eventBus) {
		
		final ActivityManager navigationActivityManager = new ActivityManager(navigationActivityMapper, eventBus);
		navigationActivityManager.setViewPort(view.getMenuDisplay());

		final ActivityManager detailsActivityManager = new ActivityManager(itemDetailsActionActivityMapper, eventBus);
		detailsActivityManager.setViewPort(view.getContentDisplay());
		
		display.setView(view);	
	}
	
	@Override
	public void onStop() {
		super.onStop();
	}

}
