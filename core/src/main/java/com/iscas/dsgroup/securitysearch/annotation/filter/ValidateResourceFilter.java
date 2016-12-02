package com.iscas.dsgroup.securitysearch.annotation.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.iscas.dsgroup.securitysearch.annotation.validate.Validate;
import com.iscas.dsgroup.securitysearch.exception.ExceptionManager;
import com.iscas.dsgroup.securitysearch.util.FilterHelper;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

/**
 * 
 * @author lennyBai
 *
 */

public class ValidateResourceFilter implements ResourceFilter, ContainerRequestFilter {

	private ExceptionManager exceptionManager;
	private Class<?> target;
	private List<Validate> list;

	protected ValidateResourceFilter(Class<?> target, List<Validate> list, ExceptionManager exceptionManager) {
		this.target = target;
		this.list = list;
		this.exceptionManager = exceptionManager;
	}

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		// TODO Auto-generated method stub
		try {
			Object object = FilterHelper.getEntity(this.target, request);

			List validateError = new ArrayList();

			for (Validate validateClass : this.list) {
				validateClass.validate(object, validateError);
			}

			if (!validateError.isEmpty()) {
				this.exceptionManager.fireValidationException(validateError);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.exceptionManager.fireSystemException(e);
		}

		return request;
	}

	@Override
	public ContainerRequestFilter getRequestFilter() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		// TODO Auto-generated method stub
		return null;
	}

}
