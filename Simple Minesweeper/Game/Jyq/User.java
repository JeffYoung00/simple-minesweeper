package Jyq;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
    private String UserName;
    private String PSW;
    private static final long serialVersionUID = 114514L;
    public User(String userName, String PSW) {
        UserName = userName;
        this.PSW = PSW;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPSW() {
        return PSW;
    }

    public void setPSW(String PSW) {
        this.PSW = PSW;
    }
}
