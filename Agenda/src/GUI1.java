import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GUI1
{
	private JFrame frame;
	private JPanel pane;
	private JPanel buttons1;
	private JPanel buttons2;
	private JPanel top;
	private JPanel bottom;
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
	//private Simulator s;

	public static void main(String args[])
	{
		new GUI1();
	}

	public GUI1()
	{
		makeFrame();
		frame2 = new inputFrame();
		frame2.getGui(this);
	}

	public void makeFrame()
	{
		frame = new JFrame("Agenda");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setResizable(false);

		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		pane = new JPanel(new BorderLayout(25, 25));
		frame.add(pane);	
		
		JPanel buttons1a = new JPanel(new BorderLayout(25, 25));
		buttons1 = new JPanel(new GridLayout(3, 1, 25, 25));	
		buttons1a.add(buttons1, BorderLayout.EAST);
		
		JPanel buttons2a = new JPanel(new BorderLayout(25, 25));
		buttons2 = new JPanel(new GridLayout(3, 1, 25, 25));
		buttons2a.add(buttons2, BorderLayout.WEST);
				
		JPanel tablea = new JPanel(new BorderLayout(25, 25));
		table = new JTable(12, 5);
		JScrollPane scrollpane = new JScrollPane(table);
		table.setValueAt("Artiest", 0, 0);
		table.setValueAt("Populariteit", 0, 1);
		table.setValueAt("Podium", 0, 2);
		table.setValueAt("Start Tijd", 0, 3);
		table.setValueAt("Eind Tijd", 0, 4);
		// table.setEnabled(false);
		table.setGridColor(Color.LIGHT_GRAY);
		tablea.add(scrollpane, BorderLayout.CENTER);
		tablea.add(table, BorderLayout.CENTER);
		
		JPanel top = new JPanel(new BorderLayout(25, 25));
		
		JPanel bottom = new JPanel(new BorderLayout(25, 25));

		// button1 = new JButton("Set Data");
		// buttons1.add(button1);
		// button1.addActionListener(new ActionListener()
		// {
		// public void actionPerformed(ActionEvent e)
		// {
		// setData();
		// }
		// });
		
		button2 = new JButton("Simulator");
		buttons2.add(button2);
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//s = new Simulator();
			}

		});

		button3 = new JButton("Verwijder alles");
		buttons2.add(button3);
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
				artist = pop.showInputDialog(null, "Artiest");
				popu = pop.showInputDialog(null, "Populariteit");
				podium = pop.showInputDialog(null, "Podium");
				startTime = pop.showInputDialog(null, "Start Tijd:");
				endTime =pop.showInputDialog(null, "Eind Tijd: ");
				table.setValueAt(artist,index,0);
				table.setValueAt(popu,index,1);
				table.setValueAt(podium,index,2);
				table.setValueAt(startTime,index,3);
				table.setValueAt(endTime, index, 4);
				frame2.setType(false);
				frame2.Visible(true);
				index++;
				// pop.showConfirmDialog(null,
				// "123","Test",JOptionPane.YES_NO_CANCEL_OPTION );
			}
		});

		button5 = new JButton("Verwijder Data");
		buttons1.add(button5);
		button5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (table.getSelectedRow() != 0)
				{
					removeRow(table.getSelectedRow());
				}

			}
		});

		button6 = new JButton("Wijzig Data");
		buttons1.add(button6);
		button6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// artist = pop.showInputDialog(null, "Artiest");
				// popu = pop.showInputDialog(null, "Populariteit");
				// podium = pop.showInputDialog(null, "Podium");
				// startTime = pop.showInputDialog(null, "Start Tijd:");
				// endTime =pop.showInputDialog(null, "Eind Tijd: ");
				frame2.setType(true);
				frame2.Visible(true);
				// artist = frame2.getArtist();
				// popu = frame2.getPopularity();
				// podium = frame2.getPodium();
				// startTime = frame2.getStartTime();
				// endTime = frame2.getEndTime();
			}
		});

		// test = new JComboBox();
		// test.setEditable(true);
		// test.addItem("Paul");
		// test.addItem("Iemand");
		// test.setSelectedItem("");
		// pane.add(test,BorderLayout.SOUTH);
		// test.addActionListener(new ActionListener()
		// {
		// public void actionPerformed(ActionEvent e)
		// {
		// System.out.println(test.getSelectedItem());
		// }
		// });
		
		pane.add(buttons1a, BorderLayout.WEST);
		pane.add(buttons2a, BorderLayout.EAST);
		pane.add(table, BorderLayout.CENTER);
		pane.add(bottom, BorderLayout.NORTH);
		pane.add(top, BorderLayout.SOUTH);

		pop = new JOptionPane();
		frame.setLocationRelativeTo(null);
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

	public int getSRow()
	{
		return table.getSelectedRow();
	}

	public void getData()
	{
		int index1 = 0;
		int index2 = 1;
		while (index1 < 5)
		{
			while (index2 < 10)
			{
				if (table.getValueAt(index2, index1) != null)
				{
					System.out.println(table.getValueAt(index2, index1)
							+ " Row: " + index2 + " Column: " + index1);
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
		while (index1 < 5)
		{
			while (index2 < 10)
			{
				table.setValueAt("Hello", index2, index1);
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
		while (index1 < 5)
		{
			while (index2 < 10)
			{
				table.setValueAt(null, index2, index1);
				index2++;
			}
			index1++;
			index2 = 1;
		}
	}

	public void removeRow(int index2)
	{
		int index1 = 0;
		while (index1 < 5)
		{
			table.setValueAt(null, index2, index1);
			index1++;
		}
	}

	public void setRow(int index2)
	{
		table.setValueAt(artist, index2, 0);
		table.setValueAt(popu, index2, 1);
		table.setValueAt(podium, index2, 2);
		table.setValueAt(startTime, index2, 3);
		table.setValueAt(endTime, index2, 4);
	}

	public void setRow2()
	{
		table.setValueAt(artist, index, 0);
		table.setValueAt(popu, index, 1);
		table.setValueAt(podium, index, 2);
		table.setValueAt(startTime, index, 3);
		table.setValueAt(endTime, index, 4);
	}
}
