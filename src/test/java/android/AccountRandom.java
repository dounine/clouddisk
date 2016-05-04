package android;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by huanghuanlai on 16/4/12.
 */
public class AccountRandom {

    public static List<String> ACCOUNTS = new ArrayList<>();

    private static Random random = new Random();
    public static String random(){
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<7;i++){
            sb.append((char)(97+random.nextInt(25)));
        }
        while(ACCOUNTS.contains(sb.toString())){
            sb = new StringBuilder();
            for(int i =0;i<7;i++){
                sb.append((char)(97+random.nextInt(25)));
            }
        }
        return sb.toString();
    }

}
