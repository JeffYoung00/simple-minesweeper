package Jyq;

import java.io.Serializable;

public class Rank implements Serializable,Comparable<Rank> {

    private static final long serialVersionUID = 7386192814415749202L;
    private String IP;
    private int Time;
    private String name;
    private String LoginTime;

    public Rank(String IP, int time, String name, String loginTime) {
        this.IP = IP;
        Time = time;
        this.name = name;
        LoginTime = loginTime;
    }

    public String getIP() {
        return IP;
    }

    public int getTime() {
        return Time;
    }

    public String getName() {
        return name;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setTime(int time) {
        Time = time;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Rank o) {
        return  o.Time - this.Time;
    }

    public String getLoginTime() {
        return LoginTime;
    }

    public void setLoginTime(String loginTime) {
        LoginTime = loginTime;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("用户:" + name);
        stringBuilder.append("\nIP:" + IP);
        stringBuilder.append("\n登录时间:" + LoginTime);
        stringBuilder.append("\n游戏时间:" + Time);
        return stringBuilder.toString();
    }
}
