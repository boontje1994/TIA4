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
	private int stepCount;
	private boolean visible;
    
    public Visitor() {
        this.speed = Math.random() * 4;
        atLocation = true;
        stepCount = 0;
        visible = true;
    }
    
    public void setVisible(boolean s)
    {
    	visible = s;
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
    	    		
	   int speed = 10;
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
        visible = true;
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
        if (visible)
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

	public void step() {
		if (!atLocation)
    	{
			if (stepCount < path.size())
			{
				
				if (stepToTarget(path.get(stepCount)))
					stepCount++;
			}
    		/*for (Point2D point : path)
    		{
    			navigateToTarget(point);
    		}*/
			else
				atLocation = true;
    	}
		
	}

	public Point getLocation() {
		Point henk = new Point();
		henk.setLocation(location);
		return henk;
		
	}
    
}