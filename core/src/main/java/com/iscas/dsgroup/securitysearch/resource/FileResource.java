package com.iscas.dsgroup.securitysearch.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.persistence.internal.sessions.factories.model.rcm.command.CommandsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.iscas.dsgroup.securitysearch.common.ConstantsSetting;
import com.iscas.dsgroup.securitysearch.pojo.CommonParam;
import com.iscas.dsgroup.securitysearch.pojo.ConfigParam;
import com.iscas.dsgroup.securitysearch.pojo.FilePojo;
import com.iscas.dsgroup.securitysearch.pojo.ResponseElement;
import com.iscas.dsgroup.securitysearch.pojo.UserPojo;
import com.iscas.dsgroup.securitysearch.service.FileService;

/**
 * 
 * @author lennyBai
 *
 */

@Component
@Path(ConstantsSetting.FILE_CONTROL_INDEX)
public class FileResource {

	@Autowired
	private FileService fileService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Here is File Interface!";
	}
	
	@GET
	@Path(ConstantsSetting.FILE_CONTROL_DEMO)
	@Produces(MediaType.APPLICATION_JSON)
	public CommonParam getCommonParam()
	{
		CommonParam commonParam = new CommonParam();
		
		return commonParam.getDemo();
	}

	@PUT
	@Path(ConstantsSetting.FILE_CONTROL_TYPE)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseElement fileInsert(CommonParam commonParam) {

		return fileService.fileIndex(ConstantsSetting.FILE_CONTROL_INDEX, ConstantsSetting.FILE_CONTROL_TYPE,
				commonParam);
	}
	
	@POST
	@Path(ConstantsSetting.FILE_CONTROL_TYPE)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseElement searchFiles(CommonParam commonParam) {
		
		return fileService.fileSearch(ConstantsSetting.FILE_CONTROL_INDEX, ConstantsSetting.FILE_CONTROL_TYPE,
				commonParam);
	}
	
	@GET
	@Path(ConstantsSetting.FILE_CONTROL_TYPE)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseElement getFiles(@QueryParam("CommonParam") String jsonParam ) {

		return null;
	}
	
}
