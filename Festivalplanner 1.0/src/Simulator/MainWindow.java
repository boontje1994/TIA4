package Simulator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Agenda.AgendaData;
import Agenda.Stage;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * Write a description of class Application here.
 * 
 * @author Vincent Stout 
 * @version 1.0
 */
public class MainWindow extends JFrame
{
 
    private Map map;
    private User user;
    private AI ai;
    private AgendaData data;
       
    private static double zoom = 1;
    private static int t;
    private static int posX = 1800;
    private static int posY = 1000;
    
    //Muis
    private int isSelected = 0;
    private static int x = 100;
    private static int y = 100;
    private int offsetX = 0;
    private int offsetY = 0;
    private Point lastMousePosition;
	private Rightcontent rightcontent;
	private boolean leltest;
	private Point lelpos;
	public boolean moving;
	private int state;
	private boolean reset;
	public JLabel	timeLabel;
	private int tickCounter;
	
	public JSlider timeSlider;
    
    //Velden
    private static JTextField xLength;
    private static JTextField yLength;
    
    public MainWindow(AgendaData data) {
    	
        super("Simulatie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        map = new Map();
        user = new User();
        ai = new AI(data);
        this.data = data;
        moving = false;
        lelpos = new Point();
        state = 0;
        reset = false;
        
        //System.out.println(ai.getData());
        //ai.setFromData("-+-22.799839811966926:18.14991019904849=+=5.835685330926907-+-438.1743629586493:428.53441272501533=+=3.2916875226481004-+-453.0993162657535:332.64180874907754=+=3.084412104015996-+-241.86010803298592:427.4194527549533=+=3.9923886987071646-+-130.5401848654935:302.3913727782494=+=2.6758230540327004-+-248.05814529089832:357.50582944821303=+=3.666507482784027-+-358.50339286433615:463.28867864180995=+=0.5525593824945532-+-319.51838220445103:462.61010474304067=+=3.1674023096183412-+-172.18717400502769:144.31963215574788=+=4.442874632635929-+-255.0573853700488:479.58403783889185=+=2.7513115156772416-+-245.95751234655566:303.4672644318088=+=3.941007148196639-+-65.83205682623544:178.27495680715398=+=0.791360380394835-+-16.51807403321198:382.15495487036515=+=5.2923104011547055-+-55.34475807696787:329.9343231091641=+=0.9584006465056369-+-134.93203836985913:159.10288261148642=+=2.347163109203493-+-318.93655799080517:346.13113582369715=+=0.6144669360221074-+-88.32057126436676:152.1205359987296=+=3.860157102349144-+-219.13278336349472:48.071009113361704=+=2.9618404468148016-+-1.7198903688027678:251.60612476265348=+=4.034123743974817-+-428.26349855899787:458.3906233740753=+=4.136713929258216-+-326.57183957264965:91.99763653423693=+=1.762205919588326-+-167.98439287280743:316.5887142341466=+=3.562289819844467-+-483.81356404834446:38.935207855372134=+=5.4836017992326465-+-342.54760243158086:172.55431091804402=+=4.73155717974128-+-375.3661985037437:319.45566076708343=+=5.554630257154994-+-126.98534952923096:307.13252265841925=+=2.3486089477618437-+-131.53724924412813:471.3377833513832=+=3.4370178592100373-+-368.02495837285556:315.98852848932756=+=4.645110685014552-+-76.66770963420255:274.1312917077881=+=2.194108016189934-+-11.993083965143924:104.53903776729146=+=3.5986722827495328-+-279.18424710299985:228.9981917074293=+=4.147508566138255-+-434.82001950682525:213.4524420291043=+=2.6401559652859143-+-492.65586864969515:425.02838766592515=+=4.613503619696543-+-1.3359626076900888:191.89668969034196=+=1.7275225312728588-+-383.7892018986491:206.10136374618594=+=3.7312402683697226-+-278.36635759198793:79.74851584003056=+=1.1558684409675812-+-203.49858760127455:491.7827351484919=+=2.424080556850582-+-414.87288813830605:50.46515146726954=+=5.685149814108841-+-64.91808092510304:207.484432030818=+=0.5199403694746156-+-245.92903563518243:308.0472380120671=+=0.5470450666843926-+-3.8282601413466844:209.6838686998501=+=1.9935517396603823-+-381.23269714881883:258.96009145538363=+=4.400445916371228-+-73.48814694414907:10.082748881065196=+=3.3505668053790574-+-475.89391522641205:182.19106845723277=+=4.693228066295364-+-358.0131171801973:284.06844254557654=+=5.894863615261477-+-116.33831222438418:166.6509249824787=+=3.9985205628940594-+-21.63385913376986:111.7865876119858=+=1.5258608754606346-+-469.4620335750155:348.41167998940836=+=5.7010417556482755-+-70.507445450186:29.797442832668608=+=1.7609496216855718-+-462.98061077551614:264.035472451786=+=2.7227991033867562-+-419.01358211126825:145.11049062942533=+=0.44028540474985745-+-80.73470650126352:298.3982925405452=+=3.172900622080478-+-83.13050476397754:276.00228173309233=+=0.6680614864848154-+-152.2422655792861:283.54715471114025=+=4.014444349564414-+-383.7059250672014:72.23709247854615=+=5.217897684655031-+-191.8977665776621:141.53385261313068=+=0.0067977426367386955-+-126.06575225305878:435.132195321661=+=4.6852191256288975-+-42.98176632466755:330.81641956340576=+=3.544142295462601-+-327.9735264985947:401.53236814754973=+=0.8510988175993014-+-315.31096514248213:79.36988012239665=+=4.049028860701901-+-123.68797462284259:188.55830727526978=+=1.1078129597630975-+-375.373267052845:474.4497996711824=+=0.7391947221272506-+-460.67560057978557:273.53963687804054=+=3.474368428476716-+-73.78116478303093:240.03929252595063=+=1.2860412788629572-+-290.01761022526495:28.58729619659345=+=4.838627539799594-+-338.6576738506736:209.1060702959922=+=1.9800714148695142-+-98.99018563264423:356.744618874065=+=5.3249630027650685-+-454.14580077940246:384.31104579983474=+=3.491521300990244-+-241.6678386453318:418.7101212152837=+=1.4514523692227326-+-1.795980610493797:52.84077621736605=+=2.747995106215145-+-42.36011614697721:70.0640448342259=+=4.128626106904196-+-407.5154583034227:458.3686564655822=+=1.8713618327288135-+-208.5914555157923:90.25910589366609=+=3.706359780756491-+-388.62574329256773:245.2564928384453=+=3.0400944124209404-+-131.20639169933196:210.04554753916355=+=1.7664533533619309-+-282.12230271061526:41.14914405179776=+=2.4314853755420227-+-34.75093324832618:484.36917635910845=+=4.2127401703435705-+-196.40668624008362:77.93774664304637=+=4.419672306205576-+-91.95591829659206:106.17661219385171=+=1.81934295866411-+-221.69370093197588:416.2075356706554=+=3.0450316519188383-+-132.02649115788478:73.23691870792537=+=1.33491594412749-+-358.3018992620099:312.9658221182828=+=3.7114009461949125-+-358.3099641177951:457.48555567612834=+=2.6304257483332285-+-43.61788393282362:289.57594374332984=+=2.8877209407338-+-381.2433642015692:254.14344665448107=+=5.190693886834247-+-336.37585343361656:429.0002251857026=+=5.106904857741219-+-256.4332545995342:17.777739971537308=+=4.351341132399553-+-226.89144499865404:75.96543882454742=+=0.9814076179304205-+-164.1410737284692:203.55514953508785=+=1.0152047501200485-+-202.1009822808632:77.47988025532338=+=0.46567891947763845-+-248.87272770813584:295.3852858130619=+=5.664708793914284-+-489.93575844796425:185.91243397272794=+=1.2394417197807506-+-259.42338276558746:6.838606343371357=+=0.6402236206458488-+-216.14945740508452:133.9385072541461=+=5.906132668784341-+-348.5188489086568:416.2186698606816=+=3.015887153689783-+-22.842468770948255:366.10060255760123=+=1.3337405865860363-+-244.71985606047414:41.72397985262655=+=2.735747642064641-+-258.40703204628534:91.24522029649323=+=0.7623347283056329-+-339.8011361835577:266.193956364976=+=2.861595470165196-+-257.45540947360746:374.0929553238687=+=2.9643729702978305");
        //Container
        JPanel container = new JPanel(new BorderLayout());
        setContentPane(container);
        
        //Menucontent - Menu
        JPanel menucontent = new Menucontent();
        container.add(menucontent, BorderLayout.NORTH);
        
        //Rightcontent - Shape
        rightcontent = new Rightcontent();
        container.add(rightcontent, BorderLayout.EAST);
        
        //Maincontent - Paint
        JPanel maincontent = new Maincontent();
        container.add(maincontent, BorderLayout.CENTER);
        
        //Bottomcontent - Credits
        JPanel bottomcontent = new bottomcontent();
        container.add(bottomcontent, BorderLayout.SOUTH);
              
        //Mouse
        Handlerclass handler = new Handlerclass();
        maincontent.addMouseListener(handler);
        maincontent.addMouseMotionListener(handler);
        maincontent.addMouseWheelListener(handler);
        
        //Keyboard
        ActionMap actionMap = maincontent.getActionMap();
        InputMap inputMap = maincontent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "forward");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "back");
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "d");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "a");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "w");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "s");

        actionMap.put("right", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                posX -= 20;
            }            
        });

        actionMap.put("left", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                posX += 20;               
            }   
        });

        actionMap.put("forward", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                posY += 20;
            }     
        });

        actionMap.put("back", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                posY -= 20;           
            }            
        });
            
        actionMap.put("d", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                user.right();
            }
        });
        
        actionMap.put("a", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                user.left();
            }
        });
        
        actionMap.put("w", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                user.forward();
            }
        });
        
        actionMap.put("s", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                user.backward();
            }
        });
        
        //Maximaliseert het venster.
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = toolkit.getScreenSize();
            
        setSize(size);
    }
    
    class Handlerclass implements MouseListener, MouseMotionListener,MouseWheelListener {
        
		//Mouse events
        public void mouseClicked(MouseEvent event) {
        	if (!moving)
        	{
	        	for (Stage stage : data.getStages())
	        	{
	        		System.out.println("this stage loc is " + stage.getPos().x + "x" + stage.getPos().y);
	        		if (stage.isLocated())
		        		{
		        			if (stage.isClicked((int)lelpos.getX(), (int)lelpos.getY()))
			        		{
			        			moving = true;
			        			stage.setSelected(true);
			        			if(event.getButton() == event.BUTTON3)
			        			{
			        				stage.setScaleSign(true);
			        			}
			        			if(event.getButton() == event.BUTTON1)
			        			{
			        				stage.setScaleSign(false);
			        			}
			        		}
	        			}
	        	}
	        	for (Crossroad item : data.getCrossroads())
	        	{
	        		if (item.isLocated())
	        		{
	        			if (item.isClicked((int)lelpos.getX(), (int)lelpos.getY()))
	        			{
	        				moving = true;
	        				item.setSelected(true);
	        			}
	        		}
	        	}
        	}
        	else
        	{
        		for (Stage stage : data.getStages())
	        	{
        			if (stage.isLocated())
	        		{
	        		
		        		if (stage.isSelected())
		        		{
		        			stage.setSelected(false);
		        			moving = false;
		        		}
	        		}
	        	}
        		for (Crossroad item : data.getCrossroads())
	        	{
	        		if (item.isLocated())
	        		{
	        			if (item.isSelected())
	        			{
	        				moving = false;
	        				item.setSelected(false);
	        			}
	        		}
	        	}
        	}
        }
        public void mouseReleased(MouseEvent event) 
        {
        	
        }
        public void mousePressed(MouseEvent event) 
        {
        	
        	
        }
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
        //Mouse motion events
        public void mouseDragged(MouseEvent event) 
        {
        	
        }
        public void mouseMoved(MouseEvent event) {
        	System.out.println(event.getPoint());
        	System.out.println("the camera is now at " + posX + "x" + posY);
        	System.out.println("the zoom is now " + zoom);
        	System.out.println("the rotation is " + Math.toRadians(t)); //TODO there are still issues when rotated
        	System.out.println("meaning the virtual cursor position is " + ((event.getPoint().x/zoom)-posX) + "x" + ((event.getPoint().y/zoom)-posY));
        	leltest = true;
        	lelpos.setLocation(((int)((event.getPoint().x)/zoom)-posX+ t), (int) (((event.getPoint().y)/zoom)-posY+ t));
        	if (moving)
        	{
	        	for (Stage stage : data.getStages())
	        	{
	        	
	        		if (stage.isLocated())
	        		{
		        		if (stage.isSelected())
		        		{
		        			System.out.println("stage " + stage.getName() + " is being moved!");
		        			stage.setPos(new Point(((int)((event.getPoint().x)/zoom)-posX+ t), (int) (((event.getPoint().y)/zoom)-posY+ t)));
		        		}
	        		}
	        	}
	        	for (Crossroad item : data.getCrossroads())
	        	{
	        		if (item.isLocated())
	        		{
	        			if (item.isSelected())
	        			{
	        				item.setPosition(new Point(((int)((event.getPoint().x)/zoom)-posX+ t), (int) (((event.getPoint().y)/zoom)-posY+ t)));
	        			}
	        		}
	        	}
        	}
        }
		@Override
		public void mouseWheelMoved(MouseWheelEvent event) 
		{
			if (moving)
        	{
	        	for (Stage stage : data.getStages())
	        	{
	        	
	        		if (stage.isLocated())
	        		{
		        		if (stage.isSelected())
		        		{
		        			stage.setRotate(stage.getRotate() + event.getWheelRotation()*6);
//		        			stage.setScaleMag(event.getWheelRotation());
		        		}
	        		}
	        	}
        	}
		}
        
    }
    
    class Menucontent extends JPanel {
        
        private JToggleButton stop;
		private JToggleButton play;
		private JToggleButton pause;

		public Menucontent() {
            
            setLayout(new GridLayout(1,2));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            JPanel leftMenuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel rightMenuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            ImageIcon pauseIcon = new ImageIcon("images/pause.png");
            ImageIcon pauseIconSelect = new ImageIcon("images/pauseSelect.png");
            pause = new JToggleButton(pauseIcon);
            pause.setSelectedIcon(pauseIconSelect);
            pause.setContentAreaFilled(false);
            pause.setBorder(null);
            pause.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    state = 2;
                    play.setSelected(false);
                    stop.setSelected(false);
                }
            });
            leftMenuPanel.add(pause);
            
            ImageIcon playIcon = new ImageIcon("images/play.png");
            ImageIcon playIconSelect = new ImageIcon("images/playSelect.png");
            play = new JToggleButton(playIcon);
            play.setSelectedIcon(playIconSelect);
            play.setContentAreaFilled(false);
            play.setBorder(null);
            leftMenuPanel.add(play);
            play.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    state = 1;
                    stop.setSelected(false);
                    pause.setSelected(false);
                }
            });
            
            ImageIcon stopIcon = new ImageIcon("images/stop.png");
            ImageIcon stopIconSelect = new ImageIcon("images/stopSelect.png");
            stop = new JToggleButton(stopIcon);
            stop.setSelectedIcon(stopIconSelect);
            stop.setContentAreaFilled(false);
            stop.setBorder(null);
            stop.setSelected(true);
            stop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    state = 0;
                    play.setSelected(false);
                    pause.setSelected(false);
                }
            });
            leftMenuPanel.add(stop);            
            
            timeSlider = new JSlider(JSlider.HORIZONTAL,0,24*60,0);    //TODO
            Color colorSlider = new Color(111,111,111);
            timeSlider.setBackground(colorSlider);
            timeSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
//                     int value = timeSlider.getValue();
//                     AI ai = new AI(value);
                	System.out.println(timeSlider.getValue());
//                	data.setTime(timeSlider.getValue()%60, timeSlider.getValue()/60);
                	
                }
            });
            leftMenuPanel.add(timeSlider);
            
            ImageIcon diaryIcon = new ImageIcon("images/diary.png");
            ImageIcon diaryIconSelect = new ImageIcon("images/diarySelect.png");
            JButton diary = new JButton(diaryIcon);
            diary.setPressedIcon(diaryIconSelect);
            diary.setContentAreaFilled(false);
            diary.setBorder(null);
            rightMenuPanel.add(diary);
            diary.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				data.setSimVisible(false);
    				data.setAgendaVisible(true);
    			}

    		});
            
            timeLabel = new JLabel();
            leftMenuPanel.add(timeLabel);
            
            
            Color c = new Color(111,111,111);
            leftMenuPanel.setBackground(c);
            rightMenuPanel.setBackground(c);
            
            add(leftMenuPanel);
            add(rightMenuPanel);    

        }
     
    }
    
    class Maincontent extends JPanel {
             
        private Thread thread1;
        
        public Maincontent() {
            
            Color c = new Color(64,179,57);
            setBackground(c);
            
            
        }
        
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g; 
            super.paintComponent(g2);
            
            g2.scale(zoom,zoom);
            g2.translate(posX,posY);
//            g2.translate(0, 0);
            g2.rotate(Math.toRadians(t),400,400); 
            
            //uit ThreadOne
            
            map.drawMountain((Graphics2D)g2);
            map.drawTree((Graphics2D)g2);
            map.drawField((Graphics2D)g2);
            map.drawGRID((Graphics2D)g2);
            map.drawEntrance((Graphics2D)g2);
            //map.drawRoad((Graphics2D)g2);
            
            user.drawUser((Graphics2D)g2);
            
            //AI
            ai.drawVisitor((Graphics2D)g2);
            
            for(Crossroad cross : data.getCrossroads())
            {
            	if(cross.isLocated())
            	{
            		cross.drawCrossroad(g2);
            	}
            }
            
            /*Graphics2D a = (Graphics2D)g2.create();
            Graphics2D b = (Graphics2D)g2.create();
            Graphics2D c = (Graphics2D)g2.create();
            Graphics2D d = (Graphics2D)g2.create();
            Graphics2D e = (Graphics2D)g2.create();*/
            for (Stage stage : data.getStages())
            {
            	if (stage.isLocated())
            	{
            		stage.drawStage(g2);
            	}
            }
            
            if (leltest)
            {
            	g2.draw(new Rectangle2D.Double(lelpos.getX(), lelpos.getY(), 20, 20));
            }

            //threadOne((Graphics2D)g2);
            
        }
        

    }
    
    class Rightcontent extends JPanel {
        
        private JComboBox stageBox;

		public Rightcontent() {
            
            setBackground(Color.WHITE);
            
            //Control menu
            JPanel controlMenu = new JPanel(null);
            controlMenu.setPreferredSize(new Dimension(250,600));
            controlMenu.setBackground(Color.WHITE);
            add(controlMenu);
            
            //Linksboven
            ImageIcon leftUpIcon = new ImageIcon("images/upLeft.png");
            ImageIcon leftUpSelectIcon = new ImageIcon("images/upSelectLeft.png");
            JButton upLeft = new JButton(leftUpIcon);
            upLeft.setPressedIcon(leftUpSelectIcon);
            upLeft.setContentAreaFilled(false);
            upLeft.setBorder(null); 
            upLeft.setFocusPainted(false);
            upLeft.setBounds(15,10,50,50);
            upLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posY += 20;
                    posX += 20;  
                }
            });
            controlMenu.add(upLeft);
            
            //Boven
            ImageIcon upIcon = new ImageIcon("images/up.png");
            ImageIcon upSelectIcon = new ImageIcon("images/upSelect.png");
            JButton up = new JButton(upIcon);
            up.setPressedIcon(upSelectIcon);
            up.setContentAreaFilled(false);
            up.setBorder(null);  
            up.setFocusPainted(false);
            up.setBounds(95,10,60,60);
            up.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posY += 20;
                }
            });
            controlMenu.add(up);
            
            //Rechtsboven            
            ImageIcon rightUpIcon = new ImageIcon("images/upRight.png");
            ImageIcon rightUpSelectIcon = new ImageIcon("images/upSelectRight.png");
            JButton upRight = new JButton(rightUpIcon);
            upRight.setPressedIcon(rightUpSelectIcon);
            upRight.setContentAreaFilled(false);
            upRight.setBorder(null); 
            upRight.setFocusPainted(false);
            upRight.setBounds(180,10,50,50);
            upRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posY += 20;
                    posX -= 20;  
                }
            });
            controlMenu.add(upRight);
           
            //Links           
            ImageIcon leftIcon = new ImageIcon("images/left.png");
            ImageIcon leftSelectIcon = new ImageIcon("images/leftSelect.png");
            JButton left = new JButton(leftIcon);
            left.setPressedIcon(leftSelectIcon);
            left.setContentAreaFilled(false);
            left.setBorder(null);      
            left.setFocusPainted(false);
            left.setBounds(10,70,60,60);
            left.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posX += 20;
                }
            });
            controlMenu.add(left);
            
            //Positie
            ImageIcon positionIcon = new ImageIcon("images/position.png");
            ImageIcon positionSelectIcon = new ImageIcon("images/positionSelect.png");
            JButton position = new JButton(positionIcon);
            position.setPressedIcon(positionSelectIcon);
            position.setContentAreaFilled(false);
            position.setBorder(null);        
            position.setFocusPainted(false);
            position.setBounds(95,70,60,60);
            position.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posX = 1800;
                    posY = 1000;
                    zoom = 0.3;
                    t = 0;
                }
            });
            controlMenu.add(position);
            
            //Right           
            ImageIcon rightIcon = new ImageIcon("images/right.png");
            ImageIcon rightSelectIcon = new ImageIcon("images/rightSelect.png");
            JButton right = new JButton(rightIcon);
            right.setPressedIcon(rightSelectIcon);
            right.setContentAreaFilled(false);
            right.setBorder(null);        
            right.setFocusPainted(false);
            right.setBounds(180,70,60,60);
            right.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posX -= 20;
                }
            });
            controlMenu.add(right);

            //Linksonder
            ImageIcon leftDownIcon = new ImageIcon("images/downLeft.png");
            ImageIcon leftDownSelectIcon = new ImageIcon("images/downSelectLeft.png");
            JButton downLeft = new JButton(leftDownIcon);
            downLeft.setPressedIcon(leftDownSelectIcon);
            downLeft.setContentAreaFilled(false);
            downLeft.setBorder(null);
            downLeft.setFocusPainted(false);
            downLeft.setBounds(15,135,60,60);
            downLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posY -= 20;
                    posX += 20; 
                }
            });
            controlMenu.add(downLeft);            
            
            //Onder       
            ImageIcon downIcon = new ImageIcon("images/down.png");
            ImageIcon downSelectIcon = new ImageIcon("images/downSelect.png");
            JButton down = new JButton(downIcon);
            down.setPressedIcon(downSelectIcon);
            down.setContentAreaFilled(false);
            down.setBorder(null);    
            down.setFocusPainted(false);
            down.setBounds(95,135,60,60);
            down.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posY -= 20;
                }
            });
            controlMenu.add(down);
            
            //Rechtsonder
            ImageIcon rightDownIcon = new ImageIcon("images/downRight.png");
            ImageIcon rightDownSelectIcon = new ImageIcon("images/downSelectRight.png");
            JButton downRight = new JButton(rightDownIcon);
            downRight.setPressedIcon(rightDownSelectIcon);
            downRight.setContentAreaFilled(false);
            downRight.setBorder(null);          
            downRight.setFocusPainted(false);
            downRight.setBounds(180,135,60,60);
            downRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posY -= 20;
                    posX -= 20;                         
                }
            });
            controlMenu.add(downRight);
                       
            //Inzoemen
            ImageIcon zoomInIcon = new ImageIcon("images/zoomIn.png");
            ImageIcon zoomInSelectIcon = new ImageIcon("images/zoomInSelect.png");
            JButton zoomIn = new JButton(zoomInIcon);
            zoomIn.setPressedIcon(zoomInSelectIcon);
            zoomIn.setContentAreaFilled(false);
            zoomIn.setBorder(null);          
            zoomIn.setFocusPainted(false);
            zoomIn.setBounds(10,200,50,50);
            zoomIn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(zoom < 1.4)
                    zoom += 0.1;
                }
            });
            controlMenu.add(zoomIn);
            
            //Uitzoemen
            ImageIcon zoomOutIcon = new ImageIcon("images/zoomOut.png");
            ImageIcon zoomOutSelectIcon = new ImageIcon("images/zoomOutSelect.png");
            JButton zoomOut = new JButton(zoomOutIcon);
            zoomOut.setPressedIcon(zoomOutSelectIcon);
            zoomOut.setContentAreaFilled(false);
            zoomOut.setBorder(null);          
            zoomOut.setFocusPainted(false);
            zoomOut.setBounds(70,200,50,50);
            zoomOut.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(zoom > 0.3)
                    zoom -= 0.1;
                }
            });
            controlMenu.add(zoomOut);
            
            //Draaien links
            ImageIcon rotateLeftIcon = new ImageIcon("images/rotateLeft.png");
            ImageIcon rotateLeftSelectIcon = new ImageIcon("images/rotateLeftSelect.png");
            JButton rotateLeft = new JButton(rotateLeftIcon);
            rotateLeft.setPressedIcon(rotateLeftSelectIcon);
            rotateLeft.setContentAreaFilled(false);
            rotateLeft.setBorder(null);          
            rotateLeft.setFocusPainted(false);
            rotateLeft.setBounds(130,200,50,50);
            rotateLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   t -= 5;
                }
            });
            controlMenu.add(rotateLeft);
            
            //Draaien rechts
            ImageIcon rotateRightIcon = new ImageIcon("images/rotateRight.png");
            ImageIcon rotateRightSelectIcon = new ImageIcon("images/rotateRightSelect.png");
            JButton rotateRight = new JButton(rotateRightIcon);
            rotateRight.setPressedIcon(rotateRightSelectIcon);
            rotateRight.setContentAreaFilled(false);
            rotateRight.setBorder(null);          
            rotateRight.setFocusPainted(false);
            rotateRight.setBounds(190,200,50,50);
            rotateRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    t += 5;
                }
            });
            controlMenu.add(rotateRight);
            
            //Grid menu         
            xLength = new JTextField("2500");
            xLength.setBounds(10,270,65,30);
            controlMenu.add(xLength);       
            
            yLength = new JTextField("1500");
            yLength.setBounds(80,270,65,30);
            controlMenu.add(yLength);
            
            JButton change = new JButton("Wijzigen");
            change.setBackground(Color.WHITE);  
            change.setFocusPainted(false);
            change.setBounds(150,270,90,30);
            change.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {                    
                    try {
                        String xL = xLength.getText();
                        int x = Integer.parseInt(xL);
                        String yL = yLength.getText();
                        int y = Integer.parseInt(yL);
                        if(x <= 2500 && y <= 2500) {
                            Map map = new Map(x, y);
                        } else {
                            System.out.println("Out of bounds!");
                        }
                    } catch(Exception error) {
                        System.out.println("Wrong value!");
                    }
                }
            });
            controlMenu.add(change);
            
            JButton reset = new JButton("Resetten");
            reset.setBackground(Color.WHITE);
            reset.setFocusPainted(false);
            reset.setBounds(10,315,90,30);
            reset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                
                }
            });
            controlMenu.add(reset);
            
            //Construction menu
            JPanel constructionMenu = new JPanel(new GridLayout(3,2));
            constructionMenu.setBounds(0,360,250,250);
            constructionMenu.setBackground(Color.WHITE);
            stageBox = new JComboBox();
            JButton place = new JButton("Plaats podium");
            JButton placeCross = new JButton("Plaats kruispunt");
            
            // Define button place
            place.setBackground(Color.WHITE);
            place.setFocusPainted(false);
            place.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	for (Stage stage : data.getStages())
                	{
                		if (stage.getName().equals(stageBox.getSelectedItem()))
                		{
                			stage.initVisual(0, 0, 480, 280);
                			stage.setPos(new Point(0,0));
                		}
                	}
                }
            });
            
            // Define button placeCross
            placeCross.setBackground(Color.white);
            placeCross.setFocusPainted(false);
            placeCross.addActionListener(new ActionListener(){
            	public void actionPerformed(ActionEvent e){
            		System.out.println("Wat is deze shit!!!");
            			Crossroad cross = new Crossroad();
            			cross.initVisual(0, 0, 100, 120);
            			cross.setPosition(new Point(0,0));
            			data.addCrossroad(cross);
            			System.out.println("waarom!");
            	}
            });
            
            //dynamic stages
            //stageBox.addItem("lel");
            
            constructionMenu.add(stageBox);
            constructionMenu.add(place);
            constructionMenu.add(placeCross);
            controlMenu.add(constructionMenu);
            
            
        }
		
		 public void updateStageBox()
		    {
		    	if (data.getStages().size() > 0)
		        {
		            for (Stage stage : data.getStages())
		            {
		            	boolean check = false;
		            	for (int i=0; i < stageBox.getItemCount(); i++)
		            	{
		            		if (stage.getName().equals(stageBox.getItemAt(i)))
		            			check = true;
		            	}
		            	if (!check)
		            		stageBox.addItem(stage.getName());
		            }
		        }
		    }

    }
    
   
    
    class bottomcontent extends JPanel {
        
        public bottomcontent() {
            
            setLayout(new FlowLayout(FlowLayout.CENTER));
            Color c1 = new Color(255,255,255);
            Color c2 = new Color(111,111,111);
            setBackground(c2);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            JLabel credits = new JLabel("JTI1A4");
            credits.setForeground(c1);
            add(credits);
            
        }
       
    }

	public void tick() {
		if (state == 1)
		{	
			tickCounter ++;
			ai.update();
			reset = false;
			timeLabel.setText(data.getTime());
//			timeSlider.setValue(tickCounter);
			if(tickCounter == 1)
			{
				tickCounter = 0;
				timeSlider.setValue(data.getHour()*60+data.getTenthMinute()*10+data.getMinute()+1);
				data.tick();
			}
		}
		if (state == 0 && !reset)
		{
			ai.reset();
			reset = true;
			data.resetTime();
			timeLabel.setText(data.getTime());
		}
		if (data.getLocationExit().equals(new Point()))
			data.setLocationExit(map.getExitBlock());
		repaint();
		rightcontent.updateStageBox();
		//1/(float)time/1000)
		// s/f=spf
		// dus f = (1/spf)
		// fps is f

		//System.out.println(time + " fps");
		
		
	}

}
