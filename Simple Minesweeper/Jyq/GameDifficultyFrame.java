import Game.Bomb;
import Game.GameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GameDifficultyFrame extends JFrame {
    private JButton easy;
    private JButton mid;
    private JButton hard;
    private JButton customized;
    private static class InPutFrame extends JFrame {
        private JTextField row;
        private JTextField line;
        private JTextField density;
        private JButton SubmitRow;
        private JButton SubmitLine;
        private JButton SubmitDensity;
        private JLabel RowL;
        private JLabel LineL;
        private JLabel DensityL;
        public InPutFrame() {
            this.setLayout(new GridLayout(3,3,10,10));
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setTitle("自定义难度");
            RowL = new JLabel("1.输入行数:");
            LineL = new JLabel("2.输入列数:");
            DensityL = new JLabel("3.输入密度并开始游戏:");
            row = new JTextField();
            line = new JTextField();
            density = new JTextField();
            SubmitRow = new JButton("1.提交行数");
            SubmitLine = new JButton("2.提交列数");
            SubmitDensity = new JButton("3.提交密度并开始游戏");
            SubmitRow.setFocusable(false);
            SubmitDensity.setFocusable(false);
            SubmitLine.setFocusable(false);
            SubmitRow.addActionListener(e -> {
                if (e.getSource() == SubmitRow) {
                    Bomb.setRows(Integer.parseInt(row.getText()));
                    SubmitRow.setEnabled(false);
                }
            });
            SubmitLine.addActionListener(e -> {
                if (e.getSource() == SubmitLine) {
                    Bomb.setLines(Integer.parseInt(line.getText()));
                    SubmitLine.setEnabled(false);
                }
            });
            SubmitDensity.addActionListener(e -> {
                if (e.getSource() == SubmitDensity) {
                    Bomb.setDensity(Integer.parseInt(density.getText()));
                    SubmitDensity.setEnabled(false);
                    try {
                        new GameUI().init();
                    } catch (IOException E) {
                        E.printStackTrace();
                    }
                    this.setVisible(false);
                }
            });
            this.setBounds(600,300,100,100);
            this.add(RowL);
            this.add(row);
            this.add(SubmitRow);
            this.add(LineL);
            this.add(line);
            this.add(SubmitLine);
            this.add(DensityL);
            this.add(density);
            this.add(SubmitDensity);
            this.pack();
            this.setVisible(true);
        }

        public static void main(String[] args) {
            new InPutFrame();
        }
    }
    public GameDifficultyFrame() {
        this.setLayout(new GridLayout(4,1,10,10));
        this.setBounds(700,250,500,500);
        this.setPreferredSize(new Dimension(320,600));
        this.setTitle("扫雷难度选择");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Client();
            }
        });
        easy = new JButton("初级难度",new ImageIcon("pic\\Easy.jpg"));
        easy.addActionListener(e -> {
            if (e.getSource() == easy) {
                Bomb.SetLevel(9,9,10);
                this.dispose();
                try {
                    new GameUI().init();
                } catch (IOException E) {
                    E.printStackTrace();
                }
            }
        });
        easy.setFocusable(false);
        mid = new JButton("中级难度", new ImageIcon("pic\\Register.jpg"));
        mid.addActionListener(e -> {
            if (e.getSource() == mid) {
                Bomb.SetLevel(16,16,40);
                this.dispose();
                try {
                    new GameUI().init();
                } catch (IOException E) {
                    E.printStackTrace();
                }
            }
        });
        mid.setFocusable(false);
        hard = new JButton("高级难度", new ImageIcon("pic\\Hard.jpeg"));
        hard.addActionListener(e -> {
            if (e.getSource() == hard) {
                Bomb.SetLevel(16,30,40);
                this.dispose();
                try {
                    new GameUI().init();
                } catch (IOException E) {
                    E.printStackTrace();
                }
            }
        });
        hard.setFocusable(false);
        customized = new JButton("自定义难度",new ImageIcon("pic\\Customize.jpg"));
        customized.setFocusable(false);
        customized.addActionListener(e -> {
            if (e.getSource() == customized) {
                //Bomb.SetLevel();
                this.dispose();
                JOptionPane.showMessageDialog(this,"请注意：行数不能大于50，" +
                        "列数不能大于25，密度不能大于40，否则地图过大，屏幕中无法完全展现","自定义须知", JOptionPane.WARNING_MESSAGE);
                new InPutFrame();
            }
        });
        this.add(easy);
        this.add(mid);
        this.add(hard);
        this.add(customized);
        this.setVisible(true);
        this.pack();
        JOptionPane.showMessageDialog(this, "请选择你的难度,可以多次选择不同难度","通知", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new GameDifficultyFrame();
    }
}
