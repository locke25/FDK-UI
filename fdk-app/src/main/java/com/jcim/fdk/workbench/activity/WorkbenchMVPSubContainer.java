package com.jcim.fdk.workbench.activity;

import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.activity.AbstractMVPSubContainer;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.jcim.fdk.itemdetails.place.ItemDetailsPlace;
import com.jcim.fdk.itemdetails.place.ItemDetailsPlace.Action;
import com.jcim.fdk.workbench.place.ItemSelectedPlace;
import com.jcim.fdk.workbench.place.WorkbenchPlace;

public class WorkbenchMVPSubContainer extends AbstractMVPSubContainer<WorkbenchActivity>{

	private WorkbenchPlace workbenchPlace;
	private PlaceController innerPlaceController;
	private ApplicationContext ctx;
	private Action action;
	private int id;
	
	public static enum Actions {
		LIST,		
		SHOW	
	}
	
	public WorkbenchMVPSubContainer(WorkbenchPlace place, ApplicationContext ctx, PlaceController placeController, Action action, int id) {
		super(place.getWorkbenchName(), ctx);
		this.workbenchPlace = place;
		this.innerPlaceController = placeController;
		this.ctx = ctx;
		this.action = action;
		this.id = id;
	}

	@Override
	protected WorkbenchActivity newActivityInstance() {
		return new WorkbenchActivity(workbenchPlace, innerPlaceController, ctx);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class<? extends Place>[] getSupportedPlaces() {
		return new Class[] {ItemDetailsPlace.class};
	}

	@Override
	protected Place getDefaultPlace() {
		return new ItemDetailsPlace(action, id);
	}

}
