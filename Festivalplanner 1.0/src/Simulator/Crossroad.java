package Simulator;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Crossroad
{
	
	private Shape rect;
	private boolean rotated;
	private ImageIcon image;
	private int ID;
	private Point position;
	private int rotate = 0;
	private int scale = 0;
	private int button = 0;
	private int rotateOld = 0;
	private boolean selected = false;
	
	public Crossroad()
	{
		
	}
	
	public Crossroad(int ID, double x, double y, double w, double h, ImageIcon image)
	{
		this.image = image;
		rect = (new Rectangle2D.Double(x,y,w,h));	
	}
	
	public void initVisual(double x, double y, double w, double h, ImageIcon image)
	{
		this.image = image;
		rect = (new Rectangle2D.Double(x,y,w,h));
		rotated = true;
	}
	
	public AffineTransform getTransform()
	{
		AffineTransform tx = new AffineTransform();
		tx.translate(getRect().getBounds().getMinX(),getRect().getBounds().getMinY());
		tx.rotate(rotate*(-5*(Math.PI/180)),0.0,0.0);
		return tx;
	}
	
	public void drawCrossroad(Graphics2D g2)
	{
		g2.drawImage(image.getImage(), getTransform(), null);
		
		if(selected == true)
		{
			if(rotated)
			{
				rect = getTransform().createTransformedShape(rect);
				rotated = false;
			}
			g2.draw(rect);
		}
	}
	
	public Shape getRect()
	{
		return rect;
	}
	
	public boolean isLocated()
	{
		return (position != null);
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
	
	public void setRotate(int rotate)
	{
		this.rotate = rotate;
	}
	
	public int getRotate()
	{
		return rotate;
	}
	
	public Point getPosition()
	{
		position = new Point((int)rect.getBounds().getMinX(),(int)rect.getBounds().getMinY());
		return position;
	}
	
	public void setPosition(Point position)
	{
		rect = (new Rectangle2D.Double(((int)position.getX()/10)*10, ((int)position.getY()/10)*10, (int)rect.getBounds().getWidth(),(int) rect.getBounds().getHeight()));
		this.position = new Point((int)rect.getBounds().getMinX(),(int)rect.getBounds().getMinY());
	}
	
	public int getScale()
	{
		return scale;
	}
	
	public void setScale(int scale)
	{
		this.scale = scale;
	}
	
	public int getButton()
	{
		return button;
	}
	
	public void setButton(int button)
	{
		this.button = button;
	}
	
	public int getRotateOld()
	{
		return rotateOld;
	}
	
	public void setRotateOld(int rotateOld)
	{
		this.rotateOld = rotateOld;
	}
	
	public boolean isClicked(int x, int y)
	{
		return (rect.contains(x,y));
	}
	
	public int getID()
	{
		return ID;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
}
