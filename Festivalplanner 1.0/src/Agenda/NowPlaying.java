package Agenda;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;

public class NowPlaying implements Runnable {

	final static int HEIGHT = 300;
	final static int WIDTH = 500;
	
	private JFrame frame;
	private JList list;
	private String[] nowPlaying = "intit list ".split(" ");
	private AgendaData data;


	public NowPlaying(AgendaData data)
	{
		makeFrame();
		this.data = data;
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
				nowPlaying[idx += 1] = "***************************".trim();
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
		frame.remove(list);
		list = null;
		list = new JList(obj);
		frame.add(list);
		//frame.revalidate();
		frame.validate();
		frame.repaint();	
	}
	
	
	
	

	
	
	public void makeFrame()
	{
		frame = new JFrame("Now Playing");
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		list = new JList(nowPlaying);
		list.setSize(new Dimension(WIDTH, HEIGHT));
		frame.add(list);
		updateNP();
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
			wait(1000/5);
		}
		
	}
	
	}

	




