package com.iscas.dsgroup.securitysearch.annotation.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iscas.dsgroup.securitysearch.annotation.ValidatedBy;
import com.iscas.dsgroup.securitysearch.annotation.validate.Validate;
import com.iscas.dsgroup.securitysearch.annotation.validate.File.MissingFilePath;
import com.iscas.dsgroup.securitysearch.exception.ExceptionManager;
import com.iscas.dsgroup.securitysearch.exception.ExceptionManagerImpl;
import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;

/**
 * 
 * @author lennyBai
 *
 */

@Component
public class ResourceFilterFactoryImpl implements ResourceFilterFactory {

	@Autowired
	private ExceptionManager excpetionManager;

	@Autowired
	private Map<String, Validate> map;

	@Override
	public List<ResourceFilter> create(AbstractMethod am) {

		ArrayList filter = new ArrayList();

		if (am.isAnnotationPresent(ValidatedBy.class)) {

			ValidatedBy validatedParam = (ValidatedBy) am.getAnnotation(ValidatedBy.class);

			List list = new ArrayList();
			String[] ValidateValue = validatedParam.value();

			for (String validateName : ValidateValue) {

				System.out.println("======================ready for validate: " + validateName);
				Validate validateClass = (Validate) this.map.get(validateName);
				// Validate validateClass =
				// com.iscas.dsgroup.securitysearch.annotation.validate.File.MissingFilePath();
				if (validateClass != null) {
					list.add(validateClass);
				} else {
					System.out.println("============================no validate found ");
				}
			}

			Class[] target = am.getMethod().getParameterTypes();
			if ((list.size() > 0) && (target.length > 0)) {
				filter.add(new ValidateResourceFilter(target[0], list, this.excpetionManager));
			}
		}

		if (filter.isEmpty()) {
			return null;
		}

		// TODO Auto-generated method stub
		return Collections.unmodifiableList(filter);
	}
}
