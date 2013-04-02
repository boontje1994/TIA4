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
	private Shape rect;
	private boolean selected = false;
	private int rotate = 0;
	private int scale = 0;
	private ImageIcon image;
	private Point pos;
	private int button = 0;
	private int rotateOld = 0;
	private boolean rotated;
	private boolean locate;
		
	public Stage()
	{
		this.image = new ImageIcon("images/MainStage.png");
	}
	
	public Stage(String name)
	{
		this.name = name;
		rect = (new Rectangle2D.Double(0,0,0,0));
		this.image = new ImageIcon("images/MainStage.png");
		locate = false;
	}
	
	public Stage(String name, double x, double y, double w, double h)
	{
		this.setName(name);
		rect = (new Rectangle2D.Double(x,y,w,h));
		this.image = new ImageIcon("images/MainStage.png");
		locate = false;
	}
	
	public void initVisual(double x, double y, double w, double h)
	{
		locate = true;
		rect = (new Rectangle2D.Double(x,y,w,h));
		rotated = true;
		pos = new Point((int)rect.getBounds().getMinX(),(int)rect.getBounds().getMinY());
	}
	
	public AffineTransform getTransform()
	{
		AffineTransform tx = new AffineTransform();
        tx.translate(getRect().getBounds().getMinX(),getRect().getBounds().getMinY());
        tx.rotate(rotate*(-5*(Math.PI/180)),0.0,0.0);
		return tx;
	}
	
	
	public void drawStage(Graphics2D g2)
	{
		g2.drawImage(image.getImage(), getTransform(), null);

		if(selected == true)
		{
			if (rotated)
			{
				rect = getTransform().createTransformedShape(rect);
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
		return locate;
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
		return button;
	}

	public void setButton(int button) {
		this.button = button;
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
	
	
	
}