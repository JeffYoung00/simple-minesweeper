package Jyq;

import javax.swing.*;
import java.awt.*;

public class UserRegisterFrame extends JFrame {
    private JLabel NameLabel;
    private JLabel PSWLabel;
    private JButton SubmitName;
    private JButton SubmitPSW;
    private JTextField Name;
    private JTextField PSW;
    private String UseName;
    private String UserPSW;
    public UserRegisterFrame(){
        Name = new JTextField();
        PSW = new JTextField();
        NameLabel = new JLabel("用户名:");
        PSWLabel = new JLabel("密码:");
        SubmitName = new JButton("提交用户名");
        SubmitName.addActionListener(e -> {
            if (e.getSource() == SubmitName) {
                UseName = Name.getText();
                Name.setEditable(false);
                SubmitName.setEnabled(false);
                //System.out.println(UseName);
            }
        });
        SubmitPSW = new JButton("提交密码并注册");
        SubmitPSW.addActionListener(e -> {
            if (e.getSource() == SubmitPSW) {
                UserPSW = PSW.getText();
                PSW.setEditable(false);
                SubmitPSW.setEnabled(false);
                UserManager.WriteUser(new User(UseName, UserPSW));
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
        new UserRegisterFrame();
    }
}
