package com.iscas.dsgroup.securitysearch.persistence;

import com.iscas.dsgroup.securitysearch.pojo.CommonParam;
import com.iscas.dsgroup.securitysearch.pojo.FilePojo;
import com.iscas.dsgroup.securitysearch.pojo.ResponseElement;
import com.iscas.dsgroup.securitysearch.pojo.UserPojo;

/**
 * 
 * @author lennyBai
 *
 */

public interface FilePersistence {

	ResponseElement fileIndex(String searchIndex, String searchType, CommonParam commonParam);

	ResponseElement fileUpate(String searchIndex, String searchType, CommonParam commonParam);

	ResponseElement fileSearch(String searchIndex, String searchType, CommonParam commonParam);

	ResponseElement fileSearchByFileContent(String searchIndex, String searchType, CommonParam commonParam);

	ResponseElement fileSearchByFileNameAndTag(String searchIndex, String searchType, CommonParam commonParam);

	/*
	 * match from filecontent,fileName and File Tag
	 */
	ResponseElement fileSearchByKeyword(String searchIndex, String searchType, CommonParam commonParam);

}