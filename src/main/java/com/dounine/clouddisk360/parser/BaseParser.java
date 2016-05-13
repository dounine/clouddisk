package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.DependResult;
import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.exception.CloudDiskException;
import com.dounine.clouddisk360.parser.deserializer.*;
import com.dounine.clouddisk360.parser.deserializer.authtoken.AuthToken;
import com.dounine.clouddisk360.parser.deserializer.differpre.DifferPress;
import com.dounine.clouddisk360.parser.deserializer.login.Login;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.pool.PoolingHttpClientConnection;
import com.dounine.clouddisk360.store.BasePathCommon;
import com.dounine.clouddisk360.store.CookieStoreUT;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseParser<Method extends HttpRequest, M extends BaseDes, C extends BaseConst, Parameter extends BaseParameter, RequestInterceptor extends BaseRequestInterceptor<C,Parser>, ResponseHandle, Parser extends BaseParser>
        extends JSONBinary implements IBaseParser{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseParser.class);

    static {
        if (StringUtils.isBlank(BasePathCommon.BASE_PATH)) {
            BasePathCommon.BASE_PATH = FileUtils.getUserDirectoryPath() + File.separator;
        }
        LOGGER.info("360云盘模块初始化位置:" + BasePathCommon.BASE_PATH);
    }

    public static final RequestConfig COOKIE_REQUEST_CONFIG = RequestConfig.custom()
            .setCookieSpec(CookieSpecs.STANDARD_STRICT)
            .setSocketTimeout(10000)
            .build();

    protected CookieStoreUT cookieStoreUT = new CookieStoreUT();
    protected HttpClientContext httpClientContext = new HttpClientContext();
    protected HttpClient httpClient;

    public RequestInterceptor requestInterceptor;
    public C CONST;
    public HttpRequest requestMethod;
    public ResponseHandler<M> responseHandler;
    public Parameter parameter;
    protected String uriPath;
    protected Class<M> mClazz;
    /**
     * 依赖返回值
     */
    protected Map<Class, M> dependencys = new ConcurrentHashMap(0);
    protected CloudDiskException cloudDiskException;
    protected LocalDateTime createDateTime = LocalDateTime.now();

    public BaseParser dependsCustomInit(final Parser parser, final BaseParser baseParser) {
        return baseParser;
    }

    public <TT> TT getDependResult(final Class<TT> depend) {
        TT tt = null;
        final Object baseDes = this.dependencys.get(depend);
        if (null != baseDes) {
            tt = (TT) baseDes;
        }
        return tt;
    }

    public <TT> TT getDependAccountResult(final Class<TT> depend) {
        final DifferPressParser differPressParser = DifferPressParser.DIFFER_PRESS_PARSERS.get(loginUserToken.getAccount());
        final Object baseDes = differPressParser.dependencys.get(depend);
        if (null != baseDes) {
            return (TT) baseDes;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void inits() {
        final Type genType = getClass().getGenericSuperclass();
        final Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        mClazz = (Class<M>) params[1];//首先初始化M返回结果类,execClouddiskException()方法要用到
        final Dependency dependency = this.getClass().getAnnotation(Dependency.class);
        if (null != dependency) {
            final Class<BaseParser>[] dClass = (Class<BaseParser>[]) dependency.depends();
            for (final Class<BaseParser> cc : dClass) {
                try {
                    final DependResult dependResult = cc.getAnnotation(DependResult.class);
                    BaseParser baseParser = cc.getConstructor(LoginUserToken.class).newInstance(loginUserToken);
                    if (baseParser.hasException()) {
                        this.cloudDiskException = baseParser.getCloudDiskException();
                    } else {
                        if (dependResult.customInit()) {
                            baseParser = baseParser.dependsCustomInit(baseParser, this);
                        } else {// 默认初始化
                            final M baseResult = (M) baseParser.parse();
                            if (baseParser.hasException()) {
                                this.cloudDiskException = baseParser.getCloudDiskException();
                            } else if (null != baseResult) {
                                // this.dependencys.putAll(baseParser.getDependencys());
                                // //此句会把所有父级响应的结果往下传递
                                dependencys.put(baseResult.getClass(), baseResult);
                            }
                        }
                    }
                    this.dataSmooth(baseParser);
                } catch (CloudDiskException e) {
                    this.cloudDiskException = e;
                } catch (InstantiationException | IllegalAccessException e) {
                    LOGGER.error("Error",e);;
                } catch (NoSuchMethodException e) {
                	LOGGER.error("Error",e);;
                } catch (SecurityException e) {
                	LOGGER.error("Error",e);;
                } catch (IllegalArgumentException e) {
                	LOGGER.error("Error",e);;
                } catch (InvocationTargetException e) {
                	LOGGER.error("Error",e);;
                }
                if (this.hasException()) {//有异常程序返回
                    return;
                }
            }
        }

        final Class<RequestInterceptor> requestInterceptorClazz = (Class<RequestInterceptor>) params[4];
        final Class<ResponseHandler<M>> responseHandlerClazz = (Class<ResponseHandler<M>>) params[5];
        final Class<HttpRequest> requestMethodClazz = (Class<HttpRequest>) params[0];
        final Class<BaseConst> baseconstMethodClazz = (Class<BaseConst>) params[2];
        try {
            this.responseHandler = (ResponseHandler<M>) responseHandlerClazz.getConstructors()[0].newInstance(this);
            this.requestInterceptor = (RequestInterceptor) requestInterceptorClazz.getConstructors()[0].newInstance(this);
            this.requestMethod = (HttpRequest) requestMethodClazz.newInstance();
            CONST = (C) baseconstMethodClazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
        	LOGGER.error("Error",e);;
        } catch (IllegalArgumentException e) {
        	LOGGER.error("Error",e);;
        } catch (InvocationTargetException e) {
        	LOGGER.error("Error",e);;
        }
    }

    public BaseParser() {
        inits();
    }

    public BaseParser(final LoginUserToken loginUser) {
        if (null == loginUserToken) {
            if (null == loginUser) {
                throw new CloudDiskException("loginUserToken 不能为空");
            }
            if (StringUtils.isBlank(loginUser.getAccount()) || StringUtils.isBlank(loginUser.getPassword())) {
                throw new CloudDiskException("loginUserToken (用户名/密码)不能为空");
            }
        }
        this.cookieStoreUT.setLoginUserToken(loginUser);
        //httpClientContext.setUserToken(loginUser);
        this.loginUserToken = loginUser;
        inits();
    }

    /**
     * 把parse数据平滑过来
     */
    public <Parser extends BaseParser> void dataSmooth(final Parser parse) {
        if (null != parse) {
            if (null == parse.loginUserToken) {
                throw new CloudDiskException("loginUserToken 不能为空");
            } else {
                if (StringUtils.isBlank(parse.loginUserToken.getAccount())) {
                    throw new CloudDiskException("loginUserToken (account)不能为空");
                }
            }
            this.httpClientContext = parse.httpClientContext;
            this.cookieStoreUT = parse.cookieStoreUT;
            //this.cookieStoreUT.setLoginUserToken(parse.loginUserToken);
            this.loginUserToken = parse.loginUserToken;
        } else {
            throw new CloudDiskException("{parse} 参数不能为空");
        }
    }

    public M parse() {
        if (hasException()) {
            return execClouddiskException();
        }
        if (null == loginUserToken) {
            throw new CloudDiskException("至少给一个解析器初始化用户信息");
        }
        final Method request = initRequest(parameter);
        final M m = execute(request);
        cookieStoreUT.setCookieStore(httpClientContext.getCookieStore());
        return m;
    }

    public M parse(final Parameter parameter) {
        if (hasException()) {
            return execClouddiskException();
        }
        this.parameter = parameter;
        return parse();
    }

    public String getUriPath() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public Method initRequest(final Parameter parameter) {
        Method method = null;
        uriPath = getUriPath();
        if (StringUtils.isNotBlank(uriPath)) {
            final String host = getRedSchmemHost();
            if (requestMethod instanceof HttpGet) {
                method = (Method) new HttpGet(host + uriPath);
            }else{
                method = (Method) new HttpPost(host + uriPath);
            }
            return method;
        }else{
            throw new CloudDiskException("使用默认initRequest,uriPath不能为空");
        }
    }

    public M execClouddiskException() {
        M m = null;
        try {
            m = mClazz.newInstance();
            m.setCddmsg(cloudDiskException.getMessage());
        } catch (InstantiationException e) {
        	LOGGER.error("Error",e);;
        } catch (IllegalAccessException e) {
        	LOGGER.error("Error",e);;
        }
        return m;
    }

    protected RequestConfig cookieRequestConfig(final String cookieSpecs) {
        return RequestConfig.custom().setCookieSpec(cookieSpecs).build();
    }

    public M execute(final Method request) {
        if (hasException()) {
            return execClouddiskException();
        }
        httpClient = HttpClients.custom().setConnectionManager(PoolingHttpClientConnection.getInstalce())
                .addInterceptorLast(requestInterceptor).setDefaultRequestConfig(COOKIE_REQUEST_CONFIG).build();
        try {
            if (request instanceof HttpGet) {
                final HttpGet get = (HttpGet) request;
                return httpClient.execute(get, responseHandler, this.httpClientContext);
            }
            final HttpPost post = (HttpPost) request;
            return httpClient.execute(post, responseHandler, this.httpClientContext);
        } catch (IOException e) {
            executeException(e,request);
        }
        return null;
    }

    public void executeException(final Exception e,final Method request){
        if(e instanceof SocketTimeoutException){
            final String errMsg = request.getRequestLine()+" Socket连接超时";
            this.cloudDiskException = new CloudDiskException(errMsg);
            LOGGER.warn(errMsg);
        }else if(e instanceof CloudDiskException){
            this.cloudDiskException = (CloudDiskException) e;
            LOGGER.warn(cloudDiskException.getMessage());
        }
    }

    public String readCookieStoreValue(final String name) {
        if (StringUtils.isBlank(name)) {
            throw new CloudDiskException("cookie名称不能为空");
        }
        final CookieStore cookieStore = httpClientContext.getCookieStore();
        final List<Cookie> cookies = cookieStore.getCookies();
        Cookie cookie = null;
        for (final Cookie coo : cookies) {
            if (coo.getName().equals(name)) {
                cookie = coo;
                break;
            }
        }
        if (null != cookie) {
            return cookie.getValue();
        }
        return null;
    }

    public String getRedHost() {
        try {
            final URIBuilder uriBuilder =new URIBuilder(getRedSchmemHost());
            return uriBuilder.getHost();
        } catch (URISyntaxException e) {
        	LOGGER.error("Error",e);
        }
        return null;
    }

    public String getRedSchmemHost() {
        final DifferPress differPress = getDependAccountResult(DifferPress.class);
        if (null == differPress) {
            throw new CloudDiskException(MsgConst.HOST_VALUE_NOT_NULL);
        } else {
            final URIBuilder uri = differPress.getRedirectUrl();
            return new StringBuilder(uri.getScheme()).append("://").append(uri.getHost()).toString();
        }
    }

    public String getRequestUri(){
        return getRedSchmemHost()+CONST.getUriPath();
    }

    @SuppressWarnings("unchecked")
    public M getParseResult() {
        return dependencys.get(mClazz);
    }

    public Login getLoginInfo() {
        final Login login = httpClientContext.getUserToken(Login.class);
        if (null == login) {
            return null;
        }
        return login;
    }
    public void setLoginInfo(final Login login){
        if(null!=login){
            httpClientContext.setUserToken(login);
        }
    }

    public String readAuthTokenValue() {
        final AuthToken authToken = (AuthToken) dependencys.get(AuthToken.class);
        if (null == authToken) {
            throw new CloudDiskException("tokenValue授权令牌为空");
        }
        return authToken.getToken();
    }

    public CookieStore readCookieStoreDisk() {
        httpClientContext.setCookieStore(cookieStoreUT.readCookieStoreForDisk());
        return httpClientContext.getCookieStore();
    }

    public CookieStore readCookieStoreDisk(final String[] filterCookie) {
        httpClientContext.setCookieStore(cookieStoreUT.readCookieStoreForDisk(filterCookie));
        return httpClientContext.getCookieStore();
    }

    public HttpClientContext getHttpClientContext() {
        return httpClientContext;
    }

    public void setHttpClientContext(final HttpClientContext httpContext) {
        this.httpClientContext = httpContext;
    }

    public CookieStoreUT getCookieStoreUT() {
        return cookieStoreUT;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Map<Class, M> getDependencys() {
        return dependencys;
    }

    public void setDependencys(Map<Class, M> dependencys) {
        this.dependencys = dependencys;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CloudDiskException getCloudDiskException() {
        return cloudDiskException;
    }

    public void setCloudDiskException(CloudDiskException cloudDiskException) {
        this.cloudDiskException = cloudDiskException;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public boolean hasException() {
        return null != this.cloudDiskException;
    }
}
