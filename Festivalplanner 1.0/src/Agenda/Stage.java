package Agenda;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class Stage 
{
	private String name;
	private int stageNumber;
	private Shape Rect;
	private boolean selected = false;
	private AffineTransform tx;
	private int rotate = 0;
	private int scale = 0;
	private ImageIcon image;
	private Point pos;
	private int Button = 0;
	private int rotateOld = 0;
		
	public Stage()
	{
	}
	
	public Stage(String name)
	{
		this.name = name;
		pos = new Point(10,10);
	}
	
	public Stage(String name, double x, double y, double w, double h, ImageIcon image, int stageNumber)
	{
//		super(name, x, y, w, h, image);
		this.setName(name);
		this.image = image;
		this.setStageNumber(stageNumber);
		Rect = (new Rectangle2D.Double(x,y,w,h));
		setTransformOnce();
	}
	
	

//	@Override
//	public Rectangle2D createIntersection(Rectangle2D r)
//	{
//		return null;
//	}
//
//	@Override
//	public Rectangle2D createUnion(Rectangle2D r)
//	{
//		return null;
//	}
//
//	@Override
//	public int outcode(double x, double y)
//	{
//		return 0;
//	}
//
//	@Override
//	public void setRect(double x, double y, double w, double h)
//	{
//	}
//
//	@Override
//	public boolean isEmpty()
//	{
//		return false;
//	}

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
		g2.translate(-600,-350);
		if(rotate != 0)
		{

			if(rotate < 0)
			{
				tx.rotate(-5*(Math.PI/180),getRect().getBounds().getCenterX(),getRect().getBounds().getCenterY());
//				Rect = tx.createTransformedShape(Rect);
				
			}
			if(rotate > 0)
			{
				tx.rotate(5*(Math.PI/180),getRect().getBounds().getCenterX(),getRect().getBounds().getCenterY());
//				Rect = tx.createTransformedShape(Rect);
			}

			rotate = 0;

			this.tx = tx;
		}
		if(scale != 0)
		{
			if(scale > 0)
			{
//				tx.translate((800/2) - ((Rect.getBounds().getMaxX()-Rect.getBounds().getMinX())*(0.9))/2,
//					    (800/2) - ((Rect.getBounds().getMaxY()-Rect.getBounds().getMinY()))*(0.9)/2);
//				pos = new Point((int)Rect.getBounds().getMinX(), (int)Rect.getBounds().getMinY());
//				tx.translate(-Rect.getBounds().getMinX(), -Rect.getBounds().getMinY());
				tx.scale(0.9, 0.9);
//				tx.translate(pos.x,pos.y);
			}
			if(scale < 0)
			{
//				tx.translate((800/2) - ((Rect.getBounds().getMaxX()-Rect.getBounds().getMinX())*(1.1))/2,
//					    (800/2) - ((Rect.getBounds().getMaxY()-Rect.getBounds().getMinY()))*(1.1)/2);
//				pos = new Point((int)Rect.getBounds().getMinX(), (int)Rect.getBounds().getMinY());
//				tx.translate(-Rect.getBounds().getMinX(), -Rect.getBounds().getMinY());
				tx.scale(1.1,1.1);
//				tx.translate(pos.x,pos.y);
			}
			scale = 0;
			this.tx = tx;
		}

		
		g2.transform(tx);
//		rotaterRect();

		g2.drawImage(image.getImage(),(int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY() ,null);

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getPos() 
	{
		pos = new Point((int)Rect.getBounds().getMinX(),(int)Rect.getBounds().getMinY());
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getButton() {
		return Button;
	}

	public void setButton(int button) {
		Button = button;
	}

	public int getRotateOld() {
		return rotateOld;
	}

	public void setRotateOld(int rotateOld) {
		this.rotateOld = rotateOld;
	}
	
	public void rotaterRect()
	{
		AffineTransform tx = new AffineTransform();
		if(rotateOld > 0)
		{
			tx.rotate(5*(Math.PI/180),getRect().getBounds().getCenterX(),getRect().getBounds().getCenterY());
		}
		if(rotateOld < 0)
		{
			tx.rotate(-5*(Math.PI/180),getRect().getBounds().getCenterX(),getRect().getBounds().getCenterY());
		}
		rotateOld = 0;
		Rect = tx.createTransformedShape(Rect);
	}
	
	
	
}