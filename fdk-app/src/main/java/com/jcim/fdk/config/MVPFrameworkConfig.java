package com.jcim.fdk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.event.SimpleEventBus;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.inubit.gui.vaadin.mvp.shell.impl.ShellImpl;
import com.inubit.gui.vaadin.mvp.view.impl.MainWindow;
import com.jcim.fdk.config.PersistenceConfig;
import com.jcim.fdk.customer.view.AddCustomerView;
import com.jcim.fdk.customer.view.AddCustomerViewImpl;
import com.jcim.fdk.details.view.DetailsView;
import com.jcim.fdk.details.view.DetailsViewImpl;
import com.jcim.fdk.history.view.HistoryView;
import com.jcim.fdk.history.view.HistoryViewImpl;
import com.jcim.fdk.i18n.WfsMessageSource;
import com.jcim.fdk.itemdetails.view.ItemDetailsView;
import com.jcim.fdk.itemdetails.view.ItemDetailsViewImpl;
import com.jcim.fdk.mvp.StoppablePlaceController;
import com.vaadin.ui.UI;

@Configuration
public class MVPFrameworkConfig {

	private @Autowired UI ui;
	
	private @Autowired WfsMessageSource messageSource;
	
	private @Autowired PersistenceConfig persi;
	
	public @Bean Shell shell() {
		return new ShellImpl(mainWindow());
	}
	
	public @Bean PlaceController placeController() {
		return new StoppablePlaceController(eventBus(), shell());
	}
	
	public @Bean EventBus eventBus() {
		return new SimpleEventBus();
	}
	
	public @Bean MainWindow mainWindow() {
		return new MainWindow(ui);		
	}
		
	public @Bean AddCustomerView addCustomerView() {
		return new AddCustomerViewImpl(messageSource);
	}
		
	@Bean
    public ItemDetailsView itemDetailsView() {
		return new ItemDetailsViewImpl();
	}
	
	@Bean
	public DetailsView detailsView() {
		return new DetailsViewImpl();
	}
	
	@Bean 
	public HistoryView historyView() {
		return new HistoryViewImpl(persi);
	}	
}
