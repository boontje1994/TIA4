
public abstract class Artist
{

	public String artist;
	public int popularity;
	
	protected Artist()
	{
		
	}
	
	public void setArtist(String newArtist)
	{
		artist = newArtist;
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public void setPopularity(int newPopularity)
	{
		popularity = newPopularity;
	}
	
	public int getPopularity()
	{
		return popularity;
	}
}
