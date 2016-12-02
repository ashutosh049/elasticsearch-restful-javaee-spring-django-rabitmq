package com.iscas.dsgroup.securitysearch.annotation.validate.File;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.iscas.dsgroup.securitysearch.annotation.validate.AbstractFileParamValidate;
import com.iscas.dsgroup.securitysearch.common.ConstantsSetting;
import com.iscas.dsgroup.securitysearch.pojo.FilePojo;
import com.iscas.dsgroup.securitysearch.exception.Error;

/**
 * 
 * @author lennyBai
 *
 */

public class MissingFilePath extends AbstractFileParamValidate {

	@Override
	public void validate(FilePojo filePojo, List<Error> errorList) {
		// TODO Auto-generated method stub
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++ " + filePojo.getFilePath());
		if (StringUtils.isBlank(filePojo.getFilePath())) {
			errorList.add(
					new Error(ConstantsSetting.ERR_FILEPATH_MISSING, new Object[] { ConstantsSetting.FILEPATH_CONTENT }));
		}
	}

}
