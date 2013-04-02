package Agenda;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.lavantech.gui.comp.DateTimePicker;
import com.lavantech.gui.comp.DateUnavailabilityModel;

public class InputDialog implements DateUnavailabilityModel{
	
	private AgendaData agendaData;
	
	private JFrame frame;
	
	private JComboBox comboArtist;
	private JComboBox comboPopularity;
	private JComboBox comboPodium;
	private JComboBox comboStartTime;
	private JComboBox comboEndTime;
	
	private DateTimePicker dateStart, dateEnd;
	private JSlider sliderPopularity;
	
	public static void main(String[] args){
		//new InputDialog();
		
	}
	
	public InputDialog(AgendaData agendaData){
		this.agendaData = agendaData;
		makeFrame();
		
	}
	
	public void makeFrame(){
		frame = new JFrame("Editor");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 300);
		
		JPanel mainPanel = new JPanel(new BorderLayout(25, 25));	
		JPanel topPanel = new JPanel(new BorderLayout(25, 25));
		JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		JPanel centerPanel = new JPanel(new GridLayout(5, 1, 5, 5));
		
		comboArtist = new JComboBox();
		comboArtist.setSelectedItem("");
		comboArtist.setEditable(true);
		JLabel lblArtist = new JLabel("Artiest");
		lblArtist.setFont(new Font("Consolas", Font.BOLD, 11));
		centerPanel.add(lblArtist);
		centerPanel.add(comboArtist);

		JLabel lblPopularity = new JLabel("Populariteit");
		lblPopularity.setFont(new Font("Consolas", Font.BOLD, 11));
		centerPanel.add(lblPopularity);
		sliderPopularity = new JSlider(0,5,0);
		sliderPopularity.setSnapToTicks(true);
		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer( 0 ), new JLabel("0") );
		labelTable.put( new Integer( 1 ), new JLabel("1") );
		labelTable.put( new Integer( 2 ), new JLabel("2") );
		labelTable.put( new Integer( 3 ), new JLabel("3") );
		labelTable.put( new Integer( 4 ), new JLabel("4") );
		labelTable.put( new Integer( 5 ), new JLabel("5") );
		sliderPopularity.setLabelTable( labelTable );
		sliderPopularity.setPaintLabels(true);

		
		centerPanel.add(sliderPopularity);

		JLabel lblPodium = new JLabel("Podium");
		comboPodium = new JComboBox();
		comboPodium.setSelectedItem("");
		comboPodium.setEditable(true);
		lblPodium.setFont(new Font("Consolas", Font.BOLD, 11));
		centerPanel.add(lblPodium);
		centerPanel.add(comboPodium);

		JLabel lblStartTime = new JLabel("Start Tijd");
		GregorianCalendar calStart = new GregorianCalendar();
        dateStart = new DateTimePicker(calStart, "dd-MMM-yyyy HH:mm z");
        centerPanel.add(lblStartTime);
        centerPanel.add(dateStart);
        dateStart.setDisplayTodayButton(false);
        GregorianCalendar limit = (GregorianCalendar)calStart.clone();
        limit.set(Calendar.MONTH, Calendar.DECEMBER);
        limit.set(Calendar.YEAR, calStart.get(Calendar.YEAR));
		limit.set(Calendar.DATE, calStart.getActualMaximum(Calendar.DATE));
		dateStart.setMaxSelectableTime(limit);

		JLabel lblEndTime = new JLabel("Eind Tijd");
		GregorianCalendar calEnd = new GregorianCalendar();
		dateEnd = new DateTimePicker(calEnd, "dd-MMM-yyyy HH:mm:ss z");
        centerPanel.add(lblEndTime);
        centerPanel.add(dateEnd);
        dateEnd.setDisplayTodayButton(false);
        GregorianCalendar limitEnd = (GregorianCalendar)calEnd.clone();
        limit.set(Calendar.MONTH, Calendar.DECEMBER);
        limit.set(Calendar.YEAR, calEnd.get(Calendar.YEAR));
		limit.set(Calendar.DATE, calEnd.getActualMaximum(Calendar.DATE));
		dateEnd.setMaxSelectableTime(limit);

		ImageIcon saveIcon = new ImageIcon("images/btn_opslaan.png");		
		Image imgb = saveIcon.getImage();
		Image saveImage = imgb.getScaledInstance(115, 25, java.awt.Image.SCALE_SMOOTH);
		saveIcon = new ImageIcon(saveImage);
		JButton saveBtn = new JButton(saveIcon);
		saveBtn.setContentAreaFilled(false);
		saveBtn.setFocusPainted(false);
		saveBtn.setBorderPainted(false);
		bottomPanel.add(saveBtn);
		saveBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(validateStringInput(comboArtist.getSelectedItem()) && validateStringInput(comboPodium.getSelectedItem())){
					Act tmpAct = new Act(getArtist(), getPopularity(), getPodium(), getStartTime(), getEndTime());
					agendaData.addAct(tmpAct);
					clearFields();
					frame.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(frame, "Onjuiste gegevens ingevoerd.");
				}
				
				
				
			}
		});
		
		ImageIcon cancelIcon = new ImageIcon("images/btn_annuleren.png");		
		Image img = cancelIcon.getImage();
		Image cancelImage = img.getScaledInstance(115, 25, java.awt.Image.SCALE_SMOOTH);
		cancelIcon = new ImageIcon(cancelImage);
		JButton cancelBtn = new JButton(cancelIcon);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setFocusPainted(false);
		cancelBtn.setBorderPainted(false);
		bottomPanel.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearFields();
				frame.setVisible(false);	
			}
		});
		

        

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.pack();
	}
	
	public boolean validateDate(){
		if(dateStart.getDate().before(dateEnd.getDate())) return true;
		else return false;
	}
	
	public boolean validateStringInput(Object input){
		if(input == null) return false;
		else return true;
	}
	
	public String getArtist(){
		return comboArtist.getSelectedItem().toString();
	}
	
	public int getPopularity(){
		return sliderPopularity.getValue();
	}
	
	public String getPodium(){
		return comboPodium.getSelectedItem().toString();
	}
	
	public String getStartTime(){
		return dateStart.getDate().getHours() + ":" + dateStart.getDate().getMinutes();
	}
	
	public String getEndTime(){
		return dateStart.getDate().getHours() + ":" + dateStart.getDate().getMinutes();
	}
	
	public void setVisible(Boolean visible){
		frame.setVisible(visible);
	}
	
	public void clearFields()
	{
		comboArtist.setSelectedItem("");
		comboPodium.setSelectedItem("");
		sliderPopularity.setValue(0);
	}
	public int[] getUnavailableDaysInAMonth(int month, int year) {
		Vector unavailableDays = new Vector();
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		int firstday = cal.getActualMinimum(Calendar.DATE);
		int lastday = cal.getActualMaximum(Calendar.DATE);
		for(int i=firstday; i<= lastday; i++)
		{
			cal.set(Calendar.DATE, i);
			int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
			if(dayofweek == Calendar.SUNDAY || dayofweek == Calendar.SATURDAY)
				unavailableDays.add(new Integer(i));
		}
		int[] retval = new int[unavailableDays.size()];
		for(int i=0; i<retval.length; i++)
			retval[i] = ((Integer)(unavailableDays.elementAt(i))).intValue();
		return retval;
	}

}
