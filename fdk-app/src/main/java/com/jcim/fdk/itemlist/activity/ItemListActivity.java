package com.jcim.fdk.itemlist.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.activity.AbstractActivity;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.jcim.fdk.itemlist.place.ItemListPlace;
import com.jcim.fdk.itemlist.view.ItemListView;


public class ItemListActivity extends AbstractActivity implements ItemListView.Presenter {

	private static final Logger log = LoggerFactory.getLogger(ItemListActivity.class);
	private ItemListView view;
	private PlaceController placeController;
	private Shell shell;	
	private EventBus eventBus;	

	public ItemListActivity(ApplicationContext ctx) {		
		this.placeController = ctx.getBean(PlaceController.class);
		this.view = ctx.getBean(ItemListView.class);		
		this.shell = ctx.getBean(Shell.class);		
		view.setPresenter(this);
		view.setShell(shell);		

	}

	@Override
	public void start(ViewPort viewPort, EventBus eventBus) {
	    this.eventBus = eventBus;
		viewPort.setView(view);
		ItemListPlace place = ((ItemListPlace) placeController.getWhere());		
		
	}
			
	@Override
	public void onStop() {		
		super.onStop();
	}


//	@Override
//	public void onItemSelection(Actions action, long itemId, String searchTerm) {
//		placeController.goTo(new ItemListPlace(action, itemId, searchTerm));
//	}	

	
}
