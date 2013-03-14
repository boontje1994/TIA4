package Agenda;
import java.util.*;

public class AgendaData
{
	private ArrayList<Act> dataA;
	private ArrayList<Stage> dataS;
	private boolean agendaVisible = true;
	private boolean simVisible = false;

	public AgendaData()
	{
		dataA = new ArrayList<Act>();
		dataS = new ArrayList<Stage>();
	}

	public void getData()
	{

	}
	
	public ArrayList<Act> getActs()
	{
		return dataA;
	}
	
	public ArrayList<Act> getActsFromStage(String stage)
	{
		ArrayList<Act> datas = new ArrayList<Act>();
		for (Act item : dataA)
		{
			if (item.getStage().equals(stage))
			{
				datas.add(item);
			}
		}
		return datas;
	}
	
	public ArrayList<Stage> getStages()
	{
		return dataS;
	}
	
	public boolean doesStageExist(String stage)
	{
		boolean yes = false;
		for (Stage item : dataS)
		{
			if (item.equals(stage))
			{
				yes = true;
				break;
			}
		}
		return yes;
	}
	
	public void importData(ArrayList<Act> data1, ArrayList<Stage> data2)
	{
		dataA = data1;
		dataS = data2;
	}

	public void addAct(Act act)
	{
		dataA.add(act);
	}

	public void addStage(Stage stage)
	{
		dataS.add(stage);
	}
	
	public void clear()
	{
		dataA = new ArrayList<Act>();
		dataS = new ArrayList<Stage>();
	}

	public boolean isSimVisible() {
		return simVisible;
	}
	
	public boolean isAgendaVisible() {
		return agendaVisible;
	}

	public void setSimVisible(boolean b) {
		simVisible = b;
		
	}

	public void setAgendaVisible(boolean b) {
		agendaVisible = b;
		
	}

}