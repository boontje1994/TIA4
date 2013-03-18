package Simulator;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class AI {

    private ArrayList<Visitor> visitor = new ArrayList <Visitor>();
    
    private static int x;
    private static int y;
    private static ArrayList<ArrayList<Point2D>> paths;
    
    //Maakt 100 visitors aan met een random locatie, richting en afbeelding.
    public AI() {  
        for(int i = 0; i < 100; i++) {
 
            Point2D location = new Point2D.Double(Math.random() * 500, Math.random() * 500);
            //TODO laat visitors spawnen bij ingang
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
    
    public void calculatePaths()
    {
    	
    }
    
    public void givePath()
    {
    	for(Visitor v : visitor) {
    		if (v.locationReached())
    		{
    			v.setPath(paths.get(0));
    		}
        }
    }
    
    //Tekent de visitors op de map.
    public void drawVisitor(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for(Visitor v : visitor) {
            v.drawImage(g2);
        }          
    }
    
    public void addRemoveVisitors()
    {
    	int totalPopularity = 25; //maak manier om populariteit te meten
    	if (totalPopularity > 20)
    	{
    		if (visitor.size() > 100) //make sane values
    		{
    			//kill visitors here
    		}
    		else if (visitor.size() <= 100) //make sane values
    		{
    			//add visitors here
    		}
    	}
    	else if (totalPopularity <= 20)
    	{
    		if (visitor.size() > 100) //make sane values
    		{
    			//kill visitors here
    		}
    		else if (visitor.size() <= 100) //make sane values
    		{
    			//add visitors here
    		}
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
    	for (String stream : data.split("-+-"))
    	{
    		String[] items = stream.split("=+=");
    		visitor.clear();
    		if (items.length > 0)
    		{
    			System.out.println(items.length);
    			System.out.println("adding visitor from string " + stream);
    			//visitor.add(new Visitor(new Point2D.Double(Double.parseDouble(items[1].split(":")[0]), Double.parseDouble(items[1].split(":")[1])),Double.parseDouble(items[2]),1));
    		}
    	}
    }
    
    
    
}