package com.dounine.clouddisk360.parser.deserializer.differpre;

import com.dounine.clouddisk360.parser.DifferPressParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public class DifferPressResponseHandle extends BaseResponseHandle<DifferPress, DifferPressParser>
		implements ResponseHandler<DifferPress> {

	public DifferPressResponseHandle(DifferPressParser parse) {
		super(parse);
	}

	@Override
	public DifferPress handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		executeBefore(response);
		Header[] headers = parse.getHttpClientContext().getResponse().getAllHeaders();
		String localtion = null;
		for (Header header : headers) {
			if (header.getName().equals("Location")) {
				localtion = header.getValue();
				break;
			}
		}
		DifferPress differPress = null;
		try {
			if(StringUtils.isNotBlank(localtion)){
				differPress = new DifferPress();
				differPress.setRedirectUrl(new URIBuilder(localtion));
				parse.getDependencys().put(DifferPress.class,differPress);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		executeAfter(response);
		return differPress;
	}

}
