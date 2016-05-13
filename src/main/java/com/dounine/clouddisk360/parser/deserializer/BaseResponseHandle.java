package com.dounine.clouddisk360.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.util.URLUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.regex.Pattern;

public class BaseResponseHandle<M extends BaseDes, P> implements ResponseHandler<M>, IBaseResponseHandle<M> {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseResponseHandle.class);
	public static final Pattern VAL_PAT = Pattern.compile("[{].*[}]");
	public static final Pattern ERR_VAL_PAT = Pattern.compile("errno=\\d{2,}&errmsg=.{2,}[&]");//查找错误信息

	public P parse;
	public Class<M> entityClass;

	@SuppressWarnings("unchecked")
	public BaseResponseHandle(final P parse) {
		this.parse = parse;
		final Type genType = getClass().getGenericSuperclass();
		final Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<M>) params[0];
	}

	public M handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
		executeBefore(response);
		final HttpEntity entity = response.getEntity();
		final String result = EntityUtils.toString(entity, Consts.UTF_8);
		final M m = desializer(result);
		saveCookie();
		executeAfter(response);
		return m;
	}

	public M desializer(final String result) {
		final Parse parseAnnotation = parse.getClass().getAnnotation(Parse.class);
		if (StringUtils.isNotBlank(result)) {
			final String resultNew = disassemblyResult(result);
			if(null!=parseAnnotation){
				LOGGER.info(parseAnnotation.value()+"(解析器) -> 响应结果:" + URLUtil.decode(result));
			}
			return JSON.parseObject(resultNew, entityClass);
		}
		if(null!=parseAnnotation){
			LOGGER.info(parseAnnotation.value()+"(解析器) -> 响应结果为空(NULL)");
		}else{
			LOGGER.info(parse.getClass().getName()+" -> 响应结果为空(NULL)");
		}
		return null;
	}

}
