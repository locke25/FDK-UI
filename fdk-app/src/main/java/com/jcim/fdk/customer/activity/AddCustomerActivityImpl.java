package com.jcim.fdk.customer.activity;

import org.springframework.context.ApplicationContext;

import com.inubit.gui.vaadin.mvp.activity.AbstractActivity;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.jcim.fdk.customer.view.AddCustomerView;
import com.jcim.fdk.dao.CustomerDao;

public class AddCustomerActivityImpl extends AbstractActivity implements AddCustomerActivity
{

	private ApplicationContext context;
	
	private AddCustomerView view;
	
	private CustomerDao customerDao;
	
    public AddCustomerActivityImpl(ApplicationContext context) {
        this.context = context;

        this.customerDao = context.getBean(CustomerDao.class);        
    }

    @Override
    public void start(ViewPort viewPort, EventBus eventBus) {
        view = context.getBean(AddCustomerView.class);
        view.setContext(context);
        view.setPresenter(this);
        viewPort.setView(view);
    }    
   
}
