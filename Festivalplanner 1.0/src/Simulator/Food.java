package Simulator;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;


public class Food extends DrawableObject
{

	public Food()
	{
	}
	
	public Food(String name, double x, double y, double w, double h,
			ImageIcon image)
	{
		super(name, x, y, w, h, image);
	}

	@Override
	public Rectangle2D createIntersection(Rectangle2D r)
	{
		return null;
	}

	@Override
	public Rectangle2D createUnion(Rectangle2D r)
	{
		return null;
	}

	@Override
	public int outcode(double x, double y)
	{
		return 0;
	}

	@Override
	public void setRect(double x, double y, double w, double h)
	{
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

}
