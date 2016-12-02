package com.iscas.dsgroup.securitysearch.util;

import com.sun.jersey.core.util.ReaderWriter;
import com.sun.jersey.spi.container.ContainerRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author lennyBai
 *
 */

public class FilterHelper {
	public static <T> T getEntity(Class<T> type, ContainerRequest request) throws IOException {
		byte[] requestEntity = getEntityBytes(request);
		T entity = request.getEntity(type);
		request.setEntityInputStream(new ByteArrayInputStream(requestEntity));

		return entity;
	}

	public static byte[] getEntityBytes(ContainerRequest request) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = request.getEntityInputStream();
		ReaderWriter.writeTo(in, out);

		byte[] requestEntity = out.toByteArray();
		request.setEntityInputStream(new ByteArrayInputStream((byte[]) requestEntity.clone()));

		return requestEntity;
	}
}
