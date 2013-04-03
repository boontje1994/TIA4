package Simulator;
import javax.swing.*;

import com.sun.org.apache.xml.internal.utils.StopParseException;

import java.sql.Time;
import java.util.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Visitor {

    private Point2D location;
    private double direction;
    private Image image;
    private ArrayList<Point> path;
	private boolean atLocation;
	private int stepCount;
	private int spawnCountDown;
	private String end;
	private String time;
    
    public Visitor() {
        atLocation = true;
        stepCount = 0;
        path = new ArrayList<Point>();
    }
 
    
    public void followPath()
    {
    	System.out.println("path following initialized");
    	
    	if (!atLocation)
    	{
    		for (Point2D point : path)
    		{
    			navigateToTarget(point);
    		}
    		atLocation = true;
    	}
    }
    
    private void navigateToTarget(final Point2D point) {
    	
    	while (!point.equals(location))
    	{
    		
     	   if (point.getX() > location.getX())
     		   location.setLocation(location.getX()+0.01, location.getY());
    		else if (point.getX() < location.getX())
    			location.setLocation(location.getX()-0.01, location.getY());
    		if (point.getY() > location.getY())
    			location.setLocation(location.getX(), location.getY()-0.01);
    		else if (point.getY() < location.getY())
    			location.setLocation(location.getX(), location.getY()+0.01); 
    		else
    			System.out.println("jump");
    		
    		double x = point.getX() - location.getX();
    		double y = point.getY() - location.getY();
    		double angle = Math.atan2(y, x);
    		direction = angle;
    		//Thread.sleep(1);
    		if ((int)point.getX() == (int)location.getX() && (int)point.getX() == (int)location.getX())
    		{
    			location.setLocation(point);
    			break;
    		}
    		//System.out.println("a visitor's location changed to " + location.getX() + "x" + location.getY());
    	}
	}
    
 private boolean stepToTarget(final Point2D point) {
    	    		
	   int speed = 1;
       if (point.getX() > location.getX())
    	   location.setLocation(location.getX()+speed, location.getY());
       else if (point.getX() < location.getX())
    		location.setLocation(location.getX()-speed, location.getY());
       if (point.getY() > location.getY())
    		location.setLocation(location.getX(), location.getY()+speed);
       else if (point.getY() < location.getY())
    		location.setLocation(location.getX(), location.getY()-speed); 
//       else
    //	   location.setLocation(point);
    		
       double x = point.getX() - location.getX();
       double y = point.getY() - location.getY();
       double angle = Math.atan2(y, x);
       direction = angle;
       //Thread.sleep(1);
       //location.setLocation(point);
//       System.out.println("a visitor's location changed to " + location.getX() + "x" + location.getY());
       if (((int)point.getX()/10) == ((int)location.getX()/10) && ((int)point.getY()/10) == ((int)location.getY()/10))
       {
    	   location.setLocation(point);
    	   return true;
       }
       else
    	   return false;
       
       
       //return (point.equals(location));
       
	}

	public void setPath(ArrayList<Point> arrayList, String endTime)
    {
    	this.path = arrayList;
    //	System.out.println("path set");
    	atLocation = false;
    	stepCount = 0;
    	end = endTime;
    }
    
    public Visitor(Point2D location, double direction, int imageType) {
        this.location = location;
        this.direction = direction;  
        //Afbeeldingen
        switch(imageType) {
            case 0:
                image = new ImageIcon("images/visitor1.png").getImage();
                break;
            case 1:
                image = new ImageIcon("images/visitor2.png").getImage();
            break;
        }
        atLocation = true;
        stepCount = 0;
        path = new ArrayList<Point>();
        spawnCountDown = 50;
        time = "0:01";
        end = "0:00";
        
    }
        
   /* public void update(ArrayList<Visitor> otherVisitor) {
    	if (spawnCountDown > 0)
    	{
    		spawnCountDown -= 1;
    	}
        Point2D oldLocation = location;
		location = new Point2D.Double((int)location.getX() + (int)(speed * Math.cos(direction)), 
									  (int)location.getY() + (int)(speed * Math.sin(direction)));
		
		/*if(location.getX() < 0 || location.getY() < 0 || location.getX() > 800 || location.getY() > 800)
			direction += Math.PI;*/
		
//		double x = targetLocation.getX() - location.getX();
//		double y = targetLocation.getY() - location.getY();
//		double angle = Math.atan2(y, x);
//		direction = angle;
//		
//		boolean collision = false;
		/*for(Visitor v : otherlnisitor) 
		{
			if(v == this)
				continue;
			if(collidesWith(v))
				collision = true;
		}
		
		if(collision)
		{
			location = oldLocation;
		}*/
    //}
    
    public Visitor(Point loc, Double dir, ArrayList<Point> pth, boolean atLo,
			int step, int noSpa, String end2, int img) {
    	this.location = loc;
        this.direction = dir;  
        //Afbeeldingen
        switch(img) {
            case 0:
                image = new ImageIcon("images/visitor1.png").getImage();
                break;
            case 1:
                image = new ImageIcon("images/visitor2.png").getImage();
            break;
        }
        atLocation = atLo;
        stepCount = step;
        path = pth;
        spawnCountDown = noSpa;
        time = "0:01";
        end = end2;
	}


	public boolean isAlive()
    {
    	return (spawnCountDown == 0);
    }
    
    private boolean collidesWith(Visitor b) {
		double x = b.location.getX() - location.getX();
		double y = b.location.getY() - location.getY();
		
		double distSq = x*x+y*y;
		if(distSq < 28*28)
			return true;
		else
			return false;
	}
    
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(image,getTransform(),null);
    }

    private AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(location.getX(), location.getY());
        tx.rotate(direction,15,32);
        return tx;
    }
    

    public String getAllData()
    {
    	String vis = "@" + location.getX() + "X" + location.getY();
    	vis += "@" + direction + "@";
    	for (Point item : path)
    	{
    		vis += "!" + item.x + "X" + item.y;
    	}
    	vis += "@" + atLocation;
    	vis += "@" + stepCount;
    	vis += "@" + spawnCountDown;
    	vis += "@" + end;
    	//vis += "@" + image.
    	//vis += "@" + speed;
    	return vis;
    }

	public boolean locationReached()
	{
		return atLocation && (end.equals("NaN"))?true:(Double.parseDouble(time.replace(":", ".")) > Double.parseDouble(end.replace(":", ".")));
	}

	public void step(String time) {
		this.time = time;
		if (!atLocation)
    	{
			if (stepCount < path.size())
			{
				
				
				
				
				boolean stept = false;
				for (int i=0; i<10; i++)
				{
					stept = stepToTarget(path.get(stepCount));
				}
				
				if (stept)
					stepCount++;
			}
    		/*for (Point2D point : path)
    		{
    			navigateToTarget(point);
    		}*/
			else
				atLocation = true;
    	}
		if (spawnCountDown > 0)
    	{
    		spawnCountDown -= 1;
    	}
		
	}

	public Point getLocation() {
		Point henk = new Point();
		henk.setLocation(location);
		return henk;
		
	}

    
}