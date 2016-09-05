package com.jcim.fdk.itemdetails.activity;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.inubit.gui.vaadin.mvp.activity.AbstractActivity;
import com.inubit.gui.vaadin.mvp.activity.ActivityManager;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.event.HandlerRegistration;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.jcim.fdk.details.event.CategorySelectedEvent;
import com.jcim.fdk.details.view.DetailsView;
import com.jcim.fdk.itemdetails.place.ItemDetailsPlace;
import com.jcim.fdk.itemdetails.view.ItemDetailsView;
import com.jcim.fdk.itemlist.place.ItemListPlace;
import com.jcim.fdk.navigation.activity.NavigationActivityMapper;
import com.jcim.fdk.workbench.place.WorkbenchPlace;
import com.vaadin.ui.Embedded;

public class ItemDetailsActivity extends AbstractActivity implements ItemDetailsView.Presenter {

	private static final Logger log = LoggerFactory.getLogger(ItemDetailsActivity.class);

	private final ItemDetailsView view;

	private final NavigationActivityMapper navigationActivityMapper;

	private final ItemDetailsActivityMapper itemDetailsActionActivityMapper;

	private ActivityManager detailsActivityManager;

	private final Shell shell;

	private final DetailsView detailView;

	private WorkbenchPlace parentPlace;

	private final PlaceController placeController;

	private HandlerRegistration catSelectReg;

	public ItemDetailsActivity(ApplicationContext ctx, Place parentPlace) {
		
		placeController = ctx.getBean(PlaceController.class);
		view = ctx.getBean(ItemDetailsView.class);
		view.setPresenter(this);
		this.shell = ctx.getBean(Shell.class);

		navigationActivityMapper = new NavigationActivityMapper(placeController, ItemDetailsPlace.getActionPlaces());
		itemDetailsActionActivityMapper = new ItemDetailsActivityMapper(ctx, parentPlace);
		
		detailView = ctx.getBean(DetailsView.class);		
		this.parentPlace = (WorkbenchPlace) parentPlace;

	}

	@Override
	public void start(ViewPort display, EventBus eventBus) {
		final ActivityManager navigationActivityManager = new ActivityManager(navigationActivityMapper, eventBus);
		navigationActivityManager.setViewPort(view.getMenuDisplay());

		detailsActivityManager = new ActivityManager(itemDetailsActionActivityMapper, eventBus);
		detailsActivityManager.setViewPort(view.getContentDisplay());

		catSelectReg = eventBus.addHandler(CategorySelectedEvent.class, new CategorySelectedEvent.Handler() {

			@Override
			public void update(long categoryId) {
				// do something here
			}
		});
		
		update(parentPlace);
		display.setView(view);
	}

	@Override
	public void onStop() {
		if (catSelectReg != null) {
			catSelectReg.removeHandler();
		}		
		super.onStop();
	}

	@Override
	public void update(Place place) {
		parentPlace = (WorkbenchPlace) place;
		detailsActivityManager.getCurrentActivity().update(place);
	}

	public Shell getShell() {
		return this.shell;
	}

}
