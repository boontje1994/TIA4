package Simulator;
import java.util.*;

import javax.swing.*;

import sun.misc.Compare;

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
    	
        init();
        //waitForChange();
    }
    
    
    public void init()
    {
    	for(int i = 0; i < 100; i++) {
    		 
            //Point2D location = new Point2D.Double(Math.random() * 500, Math.random() * 500);
        	Point2D location = new Point2D.Double(581, 3);
            //TODO laat visitors spawnen bij ingang
            double direction = Math.random() * 2 * Math.PI;
            
            Random r = new Random();
            int imageType = r.nextInt(2); 
            
            Visitor henk = new Visitor(location,direction,imageType);
            
            ArrayList<Point> path = new ArrayList<Point>();
            /*path.add(new Point(581, 3));
            path.add(new Point(200, 3));
            path.add(new Point(200, 250));
            path.add(new Point(300, 250));
            
            henk.setPath(path);*/
            //henk.followPath();
            
            visitor.add(henk);
        }
    }
    
    
    public Point getDestination()
    {
    	return data.getStagesWithActsOnTime(data.getTimePlusMinutes(30)).get(randomWithRange(0, data.getStages().size()-1)).getPos();
    }
    public void update()
    {
    	
    	for(Visitor henk : visitor)
        {
        	if (henk.locationReached() && data.getStages().size() > 0)
        		henk.setPath(givePath(henk.getLocation(), getDestination()));
        	henk.step();
        }
    }
    
    public AI(AgendaData data, int x, int y) {
    	this.data = data;
        this.x = x;
        this.y = y;
        updateVisitor();
        setTarget();
    }
    
    public ArrayList<Point> givePath(Point loc, Point stage)
    {
    	ArrayList<Point> points = data.getCrossroadsWithin(loc, stage);
    	points.add(loc);
    	points.add(new Point(stage.x + (randomWithRange(0, 48)*10), stage.y + 270 + randomWithRange(0, 2)*10));
    	
    	leftTopComparator leftTop = new leftTopComparator();
    	rightTopComparator rightTop = new rightTopComparator();
    	leftBottomComparator leftBot = new leftBottomComparator();
    	rightBottomComparator rightBot = new rightBottomComparator();
    	//Visitor visit = new Visitor();
    	
    	if(leftTop.compare(loc, stage) == 0)
    	{
    		// Visitor must go to leftTop of the field
    		Collections.sort(points,leftTop);
    	}
    	
    	else if(rightTop.compare(loc, stage) == 0)
    	{
    		// Visitor must go to rightTop of the field
    		Collections.sort(points, rightTop);
    	}
    	
    	else if(leftBot.compare(loc, stage) == 0)
    	{
    		//Visitor must go to leftBottom of the field
    		Collections.sort(points, leftBot);
    	}
    	
    	else if(rightBot.compare(loc, stage) == 0)
    	{
    		// Visitor must go to rightBottom of the field
    		Collections.sort(points, rightBot);
    	}

    	// deze regel in elke if/else 
    	// in de if else de richting aangeven
    	//Collections.sort(points, c);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nlist is given");
		for (Point punt : points)
		{
			System.out.println("location of point: " + punt.x + "x" + punt.y);
		}
    	return points;
    	
    }
    
    public void calculatePaths()
    {
    	if (!data.getStages().isEmpty())
    	{
	    	int amountOfStages = 5; //TODO maak dit werkend
	    	for (Stage stage : data.getStages())
	    	{
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
    
    /*public void givePath()
    {
    	for(Visitor v : visitor) {
    		if (v.locationReached())
    		{
    			System.out.println("do");
    			v.setPath(paths.get(0));
    			v.followPath();
    		}
        }
    }*/
    
    int randomWithRange(int min, int max)
    {
       int range = (max - min) + 1;     
       return (int)(Math.random() * range) + min;
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

	public void reset() {
		for (Visitor item: visitor)
    	{
    		item.setVisible(false);
    	}
		ArrayList<Visitor> visitor = new ArrayList <Visitor>();
		init();
		
	}
	
    
    
    
}


class leftTopComparator implements Comparator<Point>
{
    public int compare(Point p1, Point p2)
    {
    	// p1 = location
    	// p2 = stage
		if  (p2.getX() < p1.getX() && p2.getY() > p1.getY())
			return 0;
		else
			return 1;
    }
}
class leftBottomComparator implements Comparator<Point>
{
    public int compare(Point p1, Point p2)
    {
		if  (p2.getX() < p1.getX() && p2.getY() < p1.getY())
			return 0;
		else
			return 1;
    }
}
class rightTopComparator implements Comparator<Point>
{
    public int compare(Point p1, Point p2)
    {
		if  (p2.getX() > p1.getX() && p2.getY() > p1.getY())
			return 0;
		else
			return 1;
    }
}
class rightBottomComparator implements Comparator<Point>
{
    public int compare(Point p1, Point p2)
    {
		if  (p2.getX() < p1.getX() && p2.getY() < p1.getY())
			return 0;
		else
			return 1;
    }
}
