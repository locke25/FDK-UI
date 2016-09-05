package com.jcim.fdk;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jcim.fdk.config.AppConfig;
import com.jcim.fdk.i18n.WfsMessageSource;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * Application start.
 * 
 * @author o.prinz@binaere-bauten.de
 *
 */
@Theme("taskmanager-apptheme")
public class WfsUI extends UI {
	
	private static final long serialVersionUID = 1L;

	private WfsPresenter wfsPresenter;	

	
	/**
	 * Servlet context this UI runs in.
	 */
	private ServletContext servletContext;

	/**
	 * The root application context for this application.
	 */
    private ApplicationContext rootContext;
	
	@Override
	public void init(VaadinRequest request) 
	{
		servletContext = VaadinServlet.getCurrent().getServletContext();
        rootContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		
		final AnnotationConfigApplicationContext ctx = setupApplicationContext();
		wfsPresenter = new WfsPresenter(ctx);
		
		wfsPresenter.init();	
	}
	
	
	private AnnotationConfigApplicationContext setupApplicationContext() {
		
		WfsMessageSource messageSource = new WfsMessageSource();
		messageSource.setBasenames("l10n", "l10n_en");
		
		final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.setParent(rootContext);	
		ctx.getBeanFactory().registerSingleton("ui", this);
		ctx.getBeanFactory().registerSingleton("messageSource", messageSource);
		ctx.register(AppConfig.class);
		ctx.refresh();
		return ctx;
	}
}
