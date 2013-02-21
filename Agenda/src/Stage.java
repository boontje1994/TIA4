import java.util.*;

public class Stage 
{
	private ArrayList<Act> acts;
	public Stage()
	{
		acts = new ArrayList<Act>();
	}
	
	public void setAct(String artist,int pop,String stage,String startTime,String endTime)
	{
		acts.add(new Act(artist, pop, stage, startTime, endTime));
	}
	
	public Act setAct(int index)
	{
		return acts.get(index);
	}
	
}
