package com.jcim.fdk.details.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.activity.Activity;
import com.inubit.gui.vaadin.mvp.activity.ActivityMapper;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.jcim.fdk.itemdetails.activity.ItemDetailsMVPSubContainer;
import com.jcim.fdk.itemlist.place.ItemListPlace;

public class DetailsActivityMapper implements ActivityMapper {

	private static final Logger log = LoggerFactory.getLogger(DetailsActivityMapper.class);
	
	private ItemDetailsMVPSubContainer activity;
	private ApplicationContext ctx;
	
	public DetailsActivityMapper(final ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	@Override
	public Activity getActivity(Place place) {
		log.debug("requesting activity for place {}", place);
		final ItemListPlace selectedPlace = (ItemListPlace)place;
		if(selectedPlace.getItemId() < 1) {
			return null;
		}
		if(activity == null) {
			activity = new ItemDetailsMVPSubContainer(ctx);
		}
		return activity;
	}
	
	public void reset() {
		if(activity != null) {
			activity.onStop();
		}
	}
}
