package Agenda;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class NowPlaying implements Runnable {

	public static int HEIGHT = 1200;
	public static int WIDTH = 100;
	
	public static JPanel panel;
	public JFrame frame;
	private String[] nowPlaying = "intit list ".split(" ");
	private JList list;
	private AgendaData data;
	private GUI1 gui;


	public NowPlaying(AgendaData data, GUI1 gui)
	{
		this.gui = gui;
		this.data = data;
		makeFrame();
		new Thread(this).start();
		
	}
	
	public void updateNP(){
		nowPlaying = null;
		nowPlaying = new String[data.getActs().size() * 5];
		
		int idx = 0;
		try{
			
		for(int i=0; i < data.getActs().size(); i++){
			if((data.getActs().get(i).isNowPlaying()))
			{
				nowPlaying[idx] = data.getActs().get(i).getArtist();
				nowPlaying[idx += 1] = data.getActs().get(i).getStage();
				nowPlaying[idx += 1] = data.getActs().get(i).toStringTime();
				nowPlaying[idx += 1] = "*****************************".trim();
				idx++;
			}
		
			
		}
		newJlist(nowPlaying);
		}catch(NullPointerException e){
			e.printStackTrace();
			newJlist(nowPlaying);			
		}
		
	}

	
	private void newJlist(String[] obj){
		panel.remove(list);
		list = null;
		list = new JList(obj);
		panel.setMinimumSize(list.getSize());
		panel.setPreferredSize(list.getSize());
		panel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.add(list);
		panel.validate();
		panel.repaint();
		
	}
		
	
	public void makeFrame()
	{
		list = new JList(nowPlaying);
		panel = new JPanel();
		updateNP();
		panel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setVisible(true);	
		list.setVisibleRowCount(0);
		panel.add(list);
		
	}
	
	
	
	
	private void wait(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			updateNP();
			wait(10000);
		}
		
	}
	

	
	}

	




