package Simulator;
import Agenda.AgendaData;;

/**
 * Write a description of class MainWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Application
{

private MainWindow MW;

protected Application() {

}

public static Application create() {
    return new Application();
}

public void init(AgendaData data) {
    MW = new MainWindow(data);
}

public void tick() {
 MW.tick(false);
}

public void shutDown() {

}

public boolean isShowing() {
	return MW.isShowing();
}

public void setVisible(boolean b) {
	MW.setVisible(b);
	
}

}
