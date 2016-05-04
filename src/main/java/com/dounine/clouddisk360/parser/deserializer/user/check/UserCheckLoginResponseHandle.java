package com.dounine.clouddisk360.parser.deserializer.user.check;

import com.dounine.clouddisk360.parser.DifferPressParser;
import com.dounine.clouddisk360.parser.UserCheckLoginParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;
import com.dounine.clouddisk360.store.BasePathCommon;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class UserCheckLoginResponseHandle extends BaseResponseHandle<UserCheckLogin, UserCheckLoginParser>
        implements ResponseHandler<UserCheckLogin> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCheckLoginResponseHandle.class);

    public UserCheckLoginResponseHandle(final UserCheckLoginParser parse) {
        super(parse);
    }

    @Override
    public UserCheckLogin handleResponse(final HttpResponse response) throws ClientProtocolException, ClientProtocolException {
        final UserCheckLogin userCheckLogin = new UserCheckLogin();
        if (response.getStatusLine().getStatusCode() == 302) {
            final String location = response.getLastHeader("Location").getValue();
            if (StringUtils.isNotBlank(location)&&location.endsWith(".yunpan.360.cn/my/index/")) {
                return userCheckLogin;
            }
        }
        userCheckLogin.setCddmsg("登录失效");
        userCheckLogin.setErrno(401);
        if(null!=parse.getParameter()){
            if (parse.getParameter().isClearCacheStore()) {//如果要清除本地缓存则删掉文件
                final File file = new File(BasePathCommon.BASE_PATH + parse.getLoginUserToken().getAccount() + "/cookieStore/cookies.txt");
                if (file.exists()) {
                    file.delete();
                    LOGGER.info("清除本地[ cookieStore ]缓存文件");
                }
            }
            if (parse.getParameter().isClearCacheObject()) {//如果要清除缓存变量
                DifferPressParser.DIFFER_PRESS_PARSERS.remove(parse.getLoginUserToken().getAccount());//清除
                LOGGER.info("清除全局静态缓存对象");
            }
        }
        return userCheckLogin;
    }
}
