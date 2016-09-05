package com.jcim.fdk.details.event;

import com.inubit.gui.vaadin.mvp.event.Event;
import com.inubit.gui.vaadin.mvp.event.EventHandler;

public class TestDaoPushInFrontendEvent  implements Event<TestDaoPushInFrontendEvent.Handler>{

	public interface Handler extends EventHandler {
		public void update();
	}
	
		
	public TestDaoPushInFrontendEvent() {
		
	}
	
	@Override
	public void dispatch(Handler handler) {
		handler.update();
	}
}
