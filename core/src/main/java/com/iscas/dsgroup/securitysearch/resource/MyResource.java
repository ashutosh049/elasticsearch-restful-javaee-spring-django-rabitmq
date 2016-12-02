
package com.iscas.dsgroup.securitysearch.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.iscas.dsgroup.securitysearch.annotation.ValidatedBy;
import com.iscas.dsgroup.securitysearch.deamon.TestData;
import com.iscas.dsgroup.securitysearch.pojo.CommonParam;
import com.iscas.dsgroup.securitysearch.pojo.FilePojo;
import com.iscas.dsgroup.securitysearch.pojo.ResponseElement;

/**
 * Example resource class hosted at the URI path "/myresource"
 * 
 * @author lennyBai
 */
@Component
@Path("/myresource")
public class MyResource {

	private static final Logger logger = Logger.getLogger(MyResource.class);

	/**
	 * Method processing HTTP GET requests, producing "text/plain" MIME media
	 * type.
	 * 
	 * @return String that will be send back as a response of type "text/plain".
	 */
	@GET
	@Produces("text/plain")
	public String getIt() {
		logger.debug("..");
		return "Hi there!";
	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public CommonParam getCommonParam() {
		return new CommonParam();
	}
	
	@PUT
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public CommonParam putCommonParam(CommonParam commonParam)
	{
		return commonParam;
	}
	
	@PUT
	@Path("/getp")
	@Produces(MediaType.APPLICATION_JSON)
	public CommonParam putCommonParamp()
	{
		return new CommonParam();
	}

	@GET
	@Path("/getp")
	@Produces(MediaType.APPLICATION_JSON)
	public CommonParam getCommonPbyP(CommonParam commonParam) {
		return commonParam;
	}

	@GET
	@Path("/getq")
	@Produces(MediaType.APPLICATION_JSON)
	public CommonParam getCommonPbyPa(@QueryParam("commonParam") String string) {
		Gson gons = new Gson();
		CommonParam commonParam = gons.fromJson(string, CommonParam.class);
		return commonParam;
	}

	@GET
	@Path("/geta")
	@Produces(MediaType.APPLICATION_JSON)
	public CommonParam getCommonParama() {
		CommonParam commonParam = new CommonParam();
		FilePojo filePojo = new FilePojo();
		filePojo.setFilePath("testpath");
		commonParam.setFilePojo(filePojo);
		return commonParam;
	}

	@POST
	@Path("/post")
	@Produces(MediaType.APPLICATION_JSON)
	public CommonParam getCommonParam(CommonParam commonParam) {
		return commonParam;
	}

	@POST
	@Path("/posta")
	@Produces(MediaType.APPLICATION_JSON)
	@ValidatedBy({ "missingFilePath" })
	public CommonParam getCommonParama(CommonParam commonParam) {
		return commonParam;
	}

	@GET
	@Path("/getdeamon")
	@Produces(MediaType.APPLICATION_JSON)
	public TestData getTestData() {
		return new TestData();
	}

	@GET
	@Path("/getdeamons")
	@Produces(MediaType.APPLICATION_JSON)
	public TestData getTestDatas(TestData testdata) {
		return testdata;
	}

	@GET
	@Path("/getdeamond")
	@Produces(MediaType.APPLICATION_JSON)
	public TestData getTestDatad(@QueryParam("commonParam") TestData testdata) {
		return testdata;
	}

	@GET
	@Path("/getdeamonq")
	@Produces(MediaType.APPLICATION_JSON)
	public TestData getTestDatad(@QueryParam("commonParam") String string) {
		Gson gons = new Gson();
		TestData testdata = gons.fromJson(string, TestData.class);
		return testdata;
	}

	@PUT
	@Path("/putdemo")
	@Produces(MediaType.APPLICATION_JSON)
	public TestData putDemo(TestData testdata)
	{
		return testdata;
	}
	
	@GET
	@Path("/getr")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseElement getResponse(TestData testdata)
	{
		Object o = new FilePojo();
		return ResponseElement.newSuccessInstance(o);
	}
	
}
