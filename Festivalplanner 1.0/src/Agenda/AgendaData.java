package Agenda;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Simulator.Crossroad;
import Simulator.MainWindow;
import Simulator.Visitor;

public class AgendaData
{
	private ArrayList<Act> dataA;
	private ArrayList<Stage> dataS;
	private ArrayList<Crossroad> dataC;
	private boolean agendaVisible = true;
	private boolean simVisible = false;
	private int minute;
	private int tenthMinute;
	private int hour;
	private Point exit;

	public AgendaData()
	{
		dataA = new ArrayList<Act>();
		dataS = new ArrayList<Stage>();
		dataC = new ArrayList<Crossroad>();
		hour = 8;
		minute = 00;
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
	
	public ArrayList<Point> getCrossroadsWithin(Point pos1, Point pos2)
	{
		ArrayList<Point> list = new ArrayList<Point>();
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
				list.add(new Point(item.getPosition().x + 50, item.getPosition().y + 120));
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
	
	public void tick()
	{
		minute +=1;
		if(minute == 10)
		{
			minute = 0;
			tenthMinute +=1;
			if(tenthMinute == 6)
			{
				tenthMinute = 0;
				hour += 1;
				if(hour == 24)
				{
					hour = 00;
				}
			}
		}
	}
	
	public void setTime(int minute, int hour)
	{
		this.minute = minute;
		this.hour = hour;
		this.tenthMinute = 0;
	}
	
	public int getHour()
	{
		return hour;
	}
	
	public int getTenthMinute()
	{
		return tenthMinute;
	}
	
	public int getMinute()
	{
		return minute;
	}
	
	public String getTime()
	{
		return hour + ":" + tenthMinute + "" + minute;
	}
	
	public void resetTime()
	{
		minute = 0;
		tenthMinute = 0;
		hour = 0;
	}

	public ArrayList<Act> getActsHappiningAtN(String time) {
		ArrayList<Act> happens = new ArrayList<Act>();
		for (Act item : dataA)
		{
			System.out.println("time start : " + Double.parseDouble(item.getStartTime().replace(":", ".")) + "\ntime end: " + Double.parseDouble(item.getEndTime().replace(":", ".")) + "\nTime now: " +  Double.parseDouble(getTime().replace(":", ".")));
			if (Double.parseDouble(item.getStartTime().replace(":", ".")) <= Double.parseDouble(getTime().replace(":", ".")) && Double.parseDouble(item.getEndTime().replace(":", ".")) >= Double.parseDouble(getTime().replace(":", ".")))
				happens.add(item);
		}
		return happens;
	}

	public ArrayList<Stage> getStagesWithActsOnTime(String time) {
		ArrayList<Stage> happens = new ArrayList<Stage>();
		for (Act item : dataA)
		{
			System.out.println("time start : " + Double.parseDouble(item.getStartTime().replace(":", ".")) + "\ntime end: " + Double.parseDouble(item.getEndTime().replace(":", ".")) + "\nTime now: " +  Double.parseDouble(getTime().replace(":", ".")));
			if (Double.parseDouble(item.getStartTime().replace(":", ".")) <= Double.parseDouble(getTime().replace(":", ".")) && Double.parseDouble(item.getEndTime().replace(":", ".")) >= Double.parseDouble(getTime().replace(":", ".")))
			{
				for (Stage link : dataS)
				{
					if (link.getName().equals(item.getStage()))
					{
						happens.add(link);
					}
				}
			}
		}
		
		return happens;
	}

	public String getTimePlusMinutes(int i) {
		int min = minute;
		int hr = hour;
		int tentmin = tenthMinute;
		minute +=i;
		if(minute == 10)
		{
			minute = 0;
			tenthMinute +=1;
			if(tenthMinute == 6)
			{
				tenthMinute = 0;
				hour += 1;
				if(hour == 24)
				{
					hour = 00;
				}
			}
		}
		String nya =  hour + ":" + tenthMinute + "" + minute ;
		minute = min;
		hour = hr;
		tenthMinute = tentmin;
		return nya;
	}
	
	public Point getLocationExit()
	{
		Point temp = new Point(10, 10);            //TODO
		return temp;
	}
	
	public void setLocationExit(Point p)
	{
		exit = p;
	}

	public ArrayList<Act> getActsFromStageAtTime(String name, String time) {
		ArrayList<Act> acts = new ArrayList<Act>();
		for (Act item : getActsHappiningAtN(time))
		{
			if (item.getStage().equals(name))
				acts.add(item);
		}
		return acts;
	}
	
	public int getTotalCurrentPopularity()
	{
		int haslel = 0;
		for (Act act : getActsHappiningAtN(getTime()))
		{
			haslel += act.getPopularity();
		}
		return haslel;
	}
	
}
