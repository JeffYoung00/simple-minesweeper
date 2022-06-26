package Jyq;

import java.io.File;

public class Directories {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File UserRepo = new File(CWD , "Users");
    public static final File RankingList = new File(CWD, "RankingList");
    public static void main(String[] args) {
        System.out.println(new File(UserRepo ,"name"));
    }
}
