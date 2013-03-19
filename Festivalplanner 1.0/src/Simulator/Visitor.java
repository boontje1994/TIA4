package Simulator;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Visitor {

    private Point2D location;
    private Point2D targetLocation;
    private double direction;
    private Image image;
    private double speed;
    private ArrayList<Point> path;
	private boolean atLocation;
    
    public Visitor() {
        this.speed = Math.random() * 4;
        atLocation = true;
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
    		
    		
    		
    		
    		
    		Runnable run1 = new Runnable() {
                public void run() {
    	               try {
    	            	 //Point2D point2 = new Point2D.Double();
    	            	   if (point.getX() > location.getX())
    	           			//point.setLocation(point.getX()-0.001, point.getY());
    	            	   location.setLocation(location.getX()+1, location.getY());
    	           		else if (point.getX() < location.getX())
    	           			location.setLocation(location.getX()-1, location.getY());
    	           		if (point.getY() > location.getY())
    	           			location.setLocation(location.getX(), location.getY()+1);
    	           		else if (point.getY() < location.getY())
    	           			location.setLocation(location.getX(), location.getY()-1);  
    	           		
    	           		double x = point.getX() - location.getX();
    	        		double y = point.getY() - location.getY();
    	        		double angle = Math.atan2(y, x);
    	        		direction = angle;
    	                 Thread.sleep(1);
    	                 
    	                 //location = point;
    	                 //System.out.println("check!");
    	                //calculatePaths();
                       	//givePath();
    	                   //System.out.println(ai.getData());
    	               } catch(Exception e) {
    	                   System.out.println(e);
    	               }
                }
                
            };
            Thread thread1 = new Thread(run1);
            thread1.start();
    		
    		
    		
    		System.out.println("a visitor's location changed to " + location.getX() + "x" + location.getY());
    	}
	}

	public void setPath(ArrayList<Point> arrayList)
    {
    	this.path = arrayList;
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
    }
        
    public void update(ArrayList<Visitor> otherVisitor) {
        Point2D oldLocation = location;
		location = new Point2D.Double(location.getX() + speed * Math.cos(direction), 
									  location.getY() + speed * Math.sin(direction));
		
		if(location.getX() < 0 || location.getY() < 0 || location.getX() > 800 || location.getY() > 800)
			direction += Math.PI;
		
		double x = targetLocation.getX() - location.getX();
		double y = targetLocation.getY() - location.getY();
		double angle = Math.atan2(y, x);
		direction = angle;
		
		boolean collision = false;
	
		for(Visitor v : otherVisitor) 
		{
			if(v == this)
				continue;
			if(collidesWith(v))
				collision = true;
		}
		
		if(collision)
		{
			location = oldLocation;
		}
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
    
    public void setTarget(int x, int y) {
		targetLocation = new Point2D.Double(x,y);		
	}
    
    public void setTarget(Point2D point) {
		targetLocation = point;		
	}
    
    public String getAllData()
    {
    	String vis = location.getX() + ":" + location.getY();
    	//vis += "*" + targetLocation.getX() + ":" + targetLocation.getY();
    	vis += "=+=" + direction;
    	//vis += "*" + image.
    	//vis += "*" + speed;
    	return vis;
    }

	public boolean locationReached()
	{
		return atLocation;
	}
    
}