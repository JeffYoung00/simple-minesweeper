package Jyq;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class UserManager {
    public static void WriteUser(User user) {
        if (!Directories.UserRepo.exists()) {
            Directories.UserRepo.mkdir();
        }
        if (Directories.UserRepo.list().length == 0) {
            File UserRepo = Directories.UserRepo;
            File Filename = new File(UserRepo,user.getUserName());
            LazyUtils.WriteObject(Filename, user);
            JOptionPane.showMessageDialog(null,"注册成功","用户注册",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (LazyUtils.FilesIn(Directories.UserRepo).contains(user.getUserName())) {
            JOptionPane.showMessageDialog(null,"已存在同名用户，系统已退出","用户注册",JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        else {
            File UserRepo = Directories.UserRepo;
            File Filename = new File(UserRepo, user.getUserName());
            LazyUtils.WriteObject(Filename, user);
            JOptionPane.showMessageDialog(null, "注册成功", "用户注册", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private static User ReadUser(String userName) {
        if (!LazyUtils.FilesIn(Directories.UserRepo).contains(userName)) {
            JOptionPane.showMessageDialog(null,"用户不存在,系统已退出","用户登录",JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        else {
            File Filename = new File(Directories.UserRepo,userName);
            return LazyUtils.ReadObject(Filename, User.class);
        }
        return null;
    }
    public static void UserLogin(String name,String PSW) {
        User user = UserManager.ReadUser(name);
        if (name.equals(user.getUserName()) && PSW.equals(user.getPSW())) {
            Client.setUsername(name);
            Client.setLoginTime(LocalDateTime.now());
            Client.setIP(Client.ClientSocket.getLocalAddress().getHostAddress());
            try {
                Client.bufferedWriter.write(name);
                Client.bufferedWriter.newLine();
                Client.bufferedWriter.flush();
                Client.bufferedWriter.write(String.valueOf(LocalDateTime.now()));
                Client.bufferedWriter.newLine();
                Client.bufferedWriter.flush();
                Client.bufferedWriter.write(Client.getIP());
                Client.bufferedWriter.newLine();
                Client.bufferedWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(null,"登陆成功","用户登录",JOptionPane.INFORMATION_MESSAGE);
            new GameDifficultyFrame();
        }
        else {
            JOptionPane.showMessageDialog(null,"用户名或密码错误，请关闭当前登录窗口重新登录","用户登录",JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void main(String[] args) {
        UserManager.WriteUser(new User("1145","514"));
        UserManager.ReadUser("11");
    }
}
