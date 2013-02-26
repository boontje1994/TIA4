import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
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
       
    private static double zoom = 0.9;
    private static int t;
    private static int posX = 600;
    private static int posY = 350;
    
    //Muis
    boolean isSelected = false;
    private static int x = 100;
    private static int y = 100;
    private int offsetX = 0;
    private int offsetY = 0;
    
    //Velden
    private static JTextField xLength;
    private static JTextField yLength;
    
    public MainWindow() {
        
        super("Simulatie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        map = new Map();
        user = new User();
        
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
    
    class Handlerclass implements MouseListener, MouseMotionListener {
        //Mouse events
        public void mouseClicked(MouseEvent event) {}
        public void mouseReleased(MouseEvent event) {}
        public void mousePressed(MouseEvent event) {
            isSelected = true;
        }
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
        //Mouse motion events
        public void mouseDragged(MouseEvent event) {
            if(isSelected == true) {
                
                System.out.println("OffsetX> " + offsetX + "OffsetY> " + offsetY);
 
                offsetX = x - event.getX();
                offsetY = y - event.getY();
                
                x = event.getX();
                y = event.getY();
             
            }
        }
        public void mouseMoved(MouseEvent event) {}
        
    }
    
    class menucontent extends JPanel {
        
        public menucontent() {
            
            setLayout(new FlowLayout(FlowLayout.RIGHT));
            Color c = new Color(111,111,111);
            setBackground(c);
            
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JButton map = new JButton("Agenda");
            add(map);

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
            g2.translate(posX,posY);
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
                   
            thread1 = new Thread(run1);
            thread1.start();
            
        }

    }
    
    class rightcontent extends JPanel {
        
        public rightcontent() {
            
            setBackground(Color.WHITE);
            setLayout(new GridLayout(4,1));
            
            //Control menu
            JPanel controlMenu = new JPanel(new GridLayout(3,3));
            add(controlMenu);
            
            //Linksboven
            JButton upLeft = new JButton("upLeft");
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
            JButton up = new JButton("Up");
            up.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(posY < 800)
                    posY += 20;
                }
            });
            controlMenu.add(up);
            
            //Rechtsboven
            JButton upRight = new JButton("upRight");
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
            JButton left = new JButton("Left");
            left.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(posX < 800)
                    posX += 20;
                }
            });
            controlMenu.add(left);
            
            //Positie
            JButton position = new JButton("Position");
            position.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    posX = 600;
                    posY = 350;
                }
            });
            controlMenu.add(position);
            
            //Right
            JButton right = new JButton("Right");
            right.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(posX > -800)
                    posX -= 20;
                }
            });
            controlMenu.add(right);

            //Linksonder
            JButton downLeft = new JButton("downLeft");
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
            JButton down = new JButton("Down");
            down.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(posY > -800)
                    posY -= 20;
                }
            });
            controlMenu.add(down);
            
            //Rechtsonder
            JButton downRight = new JButton("downRight");
            downRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(posY > -800 && posX > -800) {
                        posY -= 20;
                        posX -= 20;       
                    } 
                }
            });
            controlMenu.add(downRight);
            
            //Veld menu
            JPanel fieldMenu = new JPanel(new GridLayout(2,2));
            add(fieldMenu); 
            
            //Inzoemen
            JButton zoomIn = new JButton("+");
            zoomIn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(zoom < 1.4)
                    zoom += 0.1;
                }
            });
            fieldMenu.add(zoomIn);
            
            //Uitzoemen
            JButton zoomOut = new JButton("-");
            zoomOut.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(zoom > 0.7)
                    zoom -= 0.1;
                }
            });
            fieldMenu.add(zoomOut);
            
            //Draaien links
            JButton rotateLeft = new JButton("Draaien Links");
            rotateLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   t += 5;
                }
            });
            fieldMenu.add(rotateLeft);
            
            //Draaien rechts
            JButton rotateRight = new JButton("Draaien Rechts");
            rotateRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    t -= 5;
                }
            });
            fieldMenu.add(rotateRight);
            
            //Grid menu
            JPanel gridMenu = new JPanel(new GridLayout(2,3));
            add(gridMenu);
            
            JLabel xLengthLabel = new JLabel("x:");
            gridMenu.add(xLengthLabel);

            xLength = new JTextField("1000");
            gridMenu.add(xLength);
            
            JLabel fixedSize = new JLabel("");
            gridMenu.add(fixedSize);
            
            JLabel yLengthLabel = new JLabel("y:");
            gridMenu.add(yLengthLabel);
            
            yLength = new JTextField("500");
            gridMenu.add(yLength);
            
            JButton change = new JButton("Wijzig");
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
            gridMenu.add(change);
            
            //Construction menu
            JPanel constructionMenu = new JPanel(new GridLayout(6,2));
            add(constructionMenu);
            
            JButton save = new JButton("Loos");
            save.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) { 
                
                }
            });
            constructionMenu.add(save);
            
            JButton reset = new JButton("Reset");
            reset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                }
            });
            constructionMenu.add(reset);

            JLabel podiumALabel = new JLabel("Podium 1");
            constructionMenu.add(podiumALabel);
            
            JButton podiumA = new JButton("Podium");
            podiumA.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
              
                }
            });
            constructionMenu.add(podiumA);
            
            JLabel podiumBLabel = new JLabel("Podium 2");
            constructionMenu.add(podiumBLabel);
            
            JButton podiumB = new JButton("Podium");
            podiumB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
           
                }
            }); 
            constructionMenu.add(podiumB);
            
            JLabel podiumCLabel = new JLabel("Poduim 3");
            constructionMenu.add(podiumCLabel);
            
            JButton podiumC = new JButton("Podium");
            podiumC.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
         
                }
            });
            constructionMenu.add(podiumC);
            
            JLabel toiletAndInfoLabel = new JLabel("ToiletInfo");
            constructionMenu.add(toiletAndInfoLabel);
            
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
