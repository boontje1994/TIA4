
public class Agenda
{

	private Artist artist;
	private Performance performance;
	private Stage stage;
	private int day;
	private int timeStart;
	private int timeEnd;
	
	public Agenda()
	{
		
	}
	
	public void setDay(int newDay)
	{
		day = newDay;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public void setTimeStart(int newTimeStart)
	{
		timeStart = newTimeStart;
	}
	
	public int getTimeStart()
	{
		return timeStart;
	}
	
	public void setTimeEnd(int newTimeEnd)
	{
		timeEnd = newTimeEnd;
	}
	
	public int getTimeEnd()
	{
		return timeEnd;
	}
	
	public String getArtist()
	{
		String performer = artist.getArtist();
		return performer;
	}
	
	public String getPerformance()
	{
		String act = performance.getPerformance();
		return act;
	}
	
	public int getStage()
	{
		int tempStage = stage.getStage();
		return tempStage;
	}
	
	public int getPopularity()
	{
		int tempPopularity = artist.getPopularity();
		return tempPopularity;
	}
	
	
}
