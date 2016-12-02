package com.iscas.dsgroup.securitysearch.annotation.validate;

import java.util.List;

import com.iscas.dsgroup.securitysearch.pojo.CommonParam;
import com.iscas.dsgroup.securitysearch.pojo.FilePojo;
import com.iscas.dsgroup.securitysearch.exception.Error;

/**
 * 
 * @author lennyBai
 *
 */

public abstract class AbstractFileParamValidate implements Validate {

	@Override
	public void validate(Object paramObject, List<Error> errorList) {
		// TODO Auto-generated method stub

		FilePojo filePojo = ((CommonParam) paramObject).getFilePojo();
		validate(filePojo, errorList);

	}

	abstract public void validate(FilePojo filePojo, List<Error> errorList);

}
