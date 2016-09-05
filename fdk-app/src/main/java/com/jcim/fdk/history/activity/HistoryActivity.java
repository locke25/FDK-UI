package com.jcim.fdk.history.activity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.google.gwt.event.shared.HandlerRegistration;
import com.inubit.gui.vaadin.mvp.activity.AbstractActivity;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.jcim.fdk.WfsUI;
import com.jcim.fdk.config.PersistenceConfig;
import com.jcim.fdk.details.event.TestDaoPushInFrontendEvent;
import com.jcim.fdk.history.view.HistoryView;
import com.jcim.fdk.model.Test;


public class HistoryActivity extends AbstractActivity implements HistoryView.Presenter {

	private static final Logger log = LoggerFactory.getLogger(HistoryActivity.class);
	private HistoryView view;
	private PersistenceConfig persistenceConfig;
	
	final private WfsUI app;
	
	public HistoryActivity(ApplicationContext ctx, Place parentPlace) {
		persistenceConfig = ctx.getBean(PersistenceConfig.class);
		view = ctx.getBean(HistoryView.class);
		view.setPresenter(this);
		this.app = ctx.getBean(WfsUI.class);
		
	}

	@Override
	public void start(ViewPort display, EventBus eventBus) {
		display.setView(view);
		view.setData();
		
		eventBus.addHandler(TestDaoPushInFrontendEvent.class, new TestDaoPushInFrontendEvent.Handler() {

			@Override
			public void update() {
				view.setData();
				app.push();
			}
		});

	}

	@Override
	public void update(Place place) {
		log.debug("selected Place {}", place);		
	}
	
	@Override
	public String getDataFromTestDao() {
		List<Test> testEntities = persistenceConfig.testDao().retrieveAll();
		
		StringBuffer stb = new StringBuffer();
		for(Test test : testEntities) {
			stb.append(test.getId() + " " + test.getTestString() + " " + test.getTestEnum().toString() + " "  + test.isTestBoolean() +  " \n");
		}
		return stb.toString();
	}
}