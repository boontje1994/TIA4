package Simulator;
import java.util.*;
import javax.swing.*;

import Agenda.AgendaData;
import Agenda.Stage;

import java.awt.*;
import java.awt.geom.Point2D;

public class AI {

    private ArrayList<Visitor> visitor = new ArrayList <Visitor>();
    
    private static int x;
    private static int y;
    private static ArrayList<ArrayList<Point>> paths;
    private AgendaData data;
    
    //Maakt 100 visitors aan met een random locatie, richting en afbeelding.
    public AI(AgendaData data) {  
    	this.data = data;
    	
        for(int i = 0; i < 100; i++) {
 
            //Point2D location = new Point2D.Double(Math.random() * 500, Math.random() * 500);
        	Point2D location = new Point2D.Double(581, 3);
            //TODO laat visitors spawnen bij ingang
            double direction = Math.random() * 2 * Math.PI;
            
            Random r = new Random();
            int imageType = r.nextInt(2); 
            
            Visitor henk = new Visitor(location,direction,imageType);
            
            ArrayList<Point> path = new ArrayList<Point>();
            path.add(new Point(581, 3));
            path.add(new Point(200, 3));
            path.add(new Point(200, 250));
            path.add(new Point(300, 250));
            
            henk.setPath(path);
            //henk.followPath();
            
            visitor.add(henk);
        }
        //waitForChange();
    }
    
    public AI(AgendaData data, int x, int y) {
    	this.data = data;
        this.x = x;
        this.y = y;
        updateVisitor();
        setTarget();
    }
    
    public void calculatePaths()
    {
    	if (!data.getStages().isEmpty())
    	{
	    	int amountOfStages = 5; //TODO maak dit werkend
	    	for (Stage stage : data.getStages())
	    	{
	    		System.out.println("path made!");
	    		ArrayList<Point> path = new ArrayList<Point>();
	    		Point stageloc = stage.getPos();
	    		Point entrance = new Point(0,0);
	    		Point point = entrance;
	    		while (!point.equals(stageloc))
	    		{
	    			if (point.getX() != stageloc.getX())
	    			{
	    				point.setLocation(stageloc.getX(), point.getY());
	    			}
	    			else if (point.getY() != stageloc.getY());
	    			{
	    				point.setLocation(point.getX(), stageloc.getY());
	    			}
	    			path.add(point);
	    		}
	    		paths.add(path);
	    	}
    	}
    	
    }
    
    public void givePath()
    {
    	for(Visitor v : visitor) {
    		if (v.locationReached())
    		{
    			System.out.println("do");
    			v.setPath(paths.get(0));
    			v.followPath();
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
    
    public void waitForChange()
    {
    	System.out.println("wait loop started!");
    	Runnable run1 = new Runnable() {
            public void run() {
            	while (true)
            	{
	                try {
	                    Thread.sleep(1000);
	                    
	                    for(Visitor henk : visitor)
	                    {
	                    	//if (henk.locationReached())
	                    		henk.followPath();
	                    }
	                    System.out.println("check!");
	                    //calculatePaths();
                    	//givePath();
	                    //System.out.println(ai.getData());
	                } catch(Exception e) {
	                    System.out.println(e);
	                }
            	}
            }
            
        };
        Thread thread1 = new Thread(run1);
        thread1.start();
    }
    
    
    
}