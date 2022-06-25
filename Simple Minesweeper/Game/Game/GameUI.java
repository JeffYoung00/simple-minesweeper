package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
//new GameUI().init();就能调用扫雷的界面
public class GameUI {
    static final int sizeOfSingle =30;
    int gameOver=0;
    Bomb bomb=new Bomb();
    JFrame f=new JFrame();
    JPanel top=new JPanel(new FlowLayout());
    CardLayout emo=new CardLayout();
    JPanel emoji=new JPanel(emo);
    JLabel countFlag=new JLabel("剩余旗子:"+String.valueOf(bomb.flags)+"    ");
    JLabel countTime=new JLabel("    剩余时间:"+String.valueOf(bomb.totalTime));
    JButton smile=new JButton(new ImageIcon("pic\\smile.jpg"));
    JButton cry=new JButton(new ImageIcon("pic\\cry.jpg"));
    JButton happy=new JButton(new ImageIcon("pic\\happy.jpg"));

    Image flag= ImageIO.read(new File("pic","flag.jpg"));
    Image blank= ImageIO.read(new File("pic","blank.jpg"));
    Image aBomb= ImageIO.read(new File("pic","aBomb.jpg"));
    Image bombing= ImageIO.read(new File("pic","bombing.jpg"));
    Image[] pics= {
            ImageIO.read(new File("pic","0.jpg")),
            ImageIO.read(new File("pic","1.jpg")),
            ImageIO.read(new File("pic","2.jpg")),
            ImageIO.read(new File("pic","3.jpg")),
            ImageIO.read(new File("pic","4.jpg")),
            ImageIO.read(new File("pic","5.jpg")),
            ImageIO.read(new File("pic","6.jpg")),
            ImageIO.read(new File("pic","7.jpg")),
            ImageIO.read(new File("pic","8.jpg"))
    };
    Canvas map=new Canvas(){
        @Override
        public void paint(Graphics g) {
            for(int i=0;i<Bomb.rows;i++){
                for(int j=0;j<Bomb.lines;j++){
                    if(bomb.map[i][j]==-1&&gameOver==1){
                        g.drawImage(aBomb,j*sizeOfSingle,i*sizeOfSingle,sizeOfSingle,sizeOfSingle,null);
                    }else if(bomb.map[i][j]==-2&&gameOver==1){
                        g.drawImage(bombing,j*sizeOfSingle,i*sizeOfSingle,sizeOfSingle,sizeOfSingle,null);
                    }else if(bomb.mapState[i][j]==1) {
                        g.drawImage(flag, j * sizeOfSingle, i * sizeOfSingle, sizeOfSingle, sizeOfSingle, null);
                    }else if (bomb.mapState[i][j] == 0) {
                        g.drawImage(blank, j * sizeOfSingle, i * sizeOfSingle, sizeOfSingle, sizeOfSingle, null);
                    } else {
                        g.drawImage(pics[bomb.map[i][j]], j * sizeOfSingle, i * sizeOfSingle, sizeOfSingle, sizeOfSingle, null);
                    }
                }
            }
        }
    };
    MouseListener ml= new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(bomb.startTime==0){
                bomb.startTime=1;
                clock.start();
            }
            if(e.getButton()==MouseEvent.BUTTON1){
                if(!bomb.clickLeft(e.getY()/sizeOfSingle,e.getX()/sizeOfSingle)){
                    gameOver=1;
                    emo.next(emoji);
                    map.removeMouseListener(ml);
                    bomb.startTime=0;
                }

                ////扫雷成功, 这里可以添加返回用时的函数!!!

                //victory return 剩余时间,行数，列数，密度////////////////////////////


                if(bomb.toVictory==0){
                    emo.last(emoji);
                    map.removeMouseListener(ml);
                    bomb.startTime=0;
                }
            } else if(e.getButton()==MouseEvent.BUTTON3){
                bomb.clickRight(e.getY()/sizeOfSingle,e.getX()/sizeOfSingle);
            }
            map.repaint();
            countFlag.setText("剩余棋子:"+String.valueOf(bomb.flags)+"    ");
        }
    };
    MouseListener ma=new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton()==MouseEvent.BUTTON1){
                f.dispose();
                try {
                    new GameUI().init();
                } catch (IOException ex) {
                }
            }
        }
    };
    Thread clock= new Thread(){
        public void run() {
            while(bomb.totalTime>0&&bomb.startTime==1) {
                countTime.setText("    剩余时间:"+String.valueOf(--bomb.totalTime));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    };

    public void init(){
        map.addMouseListener(ml);
        smile.addMouseListener(ma);
        happy.addMouseListener(ma);
        cry.addMouseListener(ma);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("haha");/////////////////////////////
            }
        });

        emoji.add(smile,"smile");
        emoji.add(cry,"cry");
        emoji.add(happy,"happy");

        top.add(countFlag,BorderLayout.EAST);
        top.add(emoji,BorderLayout.CENTER);
        top.add(countTime,BorderLayout.WEST);

        f.add(top,BorderLayout.NORTH);
        f.add(map);
        ///设置文字和字体
        countFlag.setFont(new Font("微软雅黑", Font.BOLD, 15));
        countFlag.setForeground(Color.BLACK);
        countTime.setFont(new Font("微软雅黑", Font.BOLD, 15));
        countTime.setForeground(Color.BLUE);
        ///调整了一些间距啥的
        f.setSize(Bomb.lines*sizeOfSingle+20,Bomb.rows*sizeOfSingle+100);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public GameUI() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        new GameUI().init();
    }
}
