import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class inputFrame 
{
	private JFrame frame;
	private JFrame frame2;
	private JPanel pane;
	private String artist;
	private String popularity;
	private String podium;
	private String startTime;
	private String endTime;
	public JButton button;
	private JTextField artist1;
	private JTextField popularity1;
	private JTextField podium1;
	private JTextField startTime1;
	private JTextField endTime1;
	private JPanel pane2;
	private JTextField artist2;
	private JTextField popularity2;
	private JTextField podium2;
	private JTextField startTime2;
	private JTextField endTime2;
	public JButton button2;
	
	private GUI1 gui;
	
	public static void main(String args[])
	{
		new inputFrame();
	}
	
	public inputFrame()
	{
		makeFrame();
//		makeFrame2();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Editor");
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		
		pane = new JPanel();
		frame.add(pane);
		pane.setLayout(new GridLayout(0,2));
		
		artist1 = new JTextField(10);
		JLabel label1 = new JLabel("Artiest");
		pane.add(label1);
		pane.add(artist1);
		
		JLabel label2 = new JLabel("Populariteit");
		popularity1 = new JTextField(10);
		pane.add(label2);
		pane.add(popularity1);
		
		JLabel label3 = new JLabel("Podium");
		podium1 = new JTextField(10);
		pane.add(label3);
		pane.add(podium1);
		
		JLabel label4 = new JLabel("Start Tijd");
		startTime1 = new JTextField(10);
		pane.add(label4);
		pane.add(startTime1);
		
		JLabel label5 = new JLabel("Eind Tijd");
		endTime1 = new JTextField(10);
		pane.add(label5);
		pane.add(endTime1);
		
		button = new JButton("Save");
		pane.add(button);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setArtist(artist1.getText());
				setPopularity(popularity1.getText());
				setPodium(podium1.getText());
				setStartTime(startTime1.getText());
				setEndTime(endTime1.getText());
				frame.setVisible(false);
				gui.setInfo();
				gui.setRow(gui.table.getSelectedRow());
				clearFields();
				
			}
		});
		
	}
	
	public void makeFrame2()
	{
		frame2 = new JFrame();
		frame2.setDefaultCloseOperation(frame2.EXIT_ON_CLOSE);
		frame2.setSize(300,300);
		
		pane2 = new JPanel();
		frame.add(pane2);
		pane2.setLayout(new GridLayout(0,2));
		
		JLabel label6 = new JLabel("Artiest");
		artist2 = new JTextField(10);
		pane2.add(label6);
		pane2.add(artist2);
		
		JLabel label7 = new JLabel("Populariteit");
		popularity2 = new JTextField(10);
		pane2.add(label7);
		pane2.add(popularity2);
		
		JLabel label8 = new JLabel("Podium");
		podium2 = new JTextField(10);
		pane2.add(label8);
		pane2.add(podium2);
		
		JLabel label9 = new JLabel("Start Tijd");
		startTime2 = new JTextField(10);
		pane2.add(label9);
		pane2.add(startTime2);
		
		JLabel label10 = new JLabel("Eind Tijd");
		endTime2 = new JTextField(10);
		pane2.add(label10);
		pane2.add(endTime2);
		
		button2 = new JButton("Save");
		pane2.add(button2);
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setArtist(artist2.getText());
				setPopularity(popularity2.getText());
				setPodium(podium2.getText());
				setStartTime(startTime2.getText());
				setEndTime(endTime2.getText());
				frame2.setVisible(false);
				gui.setInfo();
				gui.setRow2();
				clearFields2();
			}
		});
	}

	public String getEndTime() {
		return endTime;
	}
	
	public void getGui(GUI1 gui)
	{
		this.gui = gui;
	}
	
	public void clearFields()
	{
		artist1.setText(null);
		popularity1.setText(null);
		podium1.setText(null);
		startTime1.setText(null);
		endTime1.setText(null);
	}
	
	public void clearFields2()
	{
		artist2.setText(null);
		popularity2.setText(null);
		podium2.setText(null);
		startTime2.setText(null);
		endTime2.setText(null);
	}
	

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getPodium() {
		return podium;
	}

	public void setPodium(String podium) {
		this.podium = podium;
	}

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public void Visible(boolean a)
	{
		frame.setVisible(a);
	}
	
	public void setVisible(boolean a)
	{
		frame2.setVisible(a);
	}
	
}
