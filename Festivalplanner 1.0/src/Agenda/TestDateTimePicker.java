package Agenda;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lavantech.gui.comp.CompactGridLayout;
import com.lavantech.gui.comp.DateTimePicker;
import com.lavantech.gui.comp.DateUnavailabilityModel;
import com.lavantech.gui.comp.LocaleSpecificResources;

public class TestDateTimePicker extends JPanel implements ActionListener, DateUnavailabilityModel
{
    JCheckBox checkBox;
    JCheckBox allowClearCheckBox;
    DateTimePicker picker1, picker2, picker3;
    
    public static void main(String[] args){
    	new TestDateTimePicker();
    }

    public TestDateTimePicker()
    {
        super(new BorderLayout());
        JPanel gridPanel = new JPanel(new CompactGridLayout(0,2));
        add(gridPanel,BorderLayout.NORTH);

        gridPanel.add(new JLabel("Date & Time"));
        GregorianCalendar cal = new GregorianCalendar();
        picker1 = new DateTimePicker(cal, "dd-MMM-yyyy HH:mm:ss z");
        gridPanel.add(picker1);
        picker1.addActionListener(this);
        picker1.setDateUnavailabilityModel(this);
        picker1.setDisplayTodayButton(false);
        GregorianCalendar limit = (GregorianCalendar)cal.clone();
        limit.set(Calendar.MONTH, Calendar.DECEMBER);
        limit.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		limit.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        picker1.setMaxSelectableTime(limit);

        gridPanel.add(new JLabel("Date"));
        picker2 = new DateTimePicker(new Date(),
            "dd/MM/yy", true, false);
        gridPanel.add(picker2);
        picker2.addActionListener(this);
        picker2.setDateUnavailabilityModel(this);
		picker2.setDisplayClearButton(true);

        LocaleSpecificResources.setHourFormat(LocaleSpecificResources.HOUR_FORMAT_24);
        gridPanel.add(new JLabel("Time"));
        picker3 = new DateTimePicker(new Date(),
            "HH:mm:ss", false, true);
        gridPanel.add(picker3);
        picker3.getTimePanel().getClockPanel().setHourFormat(LocaleSpecificResources.HOUR_FORMAT_24);
        picker3.addActionListener(this);

        checkBox = new JCheckBox("Editable");
        gridPanel.add(checkBox);
        checkBox.addActionListener(this);

		allowClearCheckBox = new JCheckBox("Allow Clear");
        gridPanel.add(allowClearCheckBox);
        allowClearCheckBox.addActionListener(this);

    }

    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == checkBox)
        {
            picker1.setEditable(checkBox.isSelected());
            picker2.setEditable(checkBox.isSelected());
            picker3.setEditable(checkBox.isSelected());
        }
		else if(evt.getSource() == allowClearCheckBox)
		{
            picker1.setDisplayClearButton(allowClearCheckBox.isSelected());
            picker2.setDisplayClearButton(allowClearCheckBox.isSelected());
            picker3.setDisplayClearButton(allowClearCheckBox.isSelected());
		}
        else
        {
			DateTimePicker picker = (DateTimePicker)evt.getSource();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss z");
			if(picker.getCalendar() != null)
			{
				formatter.setCalendar(((DateTimePicker)evt.getSource()).getCalendar());
				System.out.println(formatter.format(((DateTimePicker)evt.getSource()).getCalendar().getTime()));
			}
        }
    }

    public int[] getUnavailableDaysInAMonth(int month, int year)
    {
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
