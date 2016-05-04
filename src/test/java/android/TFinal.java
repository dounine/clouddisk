package android;

import com.dounine.clouddisk360.parser.deserializer.user.token.UserToken;

/**
 * Created by huanghuanlai on 16/5/4.
 */
public class TFinal {
    public static void main(String args[]){
        final UserToken userToken = new UserToken();
        userToken.setErrmsg("asdf");

        System.out.println(userToken.getErrmsg());
    }
}
