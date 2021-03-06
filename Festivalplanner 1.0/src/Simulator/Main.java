package Simulator;
import Agenda.AgendaData;
import Agenda.GUI1;
import Agenda.NowPlaying;

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{

    public static boolean shutdownrequest = false;
    
    public static AgendaData data = new AgendaData();

    public static void main(String[] args) {
    	
    	NowPlaying nowPlaying = new NowPlaying(data);
    	GUI1 agenda = new GUI1(data, nowPlaying);
    	//nowPlaying.setGUI(agenda);
    	
    	
        Application app = Application.create();
        app.init(data);
                	
        while(!shutdownrequest) {
        		try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                app.tick();
                nowPlaying.updateNP();
                
                if (!app.isShowing())
                {
                	if (data.isSimVisible())
                	{
                		app.setVisible(true);
                	}
                }
                else
                {
                	if (!data.isSimVisible())
                	{
                		app.setVisible(false);
                	}
                }
                if (!GUI1.isShowing())
                {
                	if (data.isAgendaVisible())
                	{
                		GUI1.setVisible(true);
                	}
                }
        }
        app.shutDown();
            
        System.exit(0);

    }

}
