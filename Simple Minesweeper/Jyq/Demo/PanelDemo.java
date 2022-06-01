package Demo;

import java.awt.*;

public class PanelDemo {
    public static void main(String[] args) {
        Frame frame = new Frame("下北泽庄园");
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setBackground(new Color(11,45,14));
        frame.setLocation(500,500);
        Panel panel = new Panel(null);
        panel.setBackground(new Color(19,19, 180));
        panel.setLocation(500,500);
        panel.setVisible(true);
        panel.setSize(30,30);
        frame.add(panel);
    }

}
