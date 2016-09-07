package com.inubit.gui.vaadin.mvp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.inubit.gui.vaadin.mvp.shell.impl.ShellImpl;
import com.inubit.gui.vaadin.mvp.view.impl.MainWindow;

@Configuration
@Import(MVPBaseConfig.class)
public class MVPFrameworkConfig {

	private @Autowired MainWindow mainWindow;

	public @Bean Shell shell() {
		return new ShellImpl(mainWindow);
	}

}
