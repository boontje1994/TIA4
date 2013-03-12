package Simulator;

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{

    public static boolean shutdownrequest = false;

    public static void main(String[] args) {

        Application app = Application.create();
        app.init();
        while(!shutdownrequest) {
                app.tick();
        }
        app.shutDown();
            
        System.exit(0);

    }

}
