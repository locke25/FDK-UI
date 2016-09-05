package com.jcim.fdk.customer.view;

import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.view.impl.IsVaadinComponent;
import com.jcim.fdk.customer.activity.AddCustomerActivity;
import com.jcim.fdk.i18n.WfsMessageSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class AddCustomerViewImpl extends CssLayout implements AddCustomerView, IsVaadinComponent
{
	private static final long serialVersionUID = 1L;
		
	private WfsMessageSource messageSource;
	
	private AddCustomerActivity presenter;
	
	private TextField 
		companyNameTextField, companyStreetAndNumberTextField, 
		companyZipCodeTextField, companyCityTextField, 
		companyStationTextField, companyLavTextField,
		companyIndustryTextField, companyCountryTextField;
	
	private Button addCustomerButton, cancelButton;
	
	public AddCustomerViewImpl(WfsMessageSource messageSource) 
	{
		this.messageSource = messageSource;
		buildView();
	}
	
	public void setPresenter(AddCustomerActivity presenter)
	{
		this.presenter = presenter;
	}
	
	public void setContext(ApplicationContext context)
	{
//		this.context = context;
	}
	
	private void buildView()
	{
		GridLayout addCustomerGrid = new GridLayout(4, 7);
		addCustomerGrid.setWidth(100, Unit.PERCENTAGE);
		addCustomerGrid.setMargin(true);
		addCustomerGrid.setSpacing(true);
		
		addCustomerGrid.addComponent(
				new Label(messageSource.getMessage("customer.add.company.name")), 0, 1);
		addCustomerGrid.addComponent(
				new Label(messageSource.getMessage("customer.add.street")), 2, 1);
		addCustomerGrid.addComponent(
				new Label(messageSource.getMessage("customer.add.zip")), 0, 2);
		addCustomerGrid.addComponent(
				new Label(messageSource.getMessage("customer.add.city")), 2, 2);
		addCustomerGrid.addComponent(
				new Label(messageSource.getMessage("customer.add.industry")), 0, 3);
		addCustomerGrid.addComponent(
				new Label(messageSource.getMessage("customer.add.country")), 2, 3);
		addCustomerGrid.addComponent(
				new Label(messageSource.getMessage("customer.add.station")), 0, 4);
		addCustomerGrid.addComponent(
				new Label(messageSource.getMessage("customer.add.lav")), 2, 4);
		
		companyNameTextField = new TextField();
		companyNameTextField.setImmediate(true);
		companyNameTextField.setId("customer_company");
		addCustomerGrid.addComponent(companyNameTextField, 1, 1);
		
		companyStreetAndNumberTextField = new TextField();
		companyStreetAndNumberTextField.setImmediate(true);
		companyStreetAndNumberTextField.setId("customer_streetAndNumber");
		addCustomerGrid.addComponent(companyStreetAndNumberTextField, 3, 1);
		
		companyZipCodeTextField = new TextField();
		companyZipCodeTextField.setImmediate(true);
		companyZipCodeTextField.setId("customer_zipcode");
		addCustomerGrid.addComponent(companyZipCodeTextField, 1, 2);
		
		companyCityTextField = new TextField();
		companyCityTextField.setImmediate(true);
		companyCityTextField.setId("customer_city");
		addCustomerGrid.addComponent(companyCityTextField, 3, 2);
		
		companyStationTextField = new TextField();
		companyStationTextField.setImmediate(true);
		companyStationTextField.setId("customer_companyStation");
		addCustomerGrid.addComponent(companyStationTextField, 1, 4);
		
		companyLavTextField = new TextField();
		companyLavTextField.setImmediate(true);
		companyLavTextField.setId("customer_companyLav");
		addCustomerGrid.addComponent(companyLavTextField, 3, 4);
		
		addCustomerButton = new Button(messageSource.getMessage("customer.add.button.save"));
		addCustomerButton.setImmediate(true);
		addCustomerButton.setId("customer_save");
		addCustomerGrid.addComponent(addCustomerButton, 2, 6);
		
		cancelButton = new Button(messageSource.getMessage("customer.add.button.cancel"));
		cancelButton.setId("customer_cancel");
		cancelButton.setImmediate(true);
		
		addCustomerGrid.addComponent(cancelButton, 3, 6);
		
		addComponent(addCustomerGrid);
	}

	

	
	@Override
	public Component asVaadinComponent() 
	{
		return this;
	}	

}
