package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FloodFill {
    public static void main(String[] args) throws IOException {
        //алгоритм заливки
        JFrame jFrame = new JFrame("Flood fill");
        jFrame.setSize(500, 300);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setContentPane(new DrawPane());
        jFrame.repaint();
        jFrame.setVisible(true);
    }

    static class DrawPane extends JPanel {
        private static BufferedImage image = null;
        int rgbColor = 0; //Цвет области для заливки
        int x, y; //Координаты начальной точки
        DrawPane() throws IOException {
            File file = new File("src/com/company/data/fish.png");
            image = ImageIO.read(file);
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
        //System.out.println(x+" "+y);
                    rgbColor = image.getRGB(x,y);
                    System.out.println(rgbColor);
        //image.setRGB(x,y,new Color(255,0,0).getRGB());
                    //rgbColor = new Color(255,0,0).getRGB();
                    floodFill(x,y);
                    repaint();
                }
            });
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image,0,0,null);
        }
        public void floodFill(int x, int y){
            int rgbPoint =image.getRGB(x,y);
            if (rgbPoint!=rgbColor) return;
            setColor(x,y);
            floodFill(x-1,y);
            floodFill(x+1,y);
            floodFill(x,y-1);
            floodFill(x,y+1);
        }
        public void setColor(int x, int y){
            image.setRGB(x,y, new Color(255, 0, 0, 0).getRGB());
        }
    }
}


 /*
 class DrawPane extends JPanel {
    private static BufferedImage image = null;
    int rgbColor = 0; //Цвет области для заливки
    int x, y; //Координаты начальной точки
    DrawPane() throws IOException {
        File file = new File("src/com/company/data/fish.png");
        image = ImageIO.read(file);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
//System.out.println(x+" "+y);
                rgbColor = image.getRGB(x,y);
                System.out.println(rgbColor);
//image.setRGB
(x,y,new Color(255,0,0).getRGB());
                //rgbColor = new Color(255,0,0).getRGB();
                floodFill(x,y);
                repaint();
        }
    });
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,null);
    }
    public void floodFill(int x, int y){
        int rgbPoint =image.getRGB(x,y);
        if (rgbPoint!=rgbColor) return;
        setColor(x,y);
        floodFill(x-1,y);
        floodFill(x+1,y);
        floodFill(x,y-1);
        floodFill(x,y+1);
    }
    public void setColor(int x, int y){
        image.setRGB(x,y, new Color(255, 0, 0, 0).getRGB());
    }
}
  */