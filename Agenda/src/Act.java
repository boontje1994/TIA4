


public class Act 
{
	private String artist;
	private int popularity;
	private String Stage;
	private String startTime;
	private String endTime;
	
	public Act(String artist, int pop, String stage, String startTime, String eindTime)
	{
		this.artist = artist;
		this.popularity = pop;
		Stage = stage;
		this.startTime = startTime;
		this.endTime = eindTime;
	}
	
	public String getArtist() 
	{
		return artist;
	}
	
	public void setArtist(String artist) 
	{
		this.artist = artist;
	}
	
	public int getPopularity() 
	{
		return popularity;
	}
	
	public void setPopularity(int popularity) 
	{
		this.popularity = popularity;
	}

	public String getStage() 
	{
		return Stage;
	}

	public void setStage(String stage) 
	{
		Stage = stage;
	}

	public String getStartTime() 
	{
		return startTime;
	}

	public void setStartTime(String startTime) 
	{
		this.startTime = startTime;
	}

	public String getEndTime() 
	{
		return endTime;
	}

	public void setEndTime(String endTime) 
	{
		this.endTime = endTime;
	}
	
	
}
