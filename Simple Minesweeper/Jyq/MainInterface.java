import javax.swing.*;
import java.awt.*;

public class MainInterface {
    private JButton Login;
    private JButton Register;
    private JButton Rank;
    private JFrame MainFrame;
    public MainInterface() {

        Login = new JButton("用户登录", new ImageIcon("pic\\Login.jpg"));
        Register = new JButton("用户注册", new ImageIcon("pic\\Register.jpg"));
        Rank = new JButton("查看排行榜", new ImageIcon("pic\\panic.jpeg"));
        Login.setFocusable(false);
        Register.setFocusable(false);
        Rank.setFocusable(false);
        Login.addActionListener(e -> {
            //System.out.println("触发用户登录");
            new LoginFrame();
        });
        Register.addActionListener(e -> {
            //System.out.println("触发用户注册");
            new UserRegisterFrame();
        });
        Rank.addActionListener(e -> {
            //System.out.println("触发查看排行榜");
            JOptionPane.showMessageDialog(null, "还没做呢哼哼哼啊啊啊啊啊再查看排行榜就要雷普你力！", "排行榜", JOptionPane.ERROR_MESSAGE);
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

    public static void main(String[] args) {
        new MainInterface();
    }
}
