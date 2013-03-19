package Simulator;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class User {

    private static int posX;
    private static int posY;

    public User() {       
        posX = 60;
        posY = 60;
    }

    public void forward() { 
        posY -= 4;
    }
    
    public void backward() {
        posY += 4;
    }
    
    public void left() {
        posX -= 4;
    }
    
    public void right() {
        posX += 4;
    }
    
    public void drawUser(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;  
        Color c = new Color(228,5,5);  
        Ellipse2D ellipse = new Ellipse2D.Double(posX,posY,10,10);
        g2.setColor(c);
        g2.fill(ellipse);       
    }

}