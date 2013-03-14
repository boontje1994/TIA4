package Simulator;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class AI {

    private ArrayList<Visitor> visitor = new ArrayList <Visitor>();
    
    private static int x;
    private static int y;
    
    //Maakt 100 visitors aan met een random locatie, richting en afbeelding.
    public AI() {  
        for(int i = 0; i < 100; i++) {
 
            Point2D location = new Point2D.Double(Math.random() * 500, Math.random() * 500);
            double direction = Math.random() * 2 * Math.PI;
            
            Random r = new Random();
            int imageType = r.nextInt(2);
            
            visitor.add(new Visitor(location,direction,imageType));
        }
    }
    
    public AI(int x, int y) {
        this.x = x;
        this.y = y;
        updateVisitor();
        setTarget();
    }
    
    //Tekent de visitors op de map.
    public void drawVisitor(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for(Visitor v : visitor) {
            v.drawImage(g2);
        }          
    }
    
    public void updateVisitor() {
        for(Visitor v : visitor) {
            v.update(visitor);
        }       
    }
    
    public void setTarget() {
        System.out.println(x + ""+ y);
        
        for(Visitor v : visitor) {
            v.setTarget(x,y);   
        }
    }
    
    public String getData()
    {
    	String snap = "";
    	for (Visitor item: visitor)
    	{
    		snap += "-+-" + item.getAllData();
    	}
    	return snap;
    }
    
    public void setFromData(String data)
    {
    	for (String stream : data.split("/"))
    	{
    		String[] items = stream.split("@");
    		visitor.clear();
    		if (items.length > 1)
    		{
    			System.out.println(items.length);
    			System.out.println("adding visitor on co-ords " + items[0].split(":")[0] + "x" + items[0].split(":")[1] + " and with a speed of " + items[1]);
    			visitor.add(new Visitor(new Point2D.Double(Double.parseDouble(items[0].split(":")[0]), Double.parseDouble(items[0].split(":")[1])),Double.parseDouble(items[1]),1));
    		}
    	}
    }
    
    
    
}