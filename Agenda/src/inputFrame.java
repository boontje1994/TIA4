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
	private JComboBox artist3;
	private JComboBox popularity3;
	private JComboBox podium3;
	private JComboBox startTime3;
	private JComboBox endTime3;
//	private JPanel pane2;
//	private JTextField artist2;
//	private JTextField popularity2;
//	private JTextField podium2;
//	private JTextField startTime2;
//	private JTextField endTime2;
//	public JButton button2;
	private boolean type;
	
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
		
//		artist1 = new JTextField(10);
		artist3 = new JComboBox();
		artist3.setSelectedItem("");
		artist3.setEditable(true);
		JLabel label1 = new JLabel("Artiest");
		pane.add(label1);
		pane.add(artist3);
		
		JLabel label2 = new JLabel("Populariteit");
//		popularity1 = new JTextField(10);
		popularity3 = new JComboBox();
		popularity3.setSelectedItem("");
		popularity3.setEditable(true);
		pane.add(label2);
		pane.add(popularity3);
		
		JLabel label3 = new JLabel("Podium");
//		podium1 = new JTextField(10);
		podium3 = new JComboBox();
		podium3.setSelectedItem("");
		podium3.setEditable(true);
		pane.add(label3);
		pane.add(podium3);
		
		JLabel label4 = new JLabel("Start Tijd");
//		startTime1 = new JTextField(10);
		startTime3 = new JComboBox();
		startTime3.setSelectedItem("");
		startTime3.setEditable(true);
		pane.add(label4);
		pane.add(startTime3);
		
		JLabel label5 = new JLabel("Eind Tijd");
//		endTime1 = new JTextField(10);
		endTime3 = new JComboBox();
		endTime3.setSelectedItem("");
		endTime3.setEditable(true);
		pane.add(label5);
		pane.add(endTime3);
		
		button = new JButton("Save");
		pane.add(button);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
//				setArtist(artist1.getText());
//				setPopularity(popularity1.getText());
//				setPodium(podium1.getText());
//				setStartTime(startTime1.getText());
//				setEndTime(endTime1.getText());
				setArtist(artist3.getSelectedItem().toString());
				setPopularity(popularity3.getSelectedItem().toString());
				setPodium(podium3.getSelectedItem().toString());
				setStartTime(startTime3.getSelectedItem().toString());
				setEndTime(endTime3.getSelectedItem().toString());
				frame.setVisible(false);
				gui.setInfo();
				if(type ==true)
				{
					gui.setRow(gui.table.getSelectedRow());
				}
				if(type==false)
				{
					gui.setRow2();
				}
				clearFields2();
//				clearFields();
				
			}
		});
		
	}
	
//	public void makeFrame2()
//	{
//		frame2 = new JFrame();
//		frame2.setDefaultCloseOperation(frame2.EXIT_ON_CLOSE);
//		frame2.setSize(300,300);
//		
//		pane2 = new JPanel();
//		frame.add(pane2);
//		pane2.setLayout(new GridLayout(0,2));
//		
//		JLabel label6 = new JLabel("Artiest");
//		artist2 = new JTextField(10);
//		pane2.add(label6);
//		pane2.add(artist2);
//		
//		JLabel label7 = new JLabel("Populariteit");
//		popularity2 = new JTextField(10);
//		pane2.add(label7);
//		pane2.add(popularity2);
//		
//		JLabel label8 = new JLabel("Podium");
//		podium2 = new JTextField(10);
//		pane2.add(label8);
//		pane2.add(podium2);
//		
//		JLabel label9 = new JLabel("Start Tijd");
//		startTime2 = new JTextField(10);
//		pane2.add(label9);
//		pane2.add(startTime2);
//		
//		JLabel label10 = new JLabel("Eind Tijd");
//		endTime2 = new JTextField(10);
//		pane2.add(label10);
//		pane2.add(endTime2);
//		
//		button2 = new JButton("Save");
//		pane2.add(button2);
//		button2.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				setArtist(artist2.getText());
//				setPopularity(popularity2.getText());
//				setPodium(podium2.getText());
//				setStartTime(startTime2.getText());
//				setEndTime(endTime2.getText());
//				frame2.setVisible(false);
//				gui.setInfo();
//				if ( type ==true)
//				{
//					gui.setRow2();
//				}
//				if(type == false)
//				{
//					gui.setRow(gui.getIndex());
//				}
//				clearFields2();
//			}
//		});
//	}

	public String getEndTime() {
		return endTime;
	}
	
	public void getGui(GUI1 gui)
	{
		this.gui = gui;
	}
	
	public void setType(boolean type)
	{
		this.type =type;
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
		int i = 0;
		while (i < artist3.getItemCount())
		{
			if (artist3.getSelectedItem() != artist3.getItemAt(i))
				artist3.addItem(artist3.getSelectedItem());
		}
		
		i = 0;
		while (i < popularity3.getItemCount())
		{
			if (popularity3.getSelectedItem() != popularity3.getItemAt(i))
				popularity3.addItem(popularity3.getSelectedItem());
		}
		
		i = 0;
		while (i < podium3.getItemCount())
		{
			if (podium3.getSelectedItem() != podium3.getItemAt(i))
				podium3.addItem(podium3.getSelectedItem());
		}
		
		i = 0;
		while (i < startTime3.getItemCount())
		{
			if (startTime3.getSelectedItem() != startTime3.getItemAt(i))
				startTime3.addItem(startTime3.getSelectedItem());
		}
		
		i = 0;
		while (i < endTime3.getItemCount())
		{
			if (endTime3.getSelectedItem() != endTime3.getItemAt(i))
				endTime3.addItem(endTime3.getSelectedItem());
		}
		
		artist3.setSelectedItem("");
		popularity3.setSelectedItem("");
		podium3.setSelectedItem("");
		startTime3.setSelectedItem("");
		endTime3.setSelectedItem("");
	}
	
//	public void clearFields2()
//	{
//		artist2.setText(null);
//		popularity2.setText(null);
//		podium2.setText(null);
//		startTime2.setText(null);
//		endTime2.setText(null);
//	}
	

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
