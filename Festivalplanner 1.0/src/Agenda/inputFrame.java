package Agenda;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class inputFrame
{
	private JFrame frame;
	private JFrame frame2;
	private String artist;
	private String popularity;
	private String podium;
	private String startTime;
	private String endTime;
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
	private AgendaData data;

	private boolean type;

	private GUI1 gui;

	public inputFrame(AgendaData data)
	{
		this.data = data;
		makeFrame();
	}

	public void makeFrame()
	{
		frame = new JFrame("Editor");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 300);
		
		Dimension buttonSize = new Dimension(230, 50);
		
		JPanel mainPanel = new JPanel(new BorderLayout(25, 25));	
		
		JPanel top = new JPanel(new BorderLayout(25, 25));
		JPanel bottom = new JPanel(new GridLayout(1, 2, 5, 5));
		JPanel center = new JPanel(new GridLayout(5, 1, 5, 5));
		
		artist3 = new JComboBox();
		artist3.setSelectedItem("");
		artist3.setEditable(true);
		JLabel label1 = new JLabel("Artiest");
		label1.setFont(new Font("Consolas", Font.BOLD, 11));
		center.add(label1);
		center.add(artist3);

		JLabel label2 = new JLabel("Populariteit");
		popularity3 = new JComboBox();
		popularity3.setSelectedItem("");
		popularity3.setEditable(true);
		label2.setFont(new Font("Consolas", Font.BOLD, 11));
		center.add(label2);
		center.add(popularity3);

		JLabel label3 = new JLabel("Podium");
		podium3 = new JComboBox();
		podium3.setSelectedItem("");
		podium3.setEditable(true);
		label3.setFont(new Font("Consolas", Font.BOLD, 11));
		center.add(label3);
		center.add(podium3);

		JLabel label4 = new JLabel("Start Tijd");
		startTime3 = new JComboBox();
		startTime3.setSelectedItem("");
		startTime3.setEditable(true);
		label4.setFont(new Font("Consolas", Font.BOLD, 11));
		center.add(label4);
		center.add(startTime3);

		JLabel label5 = new JLabel("Eind Tijd");
		endTime3 = new JComboBox();
		endTime3.setSelectedItem("");
		endTime3.setEditable(true);
		label5.setFont(new Font("Consolas", Font.BOLD, 11));
		center.add(label5);
		center.add(endTime3);

		ImageIcon savea = new ImageIcon("images/btn_opslaan.png");		
		Image imgb = savea.getImage();
		Image newimgb = imgb.getScaledInstance(115, 25, java.awt.Image.SCALE_SMOOTH);
		savea = new ImageIcon(newimgb);
		JButton save = new JButton(savea);
		save.setContentAreaFilled(false);
		save.setFocusPainted(false);
		save.setBorderPainted(false);
		bottom.add(save);
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (artist3.getSelectedItem() == null
						|| popularity3.getSelectedItem() == null
						|| podium3.getSelectedItem() == null
						|| startTime3.getSelectedItem() == null
						|| endTime3.getSelectedItem() == null)
				{
					//JOptionPane error = new JOptionPane();
					//error.showConfirmDialog(null, "Een of meer velden is niet ingevuld", "Error", JOptionPane.ERROR_MESSAGE);
					JOptionPane.showConfirmDialog(null, "Een of meer velden is niet ingevuld", "Error", JOptionPane.ERROR_MESSAGE);
				} else
				{
					if (!Pattern.matches("[a-zA-Z]+", popularity3.getSelectedItem().toString()))
					{
						setArtist(artist3.getSelectedItem().toString());
						setPopularity(popularity3.getSelectedItem().toString());
						setPodium(podium3.getSelectedItem().toString());
						setStartTime(startTime3.getSelectedItem().toString());
						setEndTime(endTime3.getSelectedItem().toString());
					}
					frame.setVisible(false);
					gui.setInfo();
					if (type == true)
					{
						gui.setRow(gui.table.getSelectedRow());
					}
					if (type == false)
					{
						gui.setRow2();
						gui.setIndex(gui.getIndex() + 1);
					}
					clearFields2();
				}
			}
		});
		
		ImageIcon cancela = new ImageIcon("images/btn_annuleren.png");		
		Image img = cancela.getImage();
		Image newimg = img.getScaledInstance(115, 25, java.awt.Image.SCALE_SMOOTH);
		cancela = new ImageIcon(newimg);
		JButton cancel = new JButton(cancela);
		cancel.setContentAreaFilled(false);
		cancel.setFocusPainted(false);
		cancel.setBorderPainted(false);
		bottom.add(cancel);
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearFields3();
				frame.setVisible(false);
			}
		});
		
		
		mainPanel.add(top, BorderLayout.NORTH);
		mainPanel.add(center, BorderLayout.CENTER);
		mainPanel.add(bottom, BorderLayout.SOUTH);
		
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.pack();

	}

	public String getEndTime()
	{
		return endTime;
	}

	public void getGui(GUI1 gui)
	{
		this.gui = gui;
	}

	public void setType(boolean type)
	{
		this.type = type;
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
		boolean numberOnly = false;
		if (artist3.getItemCount() > 0)
		{
			if (!Pattern.matches("[a-zA-Z]+", popularity3.getSelectedItem().toString()))
			{
				System.out.println(popularity3.getSelectedItem().toString());
				int i = 0;
				boolean check = true;
				while (i < artist3.getItemCount())
				{
					if (artist3.getSelectedItem().equals(artist3.getItemAt(i)))
					{
						check = false;
						break;
					}
					if (check)
						artist3.addItem(artist3.getSelectedItem());
					i++;
				}
	
				i = 0;
				check = true;
				while (i < popularity3.getItemCount())
				{
					if (popularity3.getSelectedItem().equals(
							popularity3.getItemAt(i)))
					{
						check = false;
						break;
					}
					if (check)
						popularity3.addItem(popularity3.getSelectedItem());
					i++;
				}
	
				i = 0;
				check = true;
				while (i < podium3.getItemCount())
				{
					if (podium3.getSelectedItem().equals(podium3.getItemAt(i)))
					{
						check = false;
						break;
					}
					if (check)
					{
						podium3.addItem(podium3.getSelectedItem());
						data.addStage(new Stage(podium3.getSelectedItem().toString()));
					}
					i++;
				}
	
				i = 0;
				check = true;
				while (i < startTime3.getItemCount())
				{
					if (startTime3.getSelectedItem().equals(startTime3.getItemAt(i)))
					{
						check = false;
						break;
					}
					if (check)
						startTime3.addItem(startTime3.getSelectedItem());
					i++;
				}
	
				i = 0;
				check = true;
				while (i < endTime3.getItemCount())
				{
					if (endTime3.getSelectedItem().equals(endTime3.getItemAt(i)))
					{
						check = false;
						break;
					}
					if (check)
						endTime3.addItem(endTime3.getSelectedItem());
					i++;
					
				numberOnly = true;
				}
			}
		} else
		{
			if (!Pattern.matches("[a-zA-Z]+", popularity3.getSelectedItem().toString()))
			{
				popularity3.addItem(popularity3.getSelectedItem());
				podium3.addItem(podium3.getSelectedItem());
				startTime3.addItem(startTime3.getSelectedItem());
				artist3.addItem(artist3.getSelectedItem());
				endTime3.addItem(endTime3.getSelectedItem());
				numberOnly = true;
			}
		}
		if (numberOnly)
		{
			data.addAct(new Act(artist3.getSelectedItem().toString(), Integer.parseInt(popularity3.getSelectedItem().toString()), podium3.getSelectedItem().toString(), startTime3.getSelectedItem().toString(), endTime3.getSelectedItem().toString()));
			data.addStage(new Stage((String) podium3.getSelectedItem()));
			//System.out.println(podium3.getSelectedItem().toString();
			artist3.setSelectedItem(null);
			popularity3.setSelectedItem(null);
			podium3.setSelectedItem(null);
			startTime3.setSelectedItem(null);
			endTime3.setSelectedItem(null);
		}
		else
		{
			//TODO add exception to notify user when NaN occurs
			if (artist3.getSelectedItem() != null && popularity3.getSelectedItem() != null && podium3.getSelectedItem() != null && startTime3.getSelectedItem() != null && endTime3.getSelectedItem() != null)
			{
				data.addAct(new Act(artist3.getSelectedItem().toString(), Integer.parseInt(popularity3.getSelectedItem().toString()), podium3.getSelectedItem().toString(), startTime3.getSelectedItem().toString(), endTime3.getSelectedItem().toString()));
				data.addStage(new Stage((String) podium3.getSelectedItem()));
				artist3.setSelectedItem(null);
				popularity3.setSelectedItem(null);
				podium3.setSelectedItem(null);
				startTime3.setSelectedItem(null);
				endTime3.setSelectedItem(null);
			}
			else
			{
				System.out.println(artist3.getSelectedItem());
				System.out.println(popularity3.getSelectedItem());
				System.out.println(podium3.getSelectedItem());
				System.out.println(startTime3.getSelectedItem());
				System.out.println(endTime3.getSelectedItem());
			}
		}
			
	}

	public void clearFields3()
	{
		artist3.setSelectedItem("");
		popularity3.setSelectedItem("");
		podium3.setSelectedItem("");
		startTime3.setSelectedItem("");
		endTime3.setSelectedItem("");
	}
	
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getPodium()
	{
		return podium;
	}

	public void setPodium(String podium)
	{
		this.podium = podium;
	}

	public String getPopularity()
	{
		return popularity;
	}

	public void setPopularity(String popularity)
	{
		this.popularity = popularity;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
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
