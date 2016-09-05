package com.jcim.fdk.itemlist.activity;

import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.activity.Activity;
import com.inubit.gui.vaadin.mvp.activity.ActivityMapper;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.jcim.fdk.itemdetails.activity.ItemDetailsActivity;
import com.jcim.fdk.itemdetails.activity.ItemDetailsMVPSubContainer;
import com.jcim.fdk.itemlist.place.ItemListPlace;
import com.jcim.fdk.workbench.place.WorkbenchPlace;

public class ItemListActivityMapper implements ActivityMapper {

	private ItemListActivity listActivity;
	private ItemDetailsMVPSubContainer detailsActivity;
	private ApplicationContext ctx;

	public ItemListActivityMapper(ApplicationContext ctx) {
		listActivity = new ItemListActivity(ctx);
		this.ctx = ctx;
		// detailsActivity = new ItemDetailsActivity(ctx);
	}

	@Override
	public Activity getActivity(Place place) {

		final ItemListPlace selectedPlace = (ItemListPlace) place;
		
		if (detailsActivity == null) {
			detailsActivity = new ItemDetailsMVPSubContainer(ctx);
			detailsActivity.update(selectedPlace);
		}
		return detailsActivity;		
	}
}
