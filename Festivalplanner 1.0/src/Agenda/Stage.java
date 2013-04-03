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
	private int button = 0;
	private int rotateOld = 0;
	private boolean rotated;
	private boolean locate;
	private boolean scaleSign = false;
	private boolean scaleSign2 = false;
	private int scaleMag;
		
	public Stage()
	{
		ImageIcon icon = new ImageIcon("images/MainStage.png");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(480, 280, java.awt.Image.SCALE_SMOOTH);
		this.image = new ImageIcon(newImg);
	}
	
	public Stage(String name)
	{
		this.name = name;
		rect = (new Rectangle2D.Double(0,0,0,0));
		ImageIcon icon = new ImageIcon("images/MainStage.png");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(480, 280, java.awt.Image.SCALE_SMOOTH);
		this.image = new ImageIcon(newImg);
		locate = false;
	}
	
	public Stage(String name, double x, double y, double w, double h)
	{
		this.setName(name);
		rect = (new Rectangle2D.Double(x,y,w,h));
		ImageIcon icon = new ImageIcon("images/MainStage.png");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(480, 280, java.awt.Image.SCALE_SMOOTH);
		this.image = new ImageIcon(newImg);
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
		return pos;
	}

	public void setPos(Point pos) {
		rect = (new Rectangle2D.Double(((int)pos.getX()/10)*10,((int)pos.getY()/10)*10,(int)rect.getBounds().getWidth(),(int)rect.getBounds().getHeight()));
		this.pos = new Point((int)rect.getBounds().getMinX(),(int)rect.getBounds().getMinY());
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