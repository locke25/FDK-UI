package com.jcim.fdk.itemdetails.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.activity.AbstractActivity;
import com.inubit.gui.vaadin.mvp.activity.Activity;
import com.inubit.gui.vaadin.mvp.activity.ActivityMapper;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.jcim.fdk.details.activity.DetailsActivity;
import com.jcim.fdk.history.activity.HistoryActivity;
import com.jcim.fdk.itemdetails.place.ItemDetailsPlace;
import com.jcim.fdk.itemlist.place.ItemListPlace;



public class ItemDetailsActivityMapper implements ActivityMapper {

	private static final Logger log = LoggerFactory.getLogger(ItemDetailsActivityMapper.class);
	private AbstractActivity activity;
	private ApplicationContext ctx;
	private Place parentPlace;
	
	public ItemDetailsActivityMapper(final ApplicationContext ctx,Place parentPlace) {
		this.ctx = ctx;
		this.parentPlace = parentPlace;
	}
	
	@Override
	public Activity getActivity(Place place) {
		
		if (place instanceof ItemListPlace){
			ctx.getParent().getBean(PlaceController.class).goTo(place);
    		return null;
		}
		
		final ItemDetailsPlace actionPlace = (ItemDetailsPlace)place;
		
		switch (actionPlace.getAction()) {		
		
		case SUBPLACE_ONE: log.debug("ActionDetails");
			activity = new DetailsActivity(ctx,parentPlace);			
			return activity;		
			
		case SUBPLACE_TWO: log.debug("ActionHistory");
			activity = new HistoryActivity(ctx,parentPlace); 
			return  activity;		

		default:
			return null;
		}		
	}
	
}
