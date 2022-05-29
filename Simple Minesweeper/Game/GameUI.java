package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

//这个类有main(),能直接运行
public class GameUI {
    //设置格子大小,但是用的图片都是30*30的, 表情是40*40的
    //图片放在pic路径下, 后面再换好看点的...
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
                        g.drawImage(aBomb,i*sizeOfSingle,j*sizeOfSingle,sizeOfSingle,sizeOfSingle,null);
                    }else if(bomb.map[i][j]==-2&&gameOver==1){
                        g.drawImage(bombing,i*sizeOfSingle,j*sizeOfSingle,sizeOfSingle,sizeOfSingle,null);
                    }else if(bomb.mapState[i][j]==1) {
                        g.drawImage(flag, i * sizeOfSingle, j * sizeOfSingle, sizeOfSingle, sizeOfSingle, null);
                    }else if (bomb.mapState[i][j] == 0) {
                        g.drawImage(blank, i * sizeOfSingle, j * sizeOfSingle, sizeOfSingle, sizeOfSingle, null);
                    } else {
                        g.drawImage(pics[bomb.map[i][j]], i * sizeOfSingle, j * sizeOfSingle, sizeOfSingle, sizeOfSingle, null);
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
                if(!bomb.clickLeft(e.getX()/sizeOfSingle,e.getY()/sizeOfSingle)){
                    gameOver=1;
                    emo.next(emoji);
                    map.removeMouseListener(ml);
                    bomb.startTime=0;
                }

                ////扫雷成功, 这里可以添加返回用时的函数!!!
                if(bomb.toVictory==0){
                    emo.last(emoji);
                    map.removeMouseListener(ml);
                    bomb.startTime=0;
                }
            } else if(e.getButton()==MouseEvent.BUTTON3){
                bomb.clickRight(e.getX()/sizeOfSingle,e.getY()/sizeOfSingle);
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
        f.setSize(Bomb.rows*sizeOfSingle+20,Bomb.lines*sizeOfSingle+90);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public GameUI() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        new GameUI().init();
    }
}
