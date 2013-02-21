import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class AgendaFrame
{
	private JFrame frame;
	private Container pane;
	private JButton button;
	private JPanel buttons;
	private JPanel data;
	private ArrayList<Gegevens> datas;
	private JPanel lijst;
	private JPanel texts;
	private JTextField artist;
	private JTextField popu;
	private JTextField podi;
	private JTextField Stijd;
	private JTextField Etijd;

	public static void main(String[] args)
	{
		new AgendaFrame();
	}

	public AgendaFrame()
	{
		datas = new ArrayList<Gegevens>();
		makeFrame();
	}

	public void makeFrame()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);

		pane = new Container();
		frame.setContentPane(pane);
		pane.setLayout(new BorderLayout());

		buttons = new JPanel();
		pane.add(buttons, BorderLayout.WEST);
		buttons.setLayout(new BorderLayout());

		lijst = new JPanel();
		pane.add(lijst, BorderLayout.CENTER);
		lijst.setLayout(new BorderLayout());

		data = new JPanel();
		lijst.add(data, BorderLayout.NORTH);
		data.setLayout(new GridLayout(0, 5));
		data.setBackground(Color.ORANGE);

		texts = new JPanel();
		lijst.add(texts, BorderLayout.SOUTH);
		texts.setLayout(new GridLayout(0, 5));
		texts.setBackground(Color.green);

		button = new JButton();
		buttons.add(button, BorderLayout.CENTER);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addList(artist.getText());
				addList("" + popu.getText());
				addList(podi.getText());
				addList(Stijd.getText());
				addList(Etijd.getText());
				addData(artist.getText(), Integer.parseInt(popu.getText()),
						podi.getText(), Stijd.getText(), Etijd.getText());
				artist.setText("");
				popu.setText("");
				podi.setText("");
				Stijd.setText("");
				Etijd.setText("");
				frame.setVisible(false);
				frame.setVisible(true);
			}
		});

		button.setVisible(true);
		button.setText("Invoeren");

		JLabel artistTab = new JLabel();
		artistTab.setText("Artiest");
		data.add(artistTab);

		JLabel popularity = new JLabel();
		popularity.setText("Populariteit");
		data.add(popularity);

		JLabel stage = new JLabel();
		stage.setText("Podium");
		data.add(stage);

		JLabel sTijd = new JLabel();
		sTijd.setText("Start tijd");
		data.add(sTijd);

		JLabel eTijd = new JLabel();
		eTijd.setText("Eind tijd");
		data.add(eTijd);

		JLabel zanger = new JLabel("Artiest");
		texts.add(zanger);

		JLabel pop = new JLabel("Populariteit");
		texts.add(pop);

		JLabel pod = new JLabel("Podium");
		texts.add(pod);

		JLabel bTijd = new JLabel("Begin tijd");
		texts.add(bTijd);

		JLabel qTijd = new JLabel("eind tijd");
		texts.add(qTijd);

		artist = new JTextField(10);
		texts.add(artist);

		popu = new JTextField(10);
		texts.add(popu);

		podi = new JTextField(10);
		texts.add(podi);

		Stijd = new JTextField(10);
		texts.add(Stijd);

		Etijd = new JTextField(10);
		texts.add(Etijd);

		texts.setVisible(true);
		frame.pack();
		frame.setVisible(true);

	}

	public void addData(String artist, int pop, String stage, String sTijd,
			String eTijd)
	{
		datas.add(new Gegevens(artist, pop, stage, sTijd, eTijd));
	}

	public void testData()
	{
		datas.add(new Gegevens("Paul", 1, "Ergens", "8:45", "17:00"));
	}

	public void addList2()
	{
		addList(datas.get(0).getArtiest());
		addList("" + datas.get(0).getPopulariteit());
		addList(datas.get(0).getStage());
		addList(datas.get(0).getStartTijd());
		addList(datas.get(0).getEindTijd());
	}

	public void addList(String data)
	{
		JLabel knop = new JLabel(data);
		this.data.add(knop);
		pane.setVisible(false);
		pane.setVisible(true);
	}

}
