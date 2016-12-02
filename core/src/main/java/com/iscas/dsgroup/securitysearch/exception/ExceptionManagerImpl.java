package com.iscas.dsgroup.securitysearch.exception;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iscas.dsgroup.securitysearch.localization.LocalizationManager;
import com.iscas.dsgroup.securitysearch.localization.LocalizationManagerImpl;
import com.iscas.dsgroup.securitysearch.pojo.ResponseElement;

/**
 * 
 * @author lennyBai
 *
 */

public class ExceptionManagerImpl implements ExceptionManager {

	private static final Logger logger = Logger.getLogger(ExceptionManagerImpl.class);

	@Autowired
	private LocalizationManager localizationManager;
	
	public ExceptionManagerImpl() {
	}

	public void fireSystemException(Exception e) {
		throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.APPLICATION_JSON).entity(new ResponseElement("SystemError", e.toString())).build());
	}

	public void fireUserException(String code, Object[] args) {

		String message = this.localizationManager.getMessage(code, args);
		
		throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
				.entity(ResponseElement.newFailedInstance(message)).type("application/json;charset=utf-8").build());
	}

	public void fireValidationException(List<Error> error) {

		logger.debug("..");

		String[] messages = new String[error.size()];

		for (int i = 0; i < error.size(); i++) {
			Error err = (Error) error.get(i);
			messages[i] = this.localizationManager.getMessage(err.getCode(), err.getArgs());
		}

		throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
				.entity(ResponseElement.newFailedInstance(StringUtils.join(messages, ", ")))
				.type("application/json;charset=utf-8").build());
	}
}