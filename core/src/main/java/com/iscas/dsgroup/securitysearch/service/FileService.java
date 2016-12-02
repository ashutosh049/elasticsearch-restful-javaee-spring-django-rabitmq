package com.iscas.dsgroup.securitysearch.service;

import com.iscas.dsgroup.securitysearch.pojo.CommonParam;
import com.iscas.dsgroup.securitysearch.pojo.FilePojo;
import com.iscas.dsgroup.securitysearch.pojo.ResponseElement;
import com.iscas.dsgroup.securitysearch.pojo.UserPojo;

/**
 * 
 * @author lennyBai
 *
 */
public interface FileService {

	ResponseElement fileIndex(String searchIndex, String searchType, CommonParam commonParam);

	ResponseElement fileUpdate(String searchIndex, String searchType, CommonParam commonParam);

	ResponseElement fileSearch(String searchIndex, String searchType, CommonParam commonParam);
}