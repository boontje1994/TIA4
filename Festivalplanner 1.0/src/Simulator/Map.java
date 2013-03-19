package Simulator;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.util.*;

public class Map {
   
   private static int xLength = 2500;
   private static int yLength = 1500;
   
   private int xTotal;
   private int yTotal;
 
   int width = 15;
   int height = 15;
   
   public Map() {
       
   }
   
   public Map(int x, int y) {
       this.xLength = x;
       this.yLength = y; 
   }

   //Weg
//   public void drawRoad(Graphics g) {
//       Graphics2D g2 = (Graphics2D)g;
//       Color c1 = new Color(48,48,48);
//       Color c2 = new Color(255,255,255);
//       Color c3 = new Color(255,0,0);
//       
//       //Road
//       Rectangle2D road1 = new Rectangle2D.Double(-800,575,1815,65);
//       Rectangle2D road2 = new Rectangle2D.Double(1015,-800,65,1400);
//       g2.setColor(c1);  
//       g2.fill(road1);
//       g2.fill(road2);
//      
//       //Corner
//       Ellipse2D corner1 = new Ellipse2D.Double(995,555,100,100);
//       Ellipse2D corner2 = new Ellipse2D.Double(1035,595,20,20);
//       g2.setColor(c1);
//       g2.fill(corner1);
//       g2.setColor(c2);
//       g2.fill(corner2);
//       
//       //Dashed
//       for(int x = -800; x < 1015; x += 40) { 
//           for(int y = -800; y < 600; y += 40) {
//               Rectangle2D dashed1 = new Rectangle2D.Double(x,605,20,5);
//               Rectangle2D dashed2 = new Rectangle2D.Double(1045,y,5,20);
//               g2.setColor(c2);
//               g2.fill(dashed1);
//               g2.fill(dashed2);
//           }
//       }
//      
//       //Car
//       for(int i = 0; i < 1000; i++) {
//            Rectangle2D car = new Rectangle2D.Double(80,615,45,20);    
//            g2.setColor(c3);
//            g2.fill(car);
//       }
//    }
   
   //In/Uitgang
   public void drawEntrance(Graphics g) {
       Graphics2D g2 = (Graphics2D)g;
       Color c = new Color(155,151,244);
       calculate();
       Rectangle2D rectangle = new Rectangle2D.Double(xTotal+70,-40,90,80);
       g2.setColor(c);
       g2.fill(rectangle);
   }
    
   //Bomen
   public void drawTree(Graphics g) {
       Graphics2D g2 = (Graphics2D)g;
       Color c1 = new Color(128,64,0); //Stam
       Color c2 = new Color(52,137,48); //Bladeren
       Color c3 = new Color(36,208,74); //Bladeren
       calculate();
       for(int x = -xTotal-60; x <= xTotal+150; x += 40) {
           for(int y = -yTotal-60; y <= yTotal+200; y += 60) {
             Ellipse2D e1 = new Ellipse2D.Double(x+10,y+35,10,20); //Stam
             Ellipse2D e2 = new Ellipse2D.Double(x,y,30,45); //Bladeren
             Ellipse2D e3 = new Ellipse2D.Double(x+5,y+15,15,25); //Bladeren
             g2.setColor(c1);
             g2.fill(e1);
             g2.setColor(c2);
             g2.fill(e2);
             g2.setColor(c3);
             g2.fill(e3);
          }
       }
   }

   //Berg
   public void drawMountain(Graphics2D g) {
       Graphics2D g2 = (Graphics2D)g;
       calculate();
       Color c1 = new Color(68,191,62);
       Color c2 = new Color(94,201,88);
       Rectangle2D firstLayer = new Rectangle2D.Double(-xTotal-225,-yTotal-225,xLength+550,yLength+610);
       Rectangle2D secondLayer = new Rectangle2D.Double(-xTotal-125,-yTotal-125,xLength+350,yLength+420);     
       g2.setColor(c1);
       g2.fill(firstLayer);
       g2.setColor(c2);
       g2.fill(secondLayer);       
   }
   
   //Veld
   public void drawField(Graphics g) { 
       Graphics2D g2 = (Graphics2D)g;
       calculate();
       Rectangle2D rectangle = new Rectangle2D.Double(-xTotal+19,-yTotal+59,xLength+60,yLength+60);
       Color c = new Color(64,179,57);
       g2.setColor(c);
       g2.fill(rectangle);    
   }

   //GRID 
   public void drawGRID(Graphics g){    
       Graphics2D g2 = (Graphics2D)g;
       calculate();
       for(int x = -xTotal; x <= xTotal; x += 40) {
           for(int y = -yTotal; y <= yTotal; y += 40) {
               //direction.get(y).get(x).draw(g,x,y);
               Rectangle2D rec = new Rectangle2D.Double(x+37,y+77,40,40);
               Color c = new Color(16,199,57);
               g2.setColor(c);
               g2.draw(rec);                
           }
       }
   }
   
   public void calculate() {
       xTotal = xLength / 2;
       yTotal = yLength / 2;        
   }
   
}