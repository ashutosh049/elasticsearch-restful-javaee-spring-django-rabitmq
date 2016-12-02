package com.iscas.dsgroup.securitysearch.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iscas.dsgroup.securitysearch.common.ConstantsSetting;
import com.iscas.dsgroup.securitysearch.persistence.FilePersistence;
import com.iscas.dsgroup.securitysearch.pojo.CommonParam;
import com.iscas.dsgroup.securitysearch.pojo.FilePojo;
import com.iscas.dsgroup.securitysearch.pojo.ResponseElement;
import com.iscas.dsgroup.securitysearch.pojo.UserPojo;
import com.sun.appserv.util.cache.Constants;

/**
 * 
 * @author lennyBai
 *
 */

@Component
public class FileServiceImpl implements FileService {

	@Autowired
	private FilePersistence filePersistence;

	@Override
	public ResponseElement fileIndex(String searchIndex, String searchType, CommonParam commonParam) {

		return filePersistence.fileIndex(searchIndex, searchType, commonParam);
	}

	@Override
	public ResponseElement fileUpdate(String searchIndex, String searchType, CommonParam commonParam) {

		return filePersistence.fileUpate(searchIndex, searchType, commonParam);
	}

	@Override
	public ResponseElement fileSearch(String searchIndex, String searchType, CommonParam commonParam) {

		if (commonParam.getConfigParam().getTodoAction() == ConstantsSetting.TODO_LIST_FILE_DEFAULTSEARCH) {
			return this.fileDefaultSearch(searchIndex, searchType, commonParam);
		} else if (commonParam.getConfigParam().getTodoAction() == ConstantsSetting.TODO_LIST_FILE_KEYWORDSEARCH) {
			return this.fileSearchByKeyword(searchIndex, searchType, commonParam);
		}
		// TODO Auto-generated method stub
		else {
			return null;
		}
	}

	private ResponseElement fileDefaultSearch(String searchIndex, String searchType, CommonParam commonParam) {

		if ( StringUtils.isNotBlank( commonParam.getFilePojo().getFileContent() ) )
			return filePersistence.fileSearchByFileContent(searchIndex, searchType, commonParam);
		else if ( ( commonParam.getFilePojo().getFileTag().length != 0 )
				||  StringUtils.isNotBlank( commonParam.getFilePojo().getFileName() ) )
			return filePersistence.fileSearchByFileNameAndTag(searchIndex, searchType, commonParam);
		else
			return filePersistence.fileSearch(searchIndex, searchType, commonParam);
	}

	private ResponseElement fileSearchByKeyword(String searchIndex, String searchType, CommonParam commonParam) {

		return filePersistence.fileSearchByKeyword(searchIndex, searchType, commonParam);
	}

}
