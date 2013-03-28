package Agenda;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Simulator.Crossroad;

public class AgendaData
{
	private ArrayList<Act> dataA;
	private ArrayList<Stage> dataS;
	private ArrayList<Crossroad> dataC;
	private boolean agendaVisible = true;
	private boolean simVisible = false;

	public AgendaData()
	{
		dataA = new ArrayList<Act>();
		dataS = new ArrayList<Stage>();
		dataC = new ArrayList<Crossroad>();
	}

	public void getData()
	{

	}
	
	public void removeAct(String object, int object2, String object3, String object4, String object5)
	{
		int coin = 0;
		boolean succ = false;
		for (Act item : dataA)
		{
			if (item.equals(new Act(object, object2, object3, object4, object5)))
			{
				succ = true;
				break;
			}
			coin++;
		}
		if (succ)
		{
			dataA.remove(coin);
			System.out.println("succes");
		}
		else
			System.out.println("geen succes, de objecten waren:\n" + object + "\n" + object2 + "\n" + object3 + "\n" + object4 + "\n" + object5 + "\n");
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
	
	public ArrayList<Crossroad> getCrossroads()
	{
		return dataC;
	}
	
	public ArrayList<Crossroad> getCrossroadsWithin(Point pos1, Point pos2)
	{
		ArrayList<Crossroad> list = new ArrayList<Crossroad>();
		int smallX;
		int smallY;
		int largeX;
		int largeY;
		if (pos1.x < pos2.x)
		{
			smallX = (int) pos1.getX();
			largeX = (int) pos2.getX();
		}
		else
		{
			smallX = (int) pos2.getX();
			largeX = (int) pos1.getX();
		}
		if (pos1.y < pos2.y)
		{
			smallY = (int) pos1.getY();
			largeY = (int) pos2.getY();
		}
		else
		{
			smallY= (int) pos2.getY();
			largeY = (int) pos1.getY();
		}
		
		for (Crossroad item : dataC)
		{
			if (item.getPosition().x > smallX && item.getPosition().x < largeX && item.getPosition().y > smallY && item.getPosition().y < largeY)
				list.add(item);
		}
		return list;
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
	
	public void addCrossroad(Crossroad cross)
	{
		dataC.add(cross);
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
