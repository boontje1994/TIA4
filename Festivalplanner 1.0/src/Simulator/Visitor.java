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
    
    public Visitor() {
        this.speed = Math.random() * 4;
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
    
    public String getAllData()
    {
    	String vis = location.getX() + ":" + location.getY();
    	//vis += "*" + targetLocation.getX() + ":" + targetLocation.getY();
    	vis += "*" + direction;
    	//vis += "*" + image.
    	//vis += "*" + speed;
    	return vis;
    }
    
}