package pattern;

import junit.framework.TestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huanghuanlai on 16/4/18.
 */
public class PatternTest extends TestCase{

    public void testPattern(){

        final Pattern pattern = Pattern.compile("errno=\\d{2,}&errmsg=[\\u0391-\\uFFE5]{2,}");
        final Matcher matcher = pattern.matcher("<script type='text/javascript'>location.href='http://yunpan.360.cn/psp_jump.html?fun=totalLogin.loginCallbackFun&errno=0&errmsg=&s=cq%255D%252B%255E6ny%253AR%253C_%25229G%2524%2528%257B6Sd2%252A%2540Yi%257BY4%2523gO%2560Rj%253E7D%2527%2524Fc%253EO%253EARR%253BO%252A2edlBRAsdbCEgYU%252A%253CFsj9%257C%257EedyA%253Al%252B%255E8YrX0%2525M%252C%252BQOBHxERv%2526DYH0%253Bbh%2540nxi.eVl%253EG%252A%255B03%255D%255CD%255B%2522E%2521%25222FRmITr%252Fs%2529%2521twh%2560zX891V%2526%253EurGL%257E%2521%2528W%252FCe%2540B%253C3b%255Dc2%253ED_QF%25231WdZZps%257E%2521dP%253CF%253A63W%2525RL8-%255EG%2526J%255Dl%257CY%253Co4%252Fr%2525U%253CnCK%252BJH%2560u%2520u%253B08%2523Y398%255D%253E&userinfo=%7B%22qid%22%3A%221256978945%22%2C%22userName%22%3A%22txxhrnw%22%2C%22nickName%22%3A%22dounine%22%2C%22loginEmail%22%3A%22403833139%40qq.com%22%2C%22mobile%22%3A%22%22%2C%22imageId%22%3A%221_t0162ccbf3cc4d9947d%22%2C%22type%22%3A%22formal%22%2C%22src%22%3A%22pcw_cloud%22%2C%22loginTime%22%3A%221460968594%22%2C%22modifyTime%22%3A%221460968594%22%2C%22refreshCount%22%3A%22%22%2C%22lm%22%3A%22%22%2C%22unSafeLogin%22%3A%220%22%2C%22loginField%22%3A%221%22%2C%22keepAlive%22%3A%221%22%2C%22ver%22%3A%222.0%22%2C%22crumb%22%3A%224272e0%22%2C%22imageUrl%22%3A%22http%3A%5C%2F%5C%2Fquc.qhimg.com%5C%2Fdm%5C%2F48_48_100%5C%2Ft0162ccbf3cc4d9947d.jpg%22%7D&';</script>");
        if(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
