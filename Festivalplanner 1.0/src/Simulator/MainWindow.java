package Simulator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
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
       
    private static double zoom = 1;
    private static int t;
    private static int posX = 600;
    private static int posY = 350;
    
    //Muis
    private int isSelected = 0;
    private static int x = 100;
    private static int y = 100;
    private int offsetX = 0;
    private int offsetY = 0;
    private Point lastMousePosition;
    
    //Stage objecten
    private Stage stageA;
    private Stage stageB;
    private Stage stageC;
    private Stage stageD;
    private Stage stageE;
    
    //Velden
    private static JTextField xLength;
    private static JTextField yLength;
    
    public MainWindow() {
        
        super("Simulatie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        map = new Map();
        user = new User();
        ai = new AI();
        
        //Container
        JPanel container = new JPanel(new BorderLayout());
        setContentPane(container);
        
        //Menucontent - Menu
        JPanel menucontent = new menucontent();
        container.add(menucontent, BorderLayout.NORTH);
        
        //Rightcontent - Shape
        JPanel rightcontent = new rightcontent();
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
                 if(posX > -800)
                    posX -= 20;
            }            
        });

        actionMap.put("left", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(posX < 800)
                    posX += 20;               
            }   
        });

        actionMap.put("forward", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(posY < 800)
                    posY += 20;
            }     
        });

        actionMap.put("back", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(posY > -800)
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
        setVisible(true);
        
    }
    
    class Handlerclass implements MouseListener, MouseMotionListener,MouseWheelListener {
        //Mouse events
        public void mouseClicked(MouseEvent event) {}
        public void mouseReleased(MouseEvent event) 
        {
        	if(stageA != null)
        	{
            	if(stageA.isSelected() == true)
            	{
            		stageA.setSelected(false);
            	}
        	}
        	if(stageB != null)
        	{
            	if(stageB.isSelected() == true && stageB != null)
            	{
            		stageB.setSelected(false);
            	}
        	}
        	if(stageC != null)
        	{
            	if(stageC.isSelected() == true && stageC != null)
            	{
            		stageC.setSelected(false);
            	}
        	}
        	if(stageD != null)
        	{
            	if(stageD.isSelected() == true && stageD != null)
            	{
            		stageD.setSelected(false);
            	}
        	}
        	if(stageE != null)
        	{
            	if(stageE.isSelected() == true && stageE != null)
            	{
            		stageE.setSelected(false);
            	}
        	}
        }
        public void mousePressed(MouseEvent event) 
        {
        	System.out.println(event.getPoint());
        	if(stageA != null)
        	{
        		System.out.println("X: " + stageA.getRect().getBounds().x + " Y: " +  stageA.getRect().getBounds().y);
            	if(stageA.getRect().contains(event.getPoint()))
            	{
            		if(stageA.isSelected() == false)
            		{
            			System.out.println("Selected A");
            			stageA.setSelected(true);
            			lastMousePosition = event.getPoint();
            		}
            	}
        	}
        	if(stageB != null)
        	{
            	if(stageB.getRect().contains(event.getPoint()))
            	{
            		if(stageB.isSelected() == false)
            		{
            			stageB.setSelected(true);
            			lastMousePosition = event.getPoint();
            		}
            	}
        	}
        	if(stageC != null)
        	{
            	if(stageC.getRect().contains(event.getPoint()) )
            	{
            		if(stageC.isSelected() == false)
            		{
            			stageC.setSelected(true);
            			lastMousePosition = event.getPoint();
            		}
            	}
        	}
        	if(stageD != null)
        	{
            	if(stageD.getRect().contains(event.getPoint()));
            	{
            		if(stageD.isSelected() == false)
            		{
            			stageD.setSelected(true);
            			lastMousePosition = event.getPoint();
            		}
            	}
        	}
        	if(stageE != null)
        	{
            	if(stageE.getRect().contains(event.getPoint()))
            	{
            		if(stageE.isSelected() == false)
            		{
            			stageE.setSelected(true);
            			lastMousePosition = event.getPoint();
            		}
            	}
        	}
        }
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
        //Mouse motion events
        public void mouseDragged(MouseEvent event) 
        {
//    		if(selected == true)
//    		{
//    			AffineTransform tx = new AffineTransform();
//    			tx.translate(event.getPoint().x - lastMousePosition.x, 
//    					 	 event.getPoint().y - lastMousePosition.y);
//    			s1 = tx.createTransformedShape(s1);
//    			lastMousePosition = event.getPoint();
//    			repaint();
//    		}
        	if(stageA != null)
        	{
            	if(stageA.isSelected() == true )
            	{
            		AffineTransform tx = new AffineTransform();
            		tx.translate(event.getPoint().x - lastMousePosition.x,
            					 event.getPoint().y - lastMousePosition.y);
            		stageA.setRect2(tx.createTransformedShape(stageA.getRect()));
            		lastMousePosition = event.getPoint();
            		stageA.updateCo();
            		repaint();
            	}
        	}
        	if(stageB != null)
        	{
            	if(stageB.isSelected() == true)
            	{
            		AffineTransform tx = new AffineTransform();
            		tx.translate(event.getPoint().x - lastMousePosition.x,
            					 event.getPoint().y - lastMousePosition.y);
            		stageB.setRect2(tx.createTransformedShape(stageB.getRect()));
            		lastMousePosition = event.getPoint();
            		repaint();
            		stageB.updateCo();
            	}
        	}
        	if(stageC != null)
        	{
               	if(stageC.isSelected() == true)
            	{
            		AffineTransform tx = new AffineTransform();
            		tx.translate(event.getPoint().x - lastMousePosition.x,
            					 event.getPoint().y - lastMousePosition.y);
            		stageC.setRect2(tx.createTransformedShape(stageC.getRect()));
            		lastMousePosition = event.getPoint();
            		repaint();
            		stageC.updateCo();
            	}
        	}
        	if(stageD != null)
        	{
            	if(stageD.isSelected() == true)
            	{
            		AffineTransform tx = new AffineTransform();
            		tx.translate(event.getPoint().x - lastMousePosition.x,
            					 event.getPoint().y - lastMousePosition.y);
            		stageD.setRect2(tx.createTransformedShape(stageD.getRect()));
            		lastMousePosition = event.getPoint();
            		repaint();
            		stageD.updateCo();
            	}
        	}
        	if(stageE != null)
        	{
            	if(stageE.isSelected() == true)
            	{
            		AffineTransform tx = new AffineTransform();
            		tx.translate(event.getPoint().x - lastMousePosition.x,
            					 event.getPoint().y - lastMousePosition.y);
            		stageE.setRect2(tx.createTransformedShape(stageE.getRect()));
            		lastMousePosition = event.getPoint();
            		repaint();
            		stageE.updateCo();
            	}
        	}
        }
        public void mouseMoved(MouseEvent event) {
        	
        }
		@Override
		public void mouseWheelMoved(MouseWheelEvent event) 
		{
			if(stageA != null)
			{
				if(stageA.isSelected() == true)
				{
//					AffineTransform tx = new AffineTransform();
//					if(event.getPreciseWheelRotation() < 0)
//					{
//						tx.rotate(-5*(Math.PI/180),stageA.getRect().getBounds().getCenterX(),stageA.getRect().getBounds().getCenterY());
//					}
//					if(event.getPreciseWheelRotation() > 0)
//					{
//						tx.rotate(5*(Math.PI/180),stageA.getRect().getBounds().getCenterX(),stageA.getRect().getBounds().getCenterY());
//					}
//					stageA.setRect2(tx.createTransformedShape(stageA.getRect()));
					stageA.setRotate((int)event.getPreciseWheelRotation());
					stageA.updateCo();
				}
				
			}
			
		}
        
    }
    
    class menucontent extends JPanel {
        
        public menucontent() {
            
            setLayout(new GridLayout(1,2));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            JPanel leftMenuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel rightMenuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            ImageIcon pauseIcon = new ImageIcon("images/pause.png");
            ImageIcon pauseIconSelect = new ImageIcon("images/pauseSelect.png");
            JToggleButton pause = new JToggleButton(pauseIcon);
            pause.setSelectedIcon(pauseIconSelect);
            pause.setContentAreaFilled(false);
            pause.setBorder(null);
            leftMenuPanel.add(pause);
            
            ImageIcon playIcon = new ImageIcon("images/play.png");
            ImageIcon playIconSelect = new ImageIcon("images/playSelect.png");
            JToggleButton play = new JToggleButton(playIcon);
            play.setSelectedIcon(playIconSelect);
            play.setContentAreaFilled(false);
            play.setBorder(null);
            leftMenuPanel.add(play);
            
            ImageIcon stopIcon = new ImageIcon("images/stop.png");
            ImageIcon stopIconSelect = new ImageIcon("images/stopSelect.png");
            JToggleButton stop = new JToggleButton(stopIcon);
            stop.setSelectedIcon(stopIconSelect);
            stop.setContentAreaFilled(false);
            stop.setBorder(null);
            leftMenuPanel.add(stop);            
            
            final JSlider timeSlider = new JSlider(JSlider.HORIZONTAL,0,1000,100);
            Color colorSlider = new Color(111,111,111);
            timeSlider.setBackground(colorSlider);
            timeSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
//                     int value = timeSlider.getValue();
//                     AI ai = new AI(value);
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
            
            Color c = new Color(111,111,111);
            leftMenuPanel.setBackground(c);
            rightMenuPanel.setBackground(c);
            
            add(leftMenuPanel);
            add(rightMenuPanel);    

        }
     
    }
    
    class Maincontent extends JPanel {
             
        private Map map;
        private User user;
        private Thread thread1;
        
        public Maincontent() {
            
            Color c = new Color(64,179,57);
            setBackground(c);
            
            map = new Map();
            user = new User();
            
        }
        
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g; 
            super.paintComponent(g2);
            
            g2.scale(zoom,zoom);
//            g2.translate(posX,posY);
            g2.translate(0, 0);
            g2.rotate(Math.toRadians(t)); 

            threadOne((Graphics2D)g2);
            
        }
        
        public void threadOne(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            
            Runnable run1 = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(50);
                        repaint();
                    } catch(Exception e) {
                        System.out.println(e);
                    }
                }
                
            };   

            map.drawMountain((Graphics2D)g2);
            map.drawTree((Graphics2D)g2);
            map.drawField((Graphics2D)g2);
            map.drawGRID((Graphics2D)g2);
            map.drawEntrance((Graphics2D)g2);
            map.drawRoad((Graphics2D)g2);
            
            user.drawUser((Graphics2D)g2);
            
            //AI
            ai.drawVisitor((Graphics2D)g2);
            
            //Draw Stages
            if(stageA != null)
            {
            	stageA.drawStage(g2);
            }
            if(stageB != null)
            {
            	stageB.drawStage(g2);
            }
            if(stageC != null)
            {
            	stageC.drawStage(g2);
            }
            if(stageD != null)
            {
            	stageD.drawStage(g2);
            }
            if(stageE != null)
            {
            	stageE.drawStage(g2);
            }
            
            thread1 = new Thread(run1);
            thread1.start();
            
        }

    }
    
    class rightcontent extends JPanel {
        
        public rightcontent() {
            
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
                    if(posY < 800 && posX < 800) {
                        posY += 20;
                        posX += 20;
                    }
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
                    if(posY < 800)
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
                    if(posY < 800 && posX > -800) {
                        posY += 20;
                        posX -= 20;  
                    }
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
                    if(posX < 800)
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
                    posX = 600;
                    posY = 350;
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
                    if(posX > -800)
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
                    if(posY > -800 && posX < 800) {
                        posY -= 20;
                        posX += 20;
                    }
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
                    if(posY > -800)
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
                    if(posY > -800 && posX > -800) {
                        posY -= 20;
                        posX -= 20;       
                    } 
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
                    if(zoom > 0.7)
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
            xLength = new JTextField("1000");
            xLength.setBounds(10,270,65,30);
            controlMenu.add(xLength);       
            
            yLength = new JTextField("500");
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
                        if(x <= 2000 && y <= 1000) {
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
            constructionMenu.setBackground(Color.BLACK);
            controlMenu.add(constructionMenu);
            
            JButton podiumA = new JButton("Podium A");
            podiumA.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                	ImageIcon im  = new ImageIcon("images/play.png");
                	stageA = new Stage("StageA",0,0,im.getIconWidth(),im.getIconHeight(),im,1);
                }
            });
            constructionMenu.add(podiumA);
            
            JButton podiumB = new JButton("Podium B");
            podiumB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                	ImageIcon im = new ImageIcon("images/left.png");
                	stageB = new Stage("StageB",(Math.random()*800),(Math.random()*800),im.getIconWidth(),im.getIconHeight(),im,2);
                }
            }); 
            constructionMenu.add(podiumB);
            
            JButton podiumC = new JButton("Podium C");
            podiumC.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                { 
                	ImageIcon im = new ImageIcon("images/up.png");
                	stageC = new Stage("StageC",(Math.random()*800),(Math.random()*800),im.getIconWidth(),im.getIconHeight(),im,3);
                }
            });
            constructionMenu.add(podiumC);
            
            JButton podiumD = new JButton("Podium D");
            podiumD.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                	ImageIcon im = new ImageIcon("Images/down.png");
                	stageD = new Stage("StageD",(Math.random()*800),(Math.random()*800),im.getIconWidth(),im.getIconHeight(),im,4);
                }
            }); 
            constructionMenu.add(podiumD);
            
            JButton podiumE = new JButton("Podium E");
            podiumE.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                { 
                	ImageIcon im = new ImageIcon("Images/upLeft.png");
                	stageE = new Stage("StageE",(Math.random()*800),(Math.random()*800),im.getIconWidth(),im.getIconHeight(),im,5);
                }
            });
            constructionMenu.add(podiumE);
            
            JButton toiletAndInfo = new JButton("ToiletInfo");
            toiletAndInfo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
       
                }
            });
            constructionMenu.add(toiletAndInfo);
            
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

}
