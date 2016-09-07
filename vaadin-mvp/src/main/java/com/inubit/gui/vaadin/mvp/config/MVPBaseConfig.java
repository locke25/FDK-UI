package com.inubit.gui.vaadin.mvp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.event.SimpleEventBus;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.shell.Shell;

@Configuration
public class MVPBaseConfig {

	private @Autowired Shell shell;

	
	public @Bean PlaceController placeController() {
		return new PlaceController(eventBus(), shell);
	}
	
	public @Bean EventBus eventBus() {
		return new SimpleEventBus();
	}
	

}
