package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class UI {
    public static void main(String[] args) throws IOException {
        JFrame f=new JFrame();
        f.add(new Button("hah"));
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("haha");
            }
        });
        f.setVisible(true);
    }
}
