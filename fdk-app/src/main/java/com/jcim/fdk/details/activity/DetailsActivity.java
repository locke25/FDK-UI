package com.jcim.fdk.details.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.inubit.gui.vaadin.mvp.activity.AbstractActivity;
import com.inubit.gui.vaadin.mvp.event.EventBus;
import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceController;
import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.inubit.gui.vaadin.mvp.view.ViewPort;
import com.jcim.fdk.config.PersistenceConfig;
import com.jcim.fdk.config.RemotePersistenceConfig;
import com.jcim.fdk.details.event.TestDaoPushInFrontendEvent;
import com.jcim.fdk.details.view.DetailsView;
import com.jcim.fdk.itemdetails.view.ItemDetailsView;
import com.jcim.fdk.model.Test.TestEnum;


public class DetailsActivity extends AbstractActivity implements DetailsView.Presenter {

    private static final Logger log = LoggerFactory.getLogger(DetailsActivity.class);

    private DetailsView view;
    
    private Shell shell;
    
    private EventBus eventBus;    
    private ResourceBundleMessageSource messageSource;
    
    private PlaceController placeController;
    
    private ItemDetailsView itemDetailsView;
    
    private RemotePersistenceConfig remotePersistenceConfig;
    private PersistenceConfig persistenceConfig;

    public DetailsActivity(ApplicationContext ctx, Place parentPlace) {
        view = ctx.getBean(DetailsView.class);
        view.setPresenter(this);
        messageSource = ctx.getBean(ResourceBundleMessageSource.class);        
        shell = ctx.getBean(Shell.class);        
        placeController = ctx.getBean(PlaceController.class);        
        itemDetailsView = ctx.getBean(ItemDetailsView.class);
        this.remotePersistenceConfig = ctx.getBean(RemotePersistenceConfig.class);
        this.persistenceConfig = ctx.getBean(PersistenceConfig.class);
        showData(1);
    }

    @Override
    public void start(ViewPort display, EventBus eventBus) {
        this.eventBus = eventBus;
        display.setView(view);
    }

    @Override
    public void update(Place place) {
        log.info("Update DetailsActivity");
        showData(1);
    }


    private void showData(final long itemId) {
//        view.set
    }

    public void createEntity(String testString, String testBoolean, String testEnum) {
    	boolean result = remotePersistenceConfig.messageService().sendMessage(testString, testBoolean, testEnum);
    	
    	
//    	persistenceConfig.testDao().create(testString, Boolean.valueOf(testBoolean), TestEnum.valueOf(testEnum) );
    	if (result)
    		eventBus.fireEvent(new TestDaoPushInFrontendEvent());
    }
    
}
