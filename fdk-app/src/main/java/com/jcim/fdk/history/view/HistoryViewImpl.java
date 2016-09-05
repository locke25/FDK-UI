package com.jcim.fdk.history.view;

import java.util.ArrayList;
import java.util.List;

import com.inubit.gui.vaadin.mvp.view.impl.IsVaadinComponent;
import com.jcim.fdk.config.PersistenceConfig;
import com.jcim.fdk.details.view.DetailsView.Presenter;
import com.jcim.fdk.model.Test;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;

public class HistoryViewImpl extends CssLayout implements HistoryView, IsVaadinComponent {

	private CssLayout layout;
	private Table history;

	private ArrayList<Object> visibleColumnIds = new ArrayList<Object>();
	private ArrayList<String> visibleColumnLabels = new ArrayList<String>();	

	private TextArea textArea;
	private Presenter presenter;
	
	private PersistenceConfig persi;
	
	public HistoryViewImpl(PersistenceConfig persi) {
	   		
		this.persi = persi;
//		buildColumns();		
//
//		buildTable();
		layout = buildContentDisplay();
		
		layout.addComponent(new Label("SubPlaceTwo"));
		
	}

	private void buildColumns() {
		
		visibleColumnIds.add("action");
		visibleColumnIds.add("date");
		visibleColumnIds.add("editor");
		visibleColumnIds.add("slaState");
		visibleColumnIds.add("priority");
		visibleColumnIds.add("category");

		visibleColumnLabels.add("Aktion");
		visibleColumnLabels.add("Datum");
		visibleColumnLabels.add("Bearbeiter");
		visibleColumnLabels.add("SLA-Status");
		visibleColumnLabels.add("Priorität");
		visibleColumnLabels.add("Kategorie");
	}
	

	private void buildTable() {
		history = new Table();
		
		history.setImmediate(true);

		history.setContainerDataSource(null);

		history.setVisibleColumns(visibleColumnIds.toArray());
		history.setColumnHeaders(visibleColumnLabels.toArray(new String[0]));	
	}

	@Override
	public Component asVaadinComponent() {
		return this;
	}

	private CssLayout buildContentDisplay() {

		CssLayout mainLayout = new CssLayout();
		mainLayout.setImmediate(false);		

		textArea = new TextArea();
		textArea.setImmediate(true);
		
		String contentFromDao = getDataFromTestDao();
		
		textArea.setValue(contentFromDao);
		
		mainLayout.addComponent(textArea);
//		mainLayout.addComponent(history);

		this.addComponent(mainLayout);		
		
		return mainLayout;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void updateData(long itemId) {
		
	}
	
	@Override
	public void setData() {
		String contentFromDao = presenter.getDataFromTestDao();
		
		textArea.setValue(contentFromDao);
	}

	
	private String getDataFromTestDao() {
		List<Test> testEntities = persi.testDao().retrieveAll();
		
		StringBuffer stb = new StringBuffer();
		for(Test test : testEntities) { 
			stb.append(test.getId() + " " + test.getTestString() + " " + test.getTestEnum().toString() + " "  + test.isTestBoolean() +  " \n");
		}
		return stb.toString();
	}
	
}
