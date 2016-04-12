package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanghuanlai on 16/4/3.
 */
public class ClassTest extends TestCase{
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassTest.class);

    public void testMap(){
        Map<String,LoginUserToken> loginUserTokenMap = new HashMap<>();
        loginUserTokenMap.put("lake",new LoginUserToken("lake","passport"));
        loginUserTokenMap.put("lake1",new LoginUserToken("lake1","passport"));
        loginUserTokenMap.put("lake2",new LoginUserToken("lake2","passport"));
        loginUserTokenMap.put("lake3",new LoginUserToken("lake3","passport"));
        LOGGER.info(JSON.toJSONString(loginUserTokenMap));

        String mapString = "{\"lake3\":{\"account\":\"lake3\",\"md5\":true,\"password\":\"passport\"},\"lake2\":{\"account\":\"lake2\",\"md5\":true,\"password\":\"passport\"},\"lake\":{\"account\":\"lake\",\"md5\":true,\"password\":\"passport\"},\"lake1\":{\"account\":\"lake1\",\"md5\":true,\"password\":\"passport\"}}";

        Map<String,LoginUserToken> uus = JSON.parseObject(JSON.toJSONString(loginUserTokenMap),new TypeReference<Map<String,LoginUserToken>>(){});

    }
}
