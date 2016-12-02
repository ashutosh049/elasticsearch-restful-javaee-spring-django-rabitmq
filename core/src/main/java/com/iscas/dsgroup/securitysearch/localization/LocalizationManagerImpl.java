package com.iscas.dsgroup.securitysearch.localization;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class LocalizationManagerImpl implements LocalizationManager {

	@Autowired
	MessageSource messagerSource;
	
	@Override
	public String getMessage(String paramString, Object[] paramArrayOfObject) {
		return this.messagerSource.getMessage(paramString, paramArrayOfObject, new Locale("en", "US"));
	}

}
