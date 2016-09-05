package com.jcim.fdk.details.event;

import com.inubit.gui.vaadin.mvp.event.Event;
import com.inubit.gui.vaadin.mvp.event.EventHandler;

public class CategorySelectedEvent  implements Event<CategorySelectedEvent.Handler>{

	public interface Handler extends EventHandler {
		public void update(long categoryId);
	}
	
	private final long categoryId;
	
	public CategorySelectedEvent(final long categoryId) {
		this.categoryId = categoryId;
	}
	
	@Override
	public void dispatch(Handler handler) {
		handler.update(categoryId);
	}
}
