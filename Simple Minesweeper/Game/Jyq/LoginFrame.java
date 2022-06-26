package Jyq;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JLabel NameLabel;
    private JLabel PSWLabel;
    private JButton SubmitName;
    private JButton SubmitPSW;
    private JTextField Name;
    private JTextField PSW;
    private String UseName;
    private String UserPSW;
    public LoginFrame(){
        Name = new JTextField();
        PSW = new JTextField();
        NameLabel = new JLabel("1.输入用户名:");
        PSWLabel = new JLabel("2.输入密码:");
        SubmitName = new JButton("1.提交用户名");
        SubmitName.addActionListener(e -> {
            if (e.getSource() == SubmitName) {
                UseName = Name.getText();
                Name.setEditable(false);
                SubmitName.setEnabled(false);
                //System.out.println(UseName);
            }
        });
        SubmitPSW = new JButton("2.提交密码并登录");
        SubmitPSW.addActionListener(e -> {
            if (e.getSource() == SubmitPSW) {
                UserPSW = PSW.getText();
                PSW.setEditable(false);
                SubmitPSW.setEnabled(false);
                UserManager.UserLogin(UseName, UserPSW);
                this.setVisible(false);
                //System.out.println(UserPSW);
            }
        });
        this.setLayout(new GridLayout(2,3));
        this.setBounds(600,300,100,100);
        this.add(NameLabel);
        this.add(Name);
        this.add(SubmitName);
        this.add(PSWLabel);
        this.add(PSW);
        this.add(SubmitPSW);
        this.setVisible(true);
        this.pack();
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
