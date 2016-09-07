package com.inubit.gui.vaadin.mvp.shell.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inubit.gui.vaadin.mvp.event.HandlerRegistration;
import com.inubit.gui.vaadin.mvp.shell.FragmentChangedEvent;
import com.inubit.gui.vaadin.mvp.shell.FragmentChangedHandler;
import com.inubit.gui.vaadin.mvp.shell.Fragmenter;
import com.inubit.gui.vaadin.mvp.shell.Shell;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;


/**
 * Implements the methods to handle URI fragment changes.
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractShell implements Shell, UriFragmentChangedListener{

    private static final Logger log = LoggerFactory.getLogger(AbstractShell.class);

    private Collection<FragmentChangedHandler> handlers = new ArrayList<FragmentChangedHandler>();

    protected String id;

    public AbstractShell(String id) {
        this.id = id;
    }

    @Override
    public String getFragment() 
    {
        final String fragment = getUriFragment();
        log.debug("complete uri fragment is {}", fragment);

        final Fragmenter fragmenter = new Fragmenter(fragment);
        return fragmenter.getSubFragment(id);
    }

    @Override
    public void setFragment(String fragment) {
        final String currentCompleteFragment = getUriFragment();
        final Fragmenter fragmenter = new Fragmenter(currentCompleteFragment);
        log.debug("current uri fragment is {}", currentCompleteFragment);

        // only change the uri if the value has changed, other don't bother Vaadin
        String currentFragment = fragmenter.getSubFragment(id);
        if(currentFragment == null || !currentFragment.equals(fragment)){
            fragmenter.setSubFragment(id, fragment);
            final String newFragment = fragmenter.toString();
            log.debug("setting uri fragment to {}", newFragment);
            Page.getCurrent().setUriFragment(newFragment, false);
        }
    }

    @Override
    public HandlerRegistration addFragmentChangedHandler(final FragmentChangedHandler handler) {
        log.debug("adding listener {}", handler);

        if(handlers.size()==0){
            log.debug("adding listener {}", this);
            Page.getCurrent().addUriFragmentChangedListener(this);
        }
        handlers.add(handler);
        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                removeFragmentChangedHandler(handler);
            }
        };
    }

    private void removeFragmentChangedHandler(FragmentChangedHandler handler) {
        log.debug("removing listener {}", handler);

        handlers.remove(handler);
        if(handlers.size()==0){
            log.debug("removing listener {}", this);
            Page.getCurrent().removeUriFragmentChangedListener(this);
        }
    }

    @Override
    public void uriFragmentChanged(UriFragmentChangedEvent event) 
    {
        final Fragmenter fragmenter = new Fragmenter(event.getUriFragment());
        final String subFragment = fragmenter.getSubFragment(id);
        if(subFragment != null){
            for (FragmentChangedHandler listener : handlers) {
                log.debug("firing info.magnolia.ui.framework.shell.FragmentChangedEvent with sub fragment {}", subFragment);
                listener.onFragmentChanged(new FragmentChangedEvent(subFragment));
            }
        }
    }

    @Override
    public Shell createSubShell(String id) {
        return new SubShell(id, this);
    }
    
    protected String getUriFragment()
    {
    	return Page.getCurrent().getUriFragment();
    }

}
