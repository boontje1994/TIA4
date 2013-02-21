
import java.util.*;

public class AgendaData 
{
	private ArrayList<Optreden> data;
	
	public static void main(String[] args)
	{
		new AgendaData();
	}
	
	public AgendaData()
	{
		data = new ArrayList<Optreden>();
	}
	
	public void getData()
	{
		
	}
	
	public void setData(String artist,int pop, String stage , String sTijd, String eTijd)
	{
		data.add(new Optreden(artist,pop,stage,sTijd,eTijd));
	}
	
	
}
