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
