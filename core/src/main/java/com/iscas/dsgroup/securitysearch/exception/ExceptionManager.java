package com.iscas.dsgroup.securitysearch.exception;

/**
 * @author lennyBai
 */

import java.util.List;

public abstract interface ExceptionManager {
	public abstract void fireSystemException(Exception paramException);

	public abstract void fireUserException(String paramString, Object[] paramArrayOfObject);

	public abstract void fireValidationException(List<Error> paramList);
}