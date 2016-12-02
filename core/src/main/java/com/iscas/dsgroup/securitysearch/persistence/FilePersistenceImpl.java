package com.iscas.dsgroup.securitysearch.persistence;

import static org.elasticsearch.index.query.FilterBuilders.andFilter;
import static org.elasticsearch.index.query.FilterBuilders.boolFilter;
import static org.elasticsearch.index.query.FilterBuilders.rangeFilter;
import static org.elasticsearch.index.query.FilterBuilders.termFilter;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.iscas.dsgroup.securitysearch.common.ConstantsSetting;
import com.iscas.dsgroup.securitysearch.pojo.CommonParam;
import com.iscas.dsgroup.securitysearch.pojo.FileControlResponse;
import com.iscas.dsgroup.securitysearch.pojo.FilePojo;
import com.iscas.dsgroup.securitysearch.pojo.ResponseElement;
import com.iscas.dsgroup.securitysearch.pojo.UserPojo;
import com.iscas.dsgroup.securitysearch.support.EsDaoSupport;
import com.iscas.dsgroup.securitysearch.util.CommonUtil;
import com.iscas.dsgroup.securitysearch.util.HttpCommonHelper;

/**
 * 
 * @author lennyBai
 *
 */

@Component
public class FilePersistenceImpl extends EsDaoSupport implements FilePersistence {

	private FileControlResponse fileControlResponse;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iscas.dsgroup.securitysearch.persistence.FilePersistence#indexFile(
	 * java.lang.String, java.lang.String,
	 * com.iscas.dsgroup.securitysearch.pojo.FilePojo)
	 */
	@Override
	public ResponseElement fileIndex(String searchIndex, String searchType, CommonParam commonParam) {

		IndexRequestBuilder indexRequest = this.getEsDao().getEsClient().prepareIndex(searchIndex, searchType,
				commonParam.getFilePojo().getFileID());
		indexRequest.setSource(commonParam.getFilePojo().toJsonString());
		IndexResponse indexResponse = indexRequest.execute().actionGet();
		
		Boolean result = HttpCommonHelper.sendNewFileTasks(searchIndex, searchType,commonParam);

		fileControlResponse = new FileControlResponse(ConstantsSetting.ERROR_CODE_SUCCESS, indexResponse.toString(),
				"");
		return ResponseElement.newSuccessInstance(fileControlResponse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iscas.dsgroup.securitysearch.persistence.FilePersistence#updateFile(
	 * java.lang.String, java.lang.String,
	 * com.iscas.dsgroup.securitysearch.pojo.FilePojo)
	 */
	@Override
	public ResponseElement fileUpate(String searchIndex, String searchType, CommonParam commonParam) {

		// 调用子方法，提取文本内容并分词
		String fileContent = "";

		try {
			fileContent = CommonUtil.fileExtraction(commonParam.getFilePojo().getFilePath());

			UpdateRequestBuilder updateRequest = this.getEsDao().getEsClient().prepareUpdate(searchIndex, searchType,
					commonParam.getFilePojo().getFileID());
			updateRequest.setDoc(fileContent);
			UpdateResponse updateResponse = updateRequest.execute().actionGet();
			fileControlResponse = new FileControlResponse(ConstantsSetting.ERROR_CODE_SUCCESS,
					updateResponse.toString(), "");
		} catch (Exception e) {

		}

		return ResponseElement.newSuccessInstance(fileControlResponse);
	}

	private FilterBuilder commonFilterBuilder(UserPojo userPojo) {

		FilterBuilder userOwnerFilter = boolFilter().should(termFilter("fileUser", userPojo.getUserId()))
				.should(termFilter("fileOwner", userPojo.getUserId()));
		// range filter fileLevel

		// 过滤器，过滤文件级别
		FilterBuilder levelFilter = rangeFilter("fileLevel").from(0).to(userPojo.getUserLevel()).includeLower(true)
				.includeUpper(true);
		// and filter 和过滤
		return andFilter(userOwnerFilter, levelFilter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iscas.dsgroup.securitysearch.persistence.FilePersistence#fileSearch(
	 * java.lang.String, java.lang.String, int,
	 * com.iscas.dsgroup.securitysearch.pojo.UserPojo)
	 */
	@Override
	public ResponseElement fileSearch(String searchIndex, String searchType, CommonParam commonParam) {

		return commonSearchMethod(searchIndex, searchType, commonParam, QueryBuilders.matchAllQuery());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iscas.dsgroup.securitysearch.persistence.FilePersistence#
	 * fileSearchByFileContent(java.lang.String, java.lang.String, int,
	 * com.iscas.dsgroup.securitysearch.pojo.FilePojo,
	 * com.iscas.dsgroup.securitysearch.pojo.UserPojo)
	 */
	@Override
	public ResponseElement fileSearchByFileContent(String searchIndex, String searchType, CommonParam commonParam) {

		QueryBuilder contentQuery = termQuery("fileContent", commonParam.getFilePojo().getFileContent());

		return commonSearchMethod(searchIndex, searchType, commonParam, contentQuery);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iscas.dsgroup.securitysearch.persistence.FilePersistence#
	 * fileSearchByFileNameAndTag(java.lang.String, java.lang.String, int,
	 * com.iscas.dsgroup.securitysearch.pojo.FilePojo,
	 * com.iscas.dsgroup.securitysearch.pojo.UserPojo)
	 */
	@Override
	public ResponseElement fileSearchByFileNameAndTag(String searchIndex, String searchType, CommonParam commonParam) {

		// 构建查询语句，用名字和标签查询
		QueryBuilder nameAndTagQuery = boolQuery()
				.should(matchQuery("fileName", commonParam.getFilePojo().getFileName()))
				.should(matchQuery("fileTag", commonParam.getFilePojo().getFileTag()));

		return commonSearchMethod(searchIndex, searchType, commonParam, nameAndTagQuery);
	}

	/*
	 * match from filecontent,fileName and File Tag
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iscas.dsgroup.securitysearch.persistence.FilePersistence#
	 * fileSearchByKeyword(java.lang.String, java.lang.String, int,
	 * com.iscas.dsgroup.securitysearch.pojo.FilePojo,
	 * com.iscas.dsgroup.securitysearch.pojo.UserPojo)
	 */
	@Override
	public ResponseElement fileSearchByKeyword(String searchIndex, String searchType, CommonParam commonParam) {

		String word = commonParam.getFilePojo().getFileKeyword();
		word = CommonUtil.ngram(word, word.length());

		QueryBuilder keywordQueryTest=QueryBuilders.multiMatchQuery(word, "fileTag","fileName","fileContent");
		
		return commonSearchMethod(searchIndex, searchType, commonParam, keywordQueryTest);

	}

	/**
	 * @param searchIndex
	 * @param searchType
	 * @param startPosition
	 * @param userPojo
	 * @param searchQuery
	 */
	private ResponseElement commonSearchMethod(String searchIndex, String searchType, CommonParam commonParam,
			QueryBuilder searchQuery) {

		FilterBuilder filter = commonFilterBuilder(commonParam.getUserPojo());

		// 创建查询
		SearchRequestBuilder request = this.getEsDao().getEsClient().prepareSearch(searchIndex);
		request.setTypes(searchType);
		request.setQuery(searchQuery);
		request.setPostFilter(filter);
		request.setFrom(commonParam.getConfigParam().getIndexFrom());
		request.setSize(commonParam.getConfigParam().getHitsNum());

		// 执行搜索
		SearchResponse searchResponse = request.execute().actionGet();
		fileControlResponse = new FileControlResponse(ConstantsSetting.ERROR_CODE_SUCCESS, searchResponse.toString(),
				getSearchResponseHits(searchResponse));
		return ResponseElement.newSuccessInstance(fileControlResponse);
	}

	private String getSearchResponseHits(SearchResponse searchResponse) {
		String temp = "";
		for(SearchHit searchHit: searchResponse.getHits())
			temp+= searchHit.getSourceAsString();
		return temp;
	}

}
