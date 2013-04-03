package Agenda;
import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class DemoApplet extends JApplet
{
    JFrame frame;

    public void init()
    {
        frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());

        JTabbedPane tabPane = new JTabbedPane();
        frame.getContentPane().add(tabPane, BorderLayout.CENTER);

        tabPane.add("DateTimePicker",new TestDateTimePicker());
        frame.pack();
    }

    public void start()
    {
        frame.show();
    }

    public void stop()
    {
        frame.hide();
    }

    public static void main(String args[])
    {
        DemoApplet demo = new DemoApplet();
        demo.init();
        demo.start();
    }
}
