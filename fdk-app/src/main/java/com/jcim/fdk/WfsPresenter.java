package com.jcim.fdk;

import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.activity.ActivityManager;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.place.PlaceHistoryHandler;
import com.inubit.gui.vaadin.mvp.place.PlaceHistoryMapper;
import com.inubit.gui.vaadin.mvp.place.PlaceHistoryMapperImpl;
import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.inubit.gui.vaadin.mvp.view.impl.ComponentContainerViewPort;
import com.jcim.fdk.mvp.StoppableActivityManager;
import com.jcim.fdk.navigation.activity.NavigationActivityMapper;
import com.jcim.fdk.workbench.place.WorkbenchPlace;


public class WfsPresenter {

    private Shell shell;
    private EventBus eventBus;
    private PlaceController placeController;
    private WfsView wfsView;
    private NavigationActivityMapper navigationActivityMapper;
    private MainActivityMapper mainActivityMapper;

    public WfsPresenter(ApplicationContext ctx) {
    	
    	this.shell = ctx.getBean(Shell.class);
        this.eventBus =  ctx.getBean(EventBus.class);
		this.placeController = ctx.getBean(PlaceController.class);
		this.wfsView = ctx.getBean(WfsView.class);

        this.navigationActivityMapper = new NavigationActivityMapper(placeController, WorkbenchPlace.getWorkbenches());
        this.mainActivityMapper = new MainActivityMapper(placeController, ctx);
        
    }

    public void init() {

        // Browser history integration
        // FIXME make this more dynamic, don't pass the place explicitly
        final PlaceHistoryMapper historyMapper = new PlaceHistoryMapperImpl(WorkbenchPlace.class);
        final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper,shell);
        final WorkbenchPlace defaultPlace = WorkbenchPlace.getWorkbenches()[0];

        historyHandler.register(placeController, eventBus, defaultPlace);

        final ActivityManager menuActivityManager = new StoppableActivityManager(navigationActivityMapper, eventBus);
        final ActivityManager mainActivityManager = new StoppableActivityManager(mainActivityMapper, eventBus);

        mainActivityManager.setViewPort(new ComponentContainerViewPort(wfsView.getMainContainer()));
        menuActivityManager.setViewPort(new ComponentContainerViewPort(wfsView.getMenuDisplay()));

        historyHandler.handleCurrentHistory();
    }

}
