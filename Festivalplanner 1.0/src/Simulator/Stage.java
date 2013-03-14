package Simulator;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class Stage extends DrawableObject
{
	private int stageNumber;
	private Shape Rect;
	private boolean selected = false;
	private AffineTransform tx;
	private int rotate = 0;
		
	public Stage(String name, double x, double y, double w, double h, ImageIcon image, int stageNumber)
	{
		super(name, x, y, w, h, image);
		this.setStageNumber(stageNumber);
		Rect = (new Rectangle2D.Double(x,y,w,h));
		setTransformOnce();
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

	public int getStageNumber()
	{
		return stageNumber;
	}

	public void setStageNumber(int stageNumber)
	{
		this.stageNumber = stageNumber;
	}
	
	public AffineTransform setTransformOnce()
	{
		AffineTransform tx = new AffineTransform();
//		tx.translate(super.getX(),super.getY());
		this.tx = tx;
		return tx;
	}
	
	public void updateCo()
	{
//		tx.((int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY());
		tx.rotate(0,(int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY());
	}
	
	public void setTransform(AffineTransform form)
	{
		this.tx = form;
	}
	
	public AffineTransform getTransform()
	{
		return tx;
	}
	
	
	public void drawStage(Graphics2D g2)
	{
//		if(rotate == 0)
//		{
//			g2.drawImage(super.getImage().getImage(),(int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY(),null);
//		}
		if(rotate != 0)
		{
//			tx = new AffineTransform();
			if(rotate < 0)
			{
				tx.rotate(-5*(Math.PI/180),getRect().getBounds().getCenterX(),getRect().getBounds().getCenterY());
//				g2.rotate(-5*(Math.PI/180),getRect().getBounds().getCenterX(),getRect().getBounds().getCenterY());
			}
			if(rotate > 0)
			{
				tx.rotate(5*(Math.PI/180),getRect().getBounds().getCenterX(),getRect().getBounds().getCenterY());
//				g2.rotate(5*(Math.PI/180),getRect().getBounds().getCenterX(),getRect().getBounds().getCenterY());
			}
//			Rect = tx.createTransformedShape(Rect);

//			g2.rotate(tx);
//			tx.translate((int)Rect.getBounds().getCenterX(),(int)Rect.getBounds().getCenterY());
//			tx.translate((int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY());
			
//			tx.translate((int)Rect.getBounds().getCenterX(),(int)Rect.getBounds().getCenterY());
			
			rotate = 0;
//			this.tx.setTransform(tx);
			this.tx = tx;
		}
//		tx.translate((int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY());
		
		g2.transform(tx);
		
//		g2.drawImage(super.getImage().getImage(),(int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY(),null);
//		tx.translate((int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY());
		
//		tx.translate((int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY());
		g2.drawImage(super.getImage().getImage(),(int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY() ,null);
		
//		g2.drawImage(super.getImage().getImage(),(int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY(),null);
		
//		g2.drawImage(super.getImage().getImage(),tx,null);
//		if(rotate != 0)
//		{
//			AffineTransform tx = new AffineTransform();
//			if(rotate < 0)
//			{
//				tx.rotate(-5*(Math.PI/180),getRect().getBounds().getCenterX(),getRect().getBounds().getCenterY());
//			}
//			if(rotate > 0)
//			{
//				tx.rotate(5*(Math.PI/180),getRect().getBounds().getCenterX(),getRect().getBounds().getCenterY());
//			}
//			rotate = 0;
//			Rect = tx.createTransformedShape(Rect);
//			g2.transform(tx);
//		}
		if(selected == true)
		{
			g2.draw(Rect);
		}

		
	}



	public Shape getRect() {
		return Rect;
	}



	public void setRect2(Shape rect) {
		Rect = rect;
	}



	public boolean isSelected() {
		return selected;
	}



	public void setSelected(boolean selected) {
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
	
	
	
}
