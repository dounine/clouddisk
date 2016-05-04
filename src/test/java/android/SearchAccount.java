package android;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanghuanlai on 16/4/12.
 */
public class SearchAccount implements Runnable{

    public static HttpPost request;
    public static int findAccount;
    public static CloseableHttpClient httpClient = HttpClients.createMinimal();
    public static File file;
    static {
        file = new File(FileUtils.getUserDirectoryPath()+"/search-account/users.txt");
        if(!file.exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        request = new HttpPost("http://api.photo.yunpan.360.cn/intf.php?method=User.searchUserByName&qid=1256961160&devtype=android&v=6.4.6&devid=e10f7443d86547fd1ac8d4998b2eead1&devname=Custom+Phone+-+4.3+-+API+18+-+768x1280&rtick=72320378105532672&sign=1e7e14f3458e10285ee2aee258d73f85&ofmt=json&pid=web&m2=d10e94d79516326e51ca723a54547d85&");
        request.setConfig(RequestConfig.custom().setSocketTimeout(2000).build());
        request.setHeader("Cookie","q=u%3Demeewct%26n%3D%25O7%25S0%25Q6%25S7%25O1%25N3%25Q3%25Q3%25O2%25OO%25O6%25PS%25PQ%25S8%26le%3D%26m%3DZGZ0WGWOWGWOWGWOWGWOWGWOZQDk%26qid%3D1256961160%26im%3D1_t01ee54137f5aa3e259%26src%3Dmpl_cloud_and%26t%3D1;t=s%3Db09a7fc516a1ff211f36061ba382d3a2%26t%3D1460477623%26lm%3D%26lf%3D4%26sk%3D53d4e1dc12844d9bf26f22bb9752f67e%26mt%3D1460477623%26rc%3D%26v%3D2.0%26a%3D1;token=1947658280.38.7a4ab23b.1256961160.1460477623;");
        request.setHeader("X-QIHOO-HOST","api.photo.yunpan.360.cn");
        request.setHeader("Accept-Encoding","zip");
        request.setHeader("Host","api.photo.yunpan.360.cn");
        request.setHeader("Charset","UTF-8");
        request.setHeader("User-Agent","Dalvik/1.6.0 (Linux; U; Android 4.3; Custom Phone - 4.3 - API 18 - 768x1280 Build/JLS36G)");
        request.setHeader("Connection","Keep-Alive");
    }

    public static void main(String args[]){

        SearchAccount searchAccount = new SearchAccount();
        new Thread(searchAccount).start();

    }

    @Override
    public void run() {
        while(true){
            final List<NameValuePair> valuePairs = new ArrayList<>(1);
            String account = AccountRandom.random();
            valuePairs.add(new BasicNameValuePair("username",account));
            request.setEntity(new UrlEncodedFormEntity(valuePairs, Consts.UTF_8));
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(request);
                String result = EntityUtils.toString(response.getEntity(),Consts.UTF_8);
                if(result.contains("\\u627e\\u4e0d\\u5230\\u8be5\\u7528\\u6237")){
                    //System.out.println("找不到访用户("+(++findAccount)+"):"+account);
                }else{
                    if(result.contains("\\u64cd\\u4f5c\\u592a\\u5feb\\u4e86, \\u4f11\\u606f\\u4e00\\u4f1a\\u5427")){
                        //System.out.println("操作太快了, 休息一会吧");
                        Thread.sleep(1000*60);
                    }else if(result.contains("\\u7528\\u6237\\u672a\\u767b\\u5f55\\u6216\\u8ba4\\u8bc1\\u4fe1\\u606f\\u9519\\u8bef")){
                        //System.out.println("用户未登录或认证信息错误");
                        return;
                    } else{
                        System.out.println("找到新用户:"+result);
                        Account accountObj = JSON.parseObject(result,Account.class);
                        if(StringUtils.isBlank(accountObj.getData().getList().get(0).getLoginEmail())){
                            FileUtils.writeStringToFile(file,result,true);
                        }
                    }
                }
            } catch (IOException e) {
                if(e instanceof SocketTimeoutException){
                    //连接超时
                }else if(e instanceof NoHttpResponseException){
                    //请求错误
                }
                else if(e instanceof SocketException){
                    //断网
                }else{
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(null!=response){
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
