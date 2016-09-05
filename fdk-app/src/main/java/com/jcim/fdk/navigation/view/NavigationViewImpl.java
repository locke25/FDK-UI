package com.jcim.fdk.navigation.view;

import java.util.Iterator;
import java.util.Locale;

import com.inubit.gui.vaadin.mvp.view.impl.IsVaadinComponent;
import com.jcim.fdk.itemdetails.place.ItemDetailsPlace;
import com.jcim.fdk.navigation.place.NavigationPlace;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;

public class NavigationViewImpl extends CustomComponent implements NavigationView, IsVaadinComponent {

	private CssLayout mainLayout;

	private Presenter presenter;

	public NavigationViewImpl(final NavigationPlace[] places) {
		
		if (places[0] instanceof ItemDetailsPlace) {
			buildMainLayoutDetail(places);
		} else {
			buildMainLayout(places);
		}	
		
		setCompositionRoot(mainLayout);
	}

	@Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void select(NavigationPlace place) {
    	
    	System.out.println("NavigationViewImpl.select() "+place.getLabel());    	
    }

    @Override
    public Component asVaadinComponent() {
        return this;
    }

	private CssLayout buildMainLayout(final NavigationPlace[] places) {
		// common part: create layout
		mainLayout = new CssLayout();
		mainLayout.setImmediate(false);
//		mainLayout.setWidth("100%");
//		mainLayout.setHeight("100%");
		mainLayout.setStyleName("menuOverall");
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		for(NavigationPlace navPlace: places) {
			final Button b = new Button();
			b.setCaption(navPlace.getLabel());
			b.setImmediate(false);
			b.setId(navPlace.getLabel() + "ButtonId");
//			b.setWidth("-1px");
//			b.setHeight("-1px");
			final NavigationPlace place = navPlace;
	        b.addListener(new ClickListener() {

	            @Override
	            public void buttonClick(ClickEvent event) {
	                if (presenter != null) {
	                    presenter.onMenuSelection(place);
	                    setStylesForColorToggeling(mainLayout, b);	                    
	                }
	            }
				
	        });
			mainLayout.addComponent(b);
		}
		
		//FirstElement selected
		mainLayout.getComponent(0).setStyleName("selectedButton");
		
		return mainLayout;
	}
	
	
	private CssLayout buildMainLayoutDetail(final NavigationPlace[] places) {
		
		mainLayout = new CssLayout();
		mainLayout.setImmediate(false);		
		mainLayout.setStyleName("menuOverall");
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		for(NavigationPlace navPlace: places) {
			final Button b = new Button();			
			b.setCaption(navPlace.getLabel());
			b.setImmediate(false);
			final NavigationPlace place = navPlace;
	        b.addListener(new ClickListener() {

	            @Override
	            public void buttonClick(ClickEvent event) {
	                if (presenter != null) {
	                    presenter.onMenuSelection(place);
	                }
	            }
				
	        });
			mainLayout.addComponent(b);
		}
		return mainLayout;
	}
	
	
	private void setStylesForColorToggeling(
			CssLayout mainLayout, Button b) {
		
		Iterator<Component> it = mainLayout.getComponentIterator(); 
		while (it.hasNext()) {
			it.next().removeStyleName("selectedButton");
		}
		
		b.setStyleName("selectedButton"); 		
	}
}
