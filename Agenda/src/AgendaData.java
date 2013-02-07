
import java.util.*;

public class AgendaData 
{
	private ArrayList<Gegevens> data;
	
	public static void main(String[] args)
	{
		new AgendaData();
	}
	
	public AgendaData()
	{
		data = new ArrayList<Gegevens>();
	}
	
	public void getData()
	{
		
	}
	
	public void setData(String artist,int pop, String stage , String sTijd, String eTijd)
	{
		data.add(new Gegevens(artist,pop,stage,sTijd,eTijd));
	}
	
	
}
