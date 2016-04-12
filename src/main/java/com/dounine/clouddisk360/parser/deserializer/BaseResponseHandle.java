package com.dounine.clouddisk360.parser.deserializer;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.util.URLUtil;

public class BaseResponseHandle<M extends BaseDes, P> implements ResponseHandler<M>, IBaseResponseHandle<M> {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseResponseHandle.class);

	public P parse;
	public Class<M> entityClass;

	@SuppressWarnings("unchecked")
	public BaseResponseHandle(P parse) {
		this.parse = parse;
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<M>) params[0];
	}

	public M handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		executeBefore(response);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, Consts.UTF_8);
		M m = desializer(result);
		saveCookie();
		executeAfter(response);
		return m;
	}

	public M desializer(String result) {
		Parse parseAnnotation = parse.getClass().getAnnotation(Parse.class);
		if (StringUtils.isNotBlank(result)) {
			String _result = disassemblyResult(result);
			if(null!=parseAnnotation){
				LOGGER.info(parseAnnotation.value()+"(解析器) -> 响应结果:" + URLUtil.decode(result));
			}
			return JSON.parseObject(_result, entityClass);
		}
		if(null!=parseAnnotation){
			LOGGER.info(parseAnnotation.value()+"(解析器) -> 响应结果为空(NULL)");
		}else{
			LOGGER.info(parse.getClass().getName()+" -> 响应结果为空(NULL)");
		}
		return null;
	}

}
