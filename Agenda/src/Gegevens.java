


public class Gegevens 
{
	private String artiest;
	private int populariteit;
	private String Stage;
	private String startTijd;
	private String eindTijd;
	
	public Gegevens(String artist, int pop, String stage, String starttijd, String eindtijd)
	{
		artiest = artist;
		populariteit = pop;
		Stage = stage;
		startTijd = starttijd;
		eindTijd = eindtijd;
	}
	
	public String getArtiest() 
	{
		return artiest;
	}
	
	public void setArtiest(String artiest) 
	{
		this.artiest = artiest;
	}
	
	public int getPopulariteit() 
	{
		return populariteit;
	}
	
	public void setPopulariteit(int populariteit) 
	{
		this.populariteit = populariteit;
	}

	public String getStage() 
	{
		return Stage;
	}

	public void setStage(String stage) 
	{
		Stage = stage;
	}

	public String getStartTijd() 
	{
		return startTijd;
	}

	public void setStartTijd(String startTijd) 
	{
		this.startTijd = startTijd;
	}

	public String getEindTijd() 
	{
		return eindTijd;
	}

	public void setEindTijd(String eindTijd) 
	{
		this.eindTijd = eindTijd;
	}
	
	
}
