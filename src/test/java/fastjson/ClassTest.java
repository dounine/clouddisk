package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huanghuanlai on 16/4/3.
 */
public class ClassTest extends TestCase{
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassTest.class);

    public void testMap(){
        final Map<String,LoginUserToken> loginUserTokenMap = new ConcurrentHashMap();
        loginUserTokenMap.put("lake",new LoginUserToken("lake","passport"));
        loginUserTokenMap.put("lake1",new LoginUserToken("lake1","passport"));
        loginUserTokenMap.put("lake2",new LoginUserToken("lake2","passport"));
        loginUserTokenMap.put("lake3",new LoginUserToken("lake3","passport"));
        LOGGER.info(JSON.toJSONString(loginUserTokenMap));

        final String mapString = "{\"lake3\":{\"account\":\"lake3\",\"md5\":true,\"password\":\"passport\"},\"lake2\":{\"account\":\"lake2\",\"md5\":true,\"password\":\"passport\"},\"lake\":{\"account\":\"lake\",\"md5\":true,\"password\":\"passport\"},\"lake1\":{\"account\":\"lake1\",\"md5\":true,\"password\":\"passport\"}}";

        final Map<String,LoginUserToken> uus = JSON.parseObject(JSON.toJSONString(loginUserTokenMap),new TypeReference<Map<String,LoginUserToken>>(){});

    }

    public static void main(String args[]){
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/sso-web/upload");
        httpPost.addHeader("Range","bytes=10000-");
        final MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
        multipartEntity.setCharset(Consts.UTF_8);
        multipartEntity.setMode(HttpMultipartMode.STRICT);
        multipartEntity.addBinaryBody("file", new File("/Users/huanghuanlai/Desktop/test.java"));
        httpPost.setEntity(multipartEntity.build());
        try {
            HttpResponse response = httpClient.execute(httpPost);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
