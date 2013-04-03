package Simulator;
import java.util.*;

import javax.swing.*;

import sun.misc.Compare;

import Agenda.AgendaData;
import Agenda.Stage;

import java.awt.*;
import java.awt.geom.Point2D;

public class AI {

    private ArrayList<Visitor> visitor = new ArrayList <Visitor>();
    
    private static int x;
    private static int y;
    private static ArrayList<ArrayList<Point>> paths;
    private AgendaData data;
    
    public AI(AgendaData data) {  
    	this.data = data;
    }

    public Visitor makeVis()
    {
        double direction = Math.random() * 2 * Math.PI;
        
        Random r = new Random();
        int imageType = r.nextInt(2); 
        
        return new Visitor(new Point(data.getLocationExit().x-5, data.getLocationExit().y),direction,imageType);
    }

    
    
    public Stage getDestination()
    {
    	ArrayList<Stage> nya = data.getStagesWithActsOnTime(data.getTimePlusMinutes(0));
    	ArrayList<String> nyan = new ArrayList<String>();
    	if (nya.size() < 1)
    		return null;
    	else
    	{
    		for (Stage item : nya)
    		{
    			int i =0;
    			while (i<data.getActsFromStageAtTime(item.getName(), data.getTime()).get(0).getPopularity())
    			{
    				nyan.add(item.getName());
    				i++;
    			}
    		}
    		Stage lol = null;
    		if (nyan.size() > 0)//this should never EVER be less than 1
    		{
	        	String name = nyan.get(randomWithRange(0, nyan.size()-1));
	        	for (Stage item :data.getStages())
	        	{
	        		if (item.getName().equals(name))
	        		{
	        			lol = item;
	        			break;
	        		}
	        	}
	        	return lol;
    		}
    		else
    			return null;
    	}
    	
    }
    public void update()
    {
    	if (data.getStagesWithActsOnTime(data.getTimePlusMinutes(0)).size() > 0)
    	{
    		if (visitor.size() < data.getTotalCurrentPopularity()*20)
    		{
    			visitor.add(makeVis());
    		}
    	}
    		 
    	//System.out.println("amount of visitors is " + visitor.size());
    	Iterator it = visitor.iterator();
    	while (it.hasNext())
    	{
    		Visitor henk = (Visitor) it.next();
    		if (henk.getLocation().x/10 == data.getLocationExit().x/10 && henk.getLocation().y/10 == data.getLocationExit().y/10 && henk.isAlive())
    			it.remove();
        	if (henk.locationReached())// && data.getStages().size() > 0)
        	{
        		Stage dest = getDestination();
        		Point pos;
        		if (dest == null)
        		{
        			pos = data.getLocationExit();
        			henk.setPath(givePath(henk.getLocation(), pos), "NaN");
        		}
        		else
        		{
        			pos = dest.getPos();
        			henk.setPath(givePath(henk.getLocation(), pos), data.getActsFromStageAtTime(dest.getName(), data.getTime()).get(0).getEndTime());
        		}
        			
        	}
        	henk.step(data.getTime());
        }
    }
    
    public AI(AgendaData data, int x, int y) {
    	this.data = data;
        this.x = x;
        this.y = y;
    }
    
    public ArrayList<Point> givePath(Point loc, Point stage)
    {
    	ArrayList<Point> points = data.getCrossroadsWithin(loc, stage);
    	points.add(loc);
    	if (!stage.equals(data.getLocationExit()))
    		points.add(new Point(stage.x + (randomWithRange(0, 48)*10), stage.y + 270 + randomWithRange(0, 2)*10));
    	else
    		points.add(stage);
    	leftTopComparator leftTop = new leftTopComparator();
    	rightTopComparator rightTop = new rightTopComparator();
    	leftBottomComparator leftBot = new leftBottomComparator();
    	rightBottomComparator rightBot = new rightBottomComparator();
    	//Visitor visit = new Visitor();
    	
    	if(leftTop.compare(loc, stage) == 0)
    	{
    		// Visitor must go to leftTop of the field
    		Collections.sort(points,leftTop);
    	}
    	
    	else if(rightTop.compare(loc, stage) == 0)
    	{
    		// Visitor must go to rightTop of the field
    		Collections.sort(points, rightTop);
    	}
    	
    	else if(leftBot.compare(loc, stage) == 0)
    	{
    		//Visitor must go to leftBottom of the field
    		Collections.sort(points, leftBot);
    	}
    	
    	else if(rightBot.compare(loc, stage) == 0)
    	{
    		// Visitor must go to rightBottom of the field
    		Collections.sort(points, rightBot);
    	}
    	return points;
    	
    }
    
    int randomWithRange(int min, int max)
    {
       int range = (max - min) + 1;     
       return (int)(Math.random() * range) + min;
    }
    
    //Tekent de visitors op de map.
    public void drawVisitor(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for(Visitor v : visitor) {
            v.drawImage(g2);
        }          
    }
    
    public String getData()
    {
    	String snap = "";
    	for (Visitor item: visitor)
    	{
    		snap += "-+-" + item.getAllData();
    	}
    	return snap;
    }
    
    public void setFromData(String data)
    {
    	for (String stream : data.split("-+-"))
    	{
    		String[] items = stream.split("=+=");
    		visitor.clear();
    		if (items.length > 0)
    		{
    			System.out.println(items.length);
    			System.out.println("adding visitor from string " + stream);
    			//visitor.add(new Visitor(new Point2D.Double(Double.parseDouble(items[1].split(":")[0]), Double.parseDouble(items[1].split(":")[1])),Double.parseDouble(items[2]),1));
    		}
    	}
    }
    

	public void reset() {
		visitor = new ArrayList <Visitor>();
	}
	
    
    
    
}


class leftTopComparator implements Comparator<Point>
{
    public int compare(Point p1, Point p2)
    {
    	// p1 = location
    	// p2 = stage
		if  (p2.getX() < p1.getX() && p2.getY() > p1.getY())
			return 0;
		else
			return 1;
    }
}
class leftBottomComparator implements Comparator<Point>
{
    public int compare(Point p1, Point p2)
    {
		if  (p2.getX() < p1.getX() && p2.getY() < p1.getY())
			return 0;
		else
			return 1;
    }
}
class rightTopComparator implements Comparator<Point>
{
    public int compare(Point p1, Point p2)
    {
		if  (p2.getX() > p1.getX() && p2.getY() > p1.getY())
			return 0;
		else
			return 1;
    }
}
class rightBottomComparator implements Comparator<Point>
{
    public int compare(Point p1, Point p2)
    {
		if  (p2.getX() < p1.getX() && p2.getY() < p1.getY())
			return 0;
		else
			return 1;
    }
}
