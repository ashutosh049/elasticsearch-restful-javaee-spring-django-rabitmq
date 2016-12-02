package com.iscas.dsgroup.securitysearch.annotation.validate;

import java.util.List;
import com.iscas.dsgroup.securitysearch.exception.Error;

/**
 * 
 * @author lennyBai
 *
 */

public abstract interface Validate {
	public abstract void validate(Object paramObject, List<Error> paramList);
}