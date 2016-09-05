package com.jcim.fdk.itemdetails.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.activity.AbstractMVPSubContainer;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.jcim.fdk.details.event.CategorySelectedEvent;
import com.jcim.fdk.itemdetails.place.ItemDetailsPlace;

public class ItemDetailsMVPSubContainer extends AbstractMVPSubContainer<ItemDetailsActivity>{

	private final static Logger log = LoggerFactory.getLogger(ItemDetailsMVPSubContainer.class);
	
	public ItemDetailsMVPSubContainer(final ApplicationContext ctx) {
		super("details", ctx);
	}
	
	@Override
	public void start(ViewPort viewPort, final EventBus outerEventBus) {
		super.start(viewPort, outerEventBus);
		EventBus innerEventBus = getApplicationContext().getBean(EventBus.class);
		innerEventBus.addHandler(CategorySelectedEvent.class, new CategorySelectedEvent.Handler() {
			
			@Override
			public void update(long categoryId) {
				outerEventBus.fireEvent(new CategorySelectedEvent(categoryId));
			}
		});
		
	}
	
	@Override
	protected Place getDefaultPlace() {
		return ItemDetailsPlace.getActionPlaces()[0];
	}
	
	@Override
	protected Class<? extends Place>[] getSupportedPlaces() {
		return new Class[]{ItemDetailsPlace.class};
	}
	
	@Override
	protected ItemDetailsActivity newActivityInstance() {
		PlaceController parentPlaceController = getApplicationContext().getParent().getBean(PlaceController.class);
		return new ItemDetailsActivity(getApplicationContext(), parentPlaceController.getWhere());
	}
	
	@Override
	public void update(Place place) {
		log.debug("update called for place {}", place);
		super.update(place);
	}
	
}
