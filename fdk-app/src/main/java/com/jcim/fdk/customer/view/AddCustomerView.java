package com.jcim.fdk.customer.view;

import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.view.View;
import com.jcim.fdk.customer.activity.AddCustomerActivity;

/**
 * 
 * The @AddCustomerView is responsible for the view to add a customer. The view renders the form 
 * with all the necessary fields to add a customer.
 * 
 * @author michael.hewing@bosch-si.com
 *
 */
public interface AddCustomerView extends View 
{
	public void setPresenter(AddCustomerActivity presenter);
	
	public void setContext(ApplicationContext context);
}
