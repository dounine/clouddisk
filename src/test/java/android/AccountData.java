package android;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanghuanlai on 16/4/12.
 */
public class AccountData {
    private String update_key;
    private List<AccountList> list = new ArrayList<>();

    public String getUpdate_key() {
        return update_key;
    }

    public void setUpdate_key(String update_key) {
        this.update_key = update_key;
    }

    public List<AccountList> getList() {
        return list;
    }

    public void setList(List<AccountList> list) {
        this.list = list;
    }
}
