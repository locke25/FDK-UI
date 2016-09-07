package com.inubit.gui.vaadin.mvp.view.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.ui.UI;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

/**
 * Creates the main window. Classes that depend on having a window created can depend on this class.
 *
 */
public class MainWindow {

	/**
	 * Application object.
	 */
    private UI ui;  
    
    public UI getUI() {
    	return ui;
    }
  
    /**
     * Permanent components to display on every page.
     */
    private Collection<Component> permanentComponents = new ArrayList<Component>();

    /**
     * Constructor.
     * 
     * @param ui
     * 		Vaadin UI object.
     */
    public MainWindow(UI ui) {
        this.ui = ui;
    }

    /**
     * Sets a new content in the ui and adds all permanent components
     * to this new content.
     * 
     * @param newContent
     * 		New content object to set in the ui.
     */
    public void setContent(ComponentContainer newContent) {
        ui.setContent(newContent);
        for (Component component : permanentComponents) {
            newContent.addComponent(component);
        }
    }

    public void addPermanentComponent(Component component) {
        permanentComponents.add(component);
        ((ComponentContainer)ui.getContent()).addComponent(component);
    }

    /**
     * Sets the page title.
     * 
     * @param title
     * 		Title of the page.
     */
    public void setTitle(String title) {
        ui.getPage().setTitle(title);
    }
}
