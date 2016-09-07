/*package com.inubit.gui.vaadin.mvp.widget.historian.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.History;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.ui.VUriFragmentUtility;

public class VHistorian extends VUriFragmentUtility {

    // MAGNOLIA this is a copy because the variable are private
    private String fragment;
    private ApplicationConnection client;
    private String paintableId;
    private boolean immediate;

    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        if (client.updateComponent(this, uidl, false)) {
            return;
        }
        String uidlFragment = uidl.getStringVariable("fragment");
        immediate = uidl.getBooleanAttribute("immediate");
        if (this.client == null) {
            // initial paint has some special logic
            this.client = client;
            paintableId = uidl.getId();
            fragment = History.getToken();

            // MAGNOLIA: if the fragment on startup is not empty we have to inform the server
            // otherwise we just add the received fragment.
            if(!fragment.equals("")){
                History.fireCurrentHistoryState();
            }
            else{
                History.newItem(uidlFragment, false);
            }
        } else {
            if (uidlFragment != null && !uidlFragment.equals(fragment)) {
                fragment = uidlFragment;
                // normal fragment change from server, add new history item
                History.newItem(uidlFragment, false);
            }
        }
    }

    // MAGNOLIA this is a copy because the variable are private
    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String historyToken = event.getValue();
        fragment = historyToken;
        if (client != null) {
            client.updateVariable(paintableId, "fragment", fragment, immediate);
        }
    }

}
*/