package Agenda;
import java.awt.Color;
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
	private Shape rect;
	private boolean selected = false;
	private int rotate = 0;
	private int scale = 0;
	private ImageIcon image;
	private Point pos;
	private int Button = 0;
	private int rotateOld = 0;
	private boolean rotated;
	private boolean scaleSign = false;
	private boolean scaleSign2 = false;
	private int scaleMag;
		
	public Stage()
	{
	}
	
	public Stage(String name)
	{
		this.name = name;
		rect = (new Rectangle2D.Double(0,0,0,0));
	}
	
	public Stage(String name, double x, double y, double w, double h, ImageIcon image)
	{
		this.setName(name);
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
//        tx.rotate(rotate*(-5*(Math.PI/180)),0.0,0.0);
        tx.rotate(Math.toRadians(rotate),rect.getBounds().getWidth()/2,rect.getBounds().getHeight()/2);
		return tx;
	}
	
	
	public void drawStage(Graphics2D g2)
	{
		
		if(scaleSign)
		g2.setColor(Color.ORANGE);
		else
		g2.setColor(Color.RED);
		g2.drawImage(image.getImage(), getTransform(), null);

		if(selected == true)
		{
			if (rotated)
			{
				getTransform();
//				rect = getTransform().createTransformedShape(rect);
				AffineTransform tx = new AffineTransform();
				tx.rotate(Math.toRadians(rotate),rect.getBounds().getMinX(),rect.getBounds().getMinY());
				rect = tx.createTransformedShape(rect);
				rotated = false;
			}

			
			g2.draw(rect);
		}

		
	}



	public Shape getRect() {
		return rect;
	}
	
	public boolean isLocated()
	{
		return (pos != null);
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
//        rotated = true;
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
		pos = new Point((int)rect.getBounds().getMinX(),(int)rect.getBounds().getMinY());
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
		rect = (new Rectangle2D.Double(pos.getX(),pos.getY(),rect.getBounds().getWidth(),rect.getBounds().getHeight()));
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
	public boolean isClicked(int x, int y)
	{
		return (rect.contains(x, y));
	}
	
	public void setScaleSign(boolean set)
	{
		scaleSign = set;
		scaleSign2 = true;
	}
	
	public boolean getScaleSign()
	{
		return scaleSign;
	}

	public int getScaleMag() {
		return scaleMag;
	}

	public void setScaleMag(int scaleMag) {
		this.scaleMag = scaleMag;
	}

	public boolean isScaleSign2() {
		return scaleSign2;
	}

	public void setScaleSign2(boolean scaleSign2) {
		this.scaleSign2 = scaleSign2;
	}
	
	
	
}