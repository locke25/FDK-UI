package com.jcim.fdk.i18n;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.vaadin.ui.UI;

/**
 * Simple wrapper class for {@link ResourceBundleMessageSource} to simplify
 * the retrieving of a localized message.
 * 
 * @author o.prinz@binaere-bauten.de
 *
 */
public class WfsMessageSource extends ResourceBundleMessageSource {

	public String getMessage(String code, Object[] args) throws NoSuchMessageException 
	{
		return super.getMessage(code, args, UI.getCurrent().getLocale());
	}
	
	public String getMessage(String code)
	{
		return getMessage(code, null);
	}
}
