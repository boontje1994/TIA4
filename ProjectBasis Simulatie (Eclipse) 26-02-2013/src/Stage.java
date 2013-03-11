import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class Stage extends DrawableObject
{
	private int stageNumber;
	
	public Stage(String name, double x, double y, double w, double h, ImageIcon image, int stageNumber)
	{
		super(name, x, y, w, h, image);
		this.setStageNumber(stageNumber);
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
	
	
	
}
