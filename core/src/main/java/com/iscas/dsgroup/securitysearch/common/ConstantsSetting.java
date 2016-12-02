package com.iscas.dsgroup.securitysearch.common;

/**
 * 
 * @author lennyBai
 *
 */

public class ConstantsSetting {

	/***********************************************************************************************************************************************/
	/******************************ESCLIENT SETTING UP********************************************************************************************/
	/***********************************************************************************************************************************************/
	
	public final static String ES_CLUSTERNAME = "segroup";
	public final static String ES_SERVERIP = "127.0.0.1";
	public final static int ES_SERVERPORT = 9300;

	/******************************RESTFUL PARAM************************************************************************************************/
	
	public final static String FILE_CONTROL_INDEX = "file";
	public final static String FILE_CONTROL_TYPE = "lib";
	public final static String FILE_CONTROL_DEMO = "demo";

	public static final String FILE_CONTENT = "FILE";

	
	/******************************ERROR MESSAGE****************************************************************************************************/
	
	public static final String FILEPATH_CONTENT = "filepath";
	public static final String ERR_FILEPATH_MISSING = "err.filepath.missing";

	/*****************************USER CONTROL ACTIONS**********************************************************************************************/
	// 检索，新建，插入，更新 操作类型定义
	public static final int TODO_LIST_GET = 0x1000; // 查看
	public static final int TODO_LIST_POST = 0x2000; // 创建
	public static final int TODO_LIST_PUT = 0x3000; // 更新
	public static final int TODO_LIST_DELETE = 0x4000; // 删除

	// 资源类型
	public static final int TODO_LIST_FILE = 0x100;
	public static final int TODO_LIST_MSG = 0x100;
	public static final int TODO_LIST_CODE = 0x100;

	// 具体操作
	// get 操作分类
	public static final int TODO_LIST_FILE_DEFAULTSEARCH = (TODO_LIST_GET | TODO_LIST_FILE | 0x01);
	public static final int TODO_LIST_FILE_KEYWORDSEARCH = (TODO_LIST_GET | TODO_LIST_FILE | 0x02);

	// put 操作分类
	public static final int TODO_LIST_FILE_INDEX = (TODO_LIST_PUT | TODO_LIST_FILE | 0x01);

	
	/****************************ERROR CODE*********************************************************************************************************/
	public static final int ERROR_CODE_SUCCESS = 0x4000;
	
}