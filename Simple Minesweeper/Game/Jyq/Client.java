package Jyq;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {
    private static String IP;
    private static int Time;
    private static String Username;
    private static LocalDateTime LoginTime;
    public static Socket ClientSocket;
    public static  BufferedWriter bufferedWriter;
    private JButton Login;

    public static String getIP() {
        return IP;
    }

    public static void setIP(String IP) {
        Client.IP = IP;
    }

    public static int getTime() {
        return Time;
    }

    public static void setTime(int time) {
        Time = time;
    }

    public static String getUsername() {
        return Username;
    }

    public static void setUsername(String username) {
        Client.Username = username;
    }

    public static LocalDateTime getLoginTime() {
        return LoginTime;
    }

    public static void setLoginTime(LocalDateTime loginTime) {
        LoginTime = loginTime;
    }

    private JButton Register;
    private JButton Rank;
    public JFrame MainFrame;
    public Client() {
        try {
            ClientSocket = new Socket("localhost", 10024);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(ClientSocket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Login = new JButton("用户登录", new ImageIcon("pic\\Login.jpg"));
        Register = new JButton("用户注册", new ImageIcon("pic\\Register.jpg"));
        Rank = new JButton("查看排行榜", new ImageIcon("pic\\panic.jpeg"));
        Login.setFocusable(false);
        Register.setFocusable(false);
        Rank.setFocusable(false);
        Login.addActionListener(e -> {
            //System.out.println("触发用户登录");
           // MainFrame.setEnabled(false);
            //MainFrame.setVisible(false);
            new LoginFrame();
            MainFrame.dispose();
        });
        Register.addActionListener(e -> {
            //System.out.println("触发用户注册");
            new UserRegisterFrame();
            MainFrame.dispose();
        });
        Rank.addActionListener(e -> {
            //System.out.println("触发查看排行榜");
            JOptionPane.showMessageDialog(null, "请查看控制台的输出！", "排行榜", JOptionPane.ERROR_MESSAGE);
            (LazyUtils.ReadObject(Directories.RankingList, RankingList.class)).ShowRank();
            MainFrame.dispose();
        });
        MainFrame = new JFrame("下北泽扫雷游戏主界面");
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setBounds(500,200,500,600);
        MainFrame.setLayout(new GridLayout(3,1,10,10));
        MainFrame.add(Login);
        MainFrame.add(Register);
        MainFrame.add(Rank);
        MainFrame.setVisible(true);
        MainFrame.setIconImage(new ImageIcon("pic\\happy.jpg").getImage());
        JOptionPane.showMessageDialog(MainFrame,"欢迎来到下北泽扫雷小游戏","欢迎",JOptionPane.PLAIN_MESSAGE);
    }
    public static void SentGamingTime(int Time) {
        try {
            Client.bufferedWriter.write(Time);
            Client.bufferedWriter.newLine();
            Client.bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        new Client();
    }
}
