package Simulator;

/**
 * Write a description of class MainWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Application
{

protected Application() {

}

public static Application create() {
    return new Application();
}

public void init() {
    new MainWindow();
}

public void tick() {
 
}

public void shutDown() {

}

}
