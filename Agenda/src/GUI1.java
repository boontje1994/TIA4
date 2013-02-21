import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GUI1 
{
	private JFrame frame;
	private JPanel pane;
	private JPanel buttons1;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	public JTable table;
	private JOptionPane pop;
	private String artist;
	private String popu;
	private String podium;
	private String startTime;
	private String endTime;
	private int index = 1;
	private inputFrame frame2;
	private JComboBox test;
	private ArrayList<Stage> stages;
	private String fileName;
	private ArrayList<Act> acts;
	
	public static void main(String args[])
	{
		new GUI1();
	}
	
	public GUI1()
	{
		makeFrame();
		frame2 = new inputFrame();
		frame2.getGui(this);
		stages = new ArrayList<Stage>();
		acts = new ArrayList<Act>();
	}
	
	public void makeFrame()
	{
		frame = new JFrame("Agenda");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setResizable(false);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JMenuBar menu = new JMenuBar();
		JMenu m1 = new JMenu("Bestand");
		JMenuItem mi1 = new JMenuItem("Open Bestand");
		JMenuItem mi2 = new JMenuItem("Opslaan");
		JMenuItem mi3 = new JMenuItem("Opslaan Als");
		JMenuItem mi4 = new JMenuItem("Close");
		mi1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(frame);
			    if(returnVal == JFileChooser.APPROVE_OPTION)
			    {
			    	try
			    	{
			    		fileName = chooser.getSelectedFile().getAbsolutePath();
			    	} catch (Exception e1)
			    	{
			    		System.err.println("File does not exist!");
			    	}
			       readFile();
			    }
//			    if(returnVal == JFileChooser.CANCEL_OPTION)
//			    {
//			    	
//			    }
			}
		});
		mi2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (fileName == null)
				{
					JFileChooser chooser = new JFileChooser();
				    int returnVal = chooser.showOpenDialog(frame);
				    if(returnVal == JFileChooser.APPROVE_OPTION)
				    {
				       fileName = chooser.getSelectedFile().getAbsolutePath();
				    }
//				    if(returnVal == JFileChooser.CANCEL_OPTION)
//				    {
//				    	
//				    }
				}
				writeFile();
			}
		});
		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi4);
		menu.add(m1);
		frame.setJMenuBar(menu);
		pane = new JPanel();
		frame.add(pane);
		pane.setLayout(new BorderLayout());
		
		buttons1 = new JPanel();
		pane.add(buttons1, BorderLayout.WEST);
		buttons1.setLayout(new GridLayout(0,1,10,10));
		
		table = new JTable(20,5);
		pane.add(table, BorderLayout.CENTER);
		table.setValueAt("Artiest",0,0);
		table.setValueAt("Populariteit",0,1);
		table.setValueAt("Podium", 0, 2);
		table.setValueAt("Start Tijd",0,3);
		table.setValueAt("Eind Tijd",0,4);
//		table.setEnabled(false);

		
//		button1 = new JButton("Set Data");
//		buttons1.add(button1);
//		button1.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				setData();
//			}
//		});
		
		button2 = new JButton("Get Data");
		buttons1.add(button2);
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getData();
				
			}
			
		});
		
		button3 = new JButton("Remove All Data");
		buttons1.add(button3);
		button3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				removeData();
				index = 1;
			}
		});
		
		button4 = new JButton("Voeg Data toe");
		buttons1.add(button4);
		button4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
//				artist = pop.showInputDialog(null, "Artiest");
//				popu = pop.showInputDialog(null, "Populariteit");
//				podium = pop.showInputDialog(null, "Podium");
//				startTime = pop.showInputDialog(null, "Start Tijd:");
//				endTime =pop.showInputDialog(null, "Eind Tijd: ");
//				table.setValueAt(artist,index,0);
//				table.setValueAt(popu,index,1);
//				table.setValueAt(podium,index,2);
//				table.setValueAt(startTime,index,3);
//				table.setValueAt(endTime, index, 4);
				frame2.setType(false);
				frame2.Visible(true);
//				index++;
//				pop.showConfirmDialog(null, "123","Test",JOptionPane.YES_NO_CANCEL_OPTION );
			}
		});
		
		button5 = new JButton("Remove Row");
		buttons1.add(button5);
		button5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(table.getSelectedRow() != 0)
				{
					removeRow(table.getSelectedRow());
				}
				
			}
		});
		
		button6 = new JButton("Edit Row");
		buttons1.add(button6);
		button6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
//				artist = pop.showInputDialog(null, "Artiest");
//				popu = pop.showInputDialog(null, "Populariteit");
//				podium = pop.showInputDialog(null, "Podium");
//				startTime = pop.showInputDialog(null, "Start Tijd:");
//				endTime =pop.showInputDialog(null, "Eind Tijd: ");
				frame2.setType(true);
				frame2.Visible(true);
//				artist = frame2.getArtist();
//				popu = frame2.getPopularity();
//				podium = frame2.getPodium();
//				startTime = frame2.getStartTime();
//				endTime = frame2.getEndTime();
			}
		});
		
//		test = new JComboBox();
//		test.setEditable(true);
//		test.addItem("Paul");
//		test.addItem("Iemand");
//		test.setSelectedItem("");
//		pane.add(test,BorderLayout.SOUTH);
//		test.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				System.out.println(test.getSelectedItem());
//			}
//		});
		

		
		
		
		pop = new JOptionPane();
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setInfo()
	{
		artist = frame2.getArtist();
		popu = frame2.getPopularity();
		podium = frame2.getPodium();
		startTime = frame2.getStartTime();
		endTime = frame2.getEndTime();
	}
	
	public void newStage(String name)
	{
		stages.add(new Stage(name));
	}
	
	public void addAct(String artist,int pop,String stage,String startTime,String endTime)
	{
		acts.add(new Act(artist, pop, stage, startTime, endTime));
	}
	
	public ArrayList<Stage> getStages()
	{
		return stages;
	}
	
	public int getStageNumberOfActs(int index)
	{
		return index;
		//return Stages.get(index).getActSize();
	}
	
	public int getSRow()
	{
		return table.getSelectedRow();
	}
	
	public void getData()
	{
		int index1 = 0;
		int index2 = 1;
		while(index1 < 5)
		{
			while(index2 < 10)
			{
				if(table.getValueAt(index2, index1) != null)
				{
					System.out.println(table.getValueAt(index2, index1) + " Row: " + index2 + " Column: " + index1);
				}
				index2++;
			}
			index1++;
			index2 = 1;
		}
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public void setData()
	{
		int index1 = 0;
		int index2 = 0;
		while(index1 < 5)
		{
			while(index2 < 10)
			{
				table.setValueAt("Hello",index2, index1);
				index2++;
			}
			index1++;
			index2 = 0;
			
		}
	}
	
	public void removeData()
	{
		int index1 = 0;
		int index2 = 1;
		while(index1 < 5)
		{
			while(index2 < 10)
			{
				table.setValueAt(null,index2,index1);
				index2++;
			}
			index1++;
			index2 = 1;
		}	
	}
	
	public void removeRow(int index2)
	{
		int index1 = 0;
		while(index1 < 5)
		{
			table.setValueAt(null,index2,index1);
			index1++;
		}
	}
	
	public void setRow(int index2)
	{
		table.setValueAt(artist,index2,0);
		table.setValueAt(popu, index2, 1);
		table.setValueAt(podium,index2,2);
		table.setValueAt(startTime,index2,3);
		table.setValueAt(endTime,index2,4);
	}
	
	public void setRow2()
	{
		table.setValueAt(artist,index,0);
		table.setValueAt(popu,index,1);
		table.setValueAt(podium,index,2);
		table.setValueAt(startTime,index,3);
		table.setValueAt(endTime,index,4);
	}
	
	public void setRowSane(int index2, String artist, String popu, String podium, String start, String end)
	{
		table.setValueAt(artist,index2,0);
		table.setValueAt(popu, index2, 1);
		table.setValueAt(podium,index2,2);
		table.setValueAt(start,index2,3);
		table.setValueAt(end,index2,4);
	}
	
	public void writeFile()
	{
		String dataStream = "AGENDAFILE\nStages;\n";
		for (Stage item : stages)
		{
			dataStream += item.getName() + ";\n";
		}
		dataStream += "Acts;\n";
		for (Act item : acts)
		{
			dataStream += item.getArtist()
						+ ";"
						+ item.getEndTime()
						+ ";"
						+ item.getPopularity()
						+ ";"
						+ item.getStage()
						+ ";"
						+ item.getStartTime()
						+ ";"
						+ "\n";
		}
		IOWrite writer = new IOWrite();
		writer.write(dataStream, fileName);
		System.out.println(dataStream);
	}
	
	public void readFile()
	{
		IOWrite reader = new IOWrite();
		String dataStream = reader.read(fileName);
		if (!dataStream.equals(""))
		{
			removeData();
			stages = new ArrayList<Stage>();
			acts = new ArrayList<Act>();
			String[] dataChunks = dataStream.split("Acts;");
			String actData = dataChunks[0].split("AGENDAFILE")[1].split("Stages;")[1];
			System.out.println(actData);
			for (String item : actData.split("\n"))
			{
				System.out.println("adding stage " + item);
				newStage(item);
			}
			System.out.println(dataChunks[1]);
			for (String item : dataChunks[1].split("\n"))
			{
				if (item.contains(";"))
				{
					String[] actdata = item.split(";");
					System.out.println("adding an act with artist " + actdata[0] + ", an endtime of " + actdata[1] + ", an popularity of " + actdata[2] + ", on the " + actdata[3] + " stage, ending on " + actdata[4]);
					setRowSane(getIndex(), actdata[0], actdata[2], actdata[3], actdata[4], actdata[1]);
					setIndex(getIndex() + 1);
					addAct(actdata[0], Integer.parseInt(actdata[2]), actdata[3], actdata[4], actdata[1]);
				}
			}
		}
		else
		{
			System.out.println("File doesn't match expectations, aborting");
			fileName = null;
		}
	}
}

