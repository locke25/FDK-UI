package com.inubit.gui.vaadin.mvp.activity;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.inubit.gui.vaadin.mvp.config.MVPBaseConfig;
import com.inubit.gui.vaadin.mvp.config.MVPFrameworkConfig;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.event.HandlerRegistration;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceChangeEvent;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.place.PlaceHistoryHandler;
import com.inubit.gui.vaadin.mvp.place.PlaceHistoryMapperImpl;
import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.inubit.gui.vaadin.mvp.view.ViewPort;

/**
 * Builds an inner MVP container having its own {@link ActivityManager},
 * {@link PlaceController} and {@link EventBus}. {@link PlaceChangeEvent} events
 * are fired to the outer {@link PlaceController} and vice versa.
 * 
 * @param <A>
 *            the inner activity the container will delegate to.
 */
public abstract class AbstractMVPSubContainer<A extends Activity> extends
		AbstractActivity {
	private String id;

	private EventBus innerEventBus;

	private PlaceController innerPlaceController;

	private Shell shell;

	private PlaceHistoryHandler historyHandler;

	private Shell subShell;

	private HandlerRegistration historyReg;

	private A activity;
	
	private ApplicationContext ctx;
	private AnnotationConfigApplicationContext subCtx;

	public AbstractMVPSubContainer(String id, ApplicationContext ctx) {
		this.id = id;
		this.ctx = ctx;
	}

	@Override
	public void start(ViewPort viewPort, EventBus outerEventBus) {

		subShell = ctx.getBean(Shell.class).createSubShell(id);
		
		subCtx = new AnnotationConfigApplicationContext();
		subCtx.register(MVPBaseConfig.class);
		subCtx.setParent(ctx);
		subCtx.getBeanFactory().registerSingleton("shell", subShell);
		subCtx.refresh();

		System.out.println(ctx.getBean(EventBus.class));
		System.out.println(subCtx.getBean(EventBus.class));
		
		innerEventBus = subCtx.getBean(EventBus.class);
		innerPlaceController = subCtx.getBean(PlaceController.class);

		activity = newActivityInstance();
		activity.start(viewPort, innerEventBus);

		historyHandler = new PlaceHistoryHandler(new PlaceHistoryMapperImpl(
				getSupportedPlaces()), subShell);
		historyReg = historyHandler.register(innerPlaceController,
				innerEventBus, getDefaultPlace());

		historyHandler.handleCurrentHistory();
	}

	@Override
	public void onStop() {
		if (activity != null) {
			activity.onStop();
			historyReg.removeHandler();
			subShell.setFragment(null);
		}
	}

	@Override
	public String mayStop() {
		return activity.mayStop();
	}
	
	@Override
	public void update(Place place) {
		if (activity != null) {
			activity.update(place);
		}
	}

	public String getId() {
		return id;
	}
	
	protected ApplicationContext getApplicationContext() {
		return subCtx;
	}

	public PlaceController getInnerPlaceController()
	{
		return innerPlaceController;
	}
	
	public EventBus getInnerEventBus()
	{
		return innerEventBus;
	}
	
	protected abstract A newActivityInstance();

	/**
	 * Prepare the IoC container.
	 */

	protected abstract Class<? extends Place>[] getSupportedPlaces();

	protected abstract Place getDefaultPlace();

}
