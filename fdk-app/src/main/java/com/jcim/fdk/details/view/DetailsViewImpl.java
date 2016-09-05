package com.jcim.fdk.details.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.inubit.gui.vaadin.mvp.view.impl.IsVaadinComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class DetailsViewImpl extends CssLayout implements DetailsView,
		IsVaadinComponent {

	private static final Logger log = LoggerFactory
			.getLogger(DetailsViewImpl.class);

	private Presenter presenter;
	private CssLayout layout;

	private Label labtestString;
	private Label labtestBoolean;
	private Label labtestEnumAsOrdinalValue;
	
	private TextField testString;
	private TextField testBoolean;
	private TextField testEnumAsOrdinalValue;
	
	private Button btnCreate;
	
	private ResourceBundleMessageSource messageSource;
	
	public DetailsViewImpl() {
		this.messageSource = messageSource;		

		layout = buildContentDisplay();
		buildMainLayout();
		setStyleName("detailsViewContent");
		
		layout.addComponent(new Label("SubPlaceOne"));
	}

	@Override
	public Component asVaadinComponent() {
		return this;
	}

	private CssLayout buildContentDisplay() {

		CssLayout mainLayout = new CssLayout();
		mainLayout.setImmediate(false);		
		
		labtestString = new Label("labtestString");
		labtestBoolean = new Label("labtestBoolean");
		labtestEnumAsOrdinalValue = new Label("labtestEnumAsOrdinalValue"); 
		
		testString = new TextField();
		testBoolean = new TextField();
		testEnumAsOrdinalValue = new TextField();
		
		CssLayout layOne = new CssLayout();
		CssLayout layTwo = new CssLayout();
		CssLayout layThree = new CssLayout();
		
		layOne.addComponent(labtestString);
		layOne.addComponent(testString);
		
		layTwo.addComponent(labtestBoolean);
		layTwo.addComponent(testBoolean);
		
		layThree.addComponent(labtestEnumAsOrdinalValue);
		layThree.addComponent(testEnumAsOrdinalValue);
		
		btnCreate = new Button("Create Entity");
		
		btnCreate.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				String t1 = testString.getValue();
				String t2 = testBoolean.getValue();
				String t3 = testEnumAsOrdinalValue.getValue();
				
				presenter.createEntity(t1, t2, t3);
			}
		});

		mainLayout.addComponent(layOne);
		mainLayout.addComponent(layTwo);
		mainLayout.addComponent(layThree);
		mainLayout.addComponent(btnCreate);
		
		this.addComponent(mainLayout);
		return mainLayout;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;		
	}

	
	private void buildMainLayout() {

	}


}
