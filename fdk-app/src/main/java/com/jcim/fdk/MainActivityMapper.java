package com.jcim.fdk;

import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.activity.Activity;
import com.inubit.gui.vaadin.mvp.activity.ActivityMapper;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.jcim.fdk.customer.activity.AddCustomerActivityImpl;
import com.jcim.fdk.itemdetails.activity.ItemDetailsMVPSubContainer;
import com.jcim.fdk.workbench.activity.WorkbenchMVPSubContainer;
import com.jcim.fdk.workbench.place.WorkbenchPlace;

public class MainActivityMapper implements ActivityMapper {

	private PlaceController placeController;
	private ApplicationContext ctx;
	private WorkbenchMVPSubContainer subContainer;
	
	public MainActivityMapper(PlaceController placeController, ApplicationContext ctx) {
		this.placeController = placeController;
		this.ctx = ctx;
	}

	@Override
	public Activity getActivity(Place place) {		
				
		WorkbenchPlace wbPlace = (WorkbenchPlace)place;
		if(wbPlace.getWorkbenchName().equals("PlaceOne")) {		
			return new ItemDetailsMVPSubContainer(ctx);
		} 
		else if (wbPlace.getWorkbenchName().equals("PlaceTwo"))
		{
			return new AddCustomerActivityImpl(ctx);
		}
				
		return null; 
	}
}
