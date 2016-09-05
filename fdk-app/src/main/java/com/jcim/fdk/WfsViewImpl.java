package com.jcim.fdk;

import com.inubit.gui.vaadin.mvp.view.impl.IsVaadinComponent;
import com.inubit.gui.vaadin.mvp.view.impl.MainWindow;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;

/**
 * Defines the base view for the application. Contains a menu
 * for navigation (menuLayout) and the viewport (mainLayout).
 * 
 * @author o.prinz@binaere-bauten.de
 *
 */
public class WfsViewImpl extends CssLayout implements WfsView, IsVaadinComponent {

	/**
	 * Menu navigation.
	 */
	private CssLayout menuLayout;
	
	/**
	 * Main viewport for the application.
	 */
	private CssLayout mainLayout;
	
	/**
	 * Logo.
	 */
	private Embedded logobosch;

	/**
	 * Default constructor.
	 * 
	 * @param mainWindow
	 */
	public WfsViewImpl(MainWindow mainWindow) 
	{
		buildMenuLayout();
		addComponent(menuLayout);
		buildMainLayout();
		addComponent(mainLayout);
		
		mainWindow.setContent(this);
	}

    @Override
    public Component asVaadinComponent() 
    {
        return this;
    }

	private CssLayout buildMenuLayout() 
	{			
		// common part: create layout
		menuLayout = new CssLayout();
		menuLayout.setImmediate(false);
		menuLayout.setStyleName("menuLayoutMain");
				
		logobosch = new Embedded();
		logobosch.setImmediate(false);
		logobosch.setWidth("144px");
		logobosch.setSource(new ThemeResource("img/logo-bosch.png"));
		logobosch.setType(1);
		logobosch.setMimeType("image/png");
		
		menuLayout.addComponent(logobosch);
		
		return menuLayout;
	}

	private CssLayout buildMainLayout() 
	{
		// common part: create layout
		mainLayout = new CssLayout();
		mainLayout.setImmediate(false);
		
		return mainLayout;
	}

	@Override
	public ComponentContainer getMainContainer() 
	{
		return mainLayout;
	}

	@Override
	public ComponentContainer getMenuDisplay() 
	{
		return menuLayout;
	}

}
